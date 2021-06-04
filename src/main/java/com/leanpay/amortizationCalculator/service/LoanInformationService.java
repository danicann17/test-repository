package com.leanpay.amortizationCalculator.service;

import com.leanpay.amortizationCalculator.dto.LoanInformation;
import com.leanpay.amortizationCalculator.dto.PaymentInformation;
import com.leanpay.amortizationCalculator.entity.AmortizationScheduleEntity;
import com.leanpay.amortizationCalculator.entity.LoanInformationEntity;
import com.leanpay.amortizationCalculator.entity.PaymentInformationEntity;
import com.leanpay.amortizationCalculator.repository.LoanInformationRepository;
import com.leanpay.amortizationCalculator.repository.PaymentInformationRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LoanInformationService {

  private final LoanInformationRepository loanInformationRepository;
  private final PaymentInformationRepository paymentInformationRepository;
  private final DtoMapper dtoMapper;

  @Autowired
  public LoanInformationService(LoanInformationRepository loanInformationRepository,
                                PaymentInformationRepository paymentInformationRepository,
                                DtoMapper dtoMapper) {
    this.loanInformationRepository = loanInformationRepository;
    this.paymentInformationRepository = paymentInformationRepository;
    this.dtoMapper = dtoMapper;
  }

  public List<LoanInformation> findAll(Pageable page) {
    List<LoanInformationEntity> loanInformation =
        loanInformationRepository.findAll(page).getContent();
    return loanInformation.stream().map(dtoMapper::toDto)
        .collect(Collectors.toList());
  }

  public Optional<LoanInformation> findById(Long id) {
    return loanInformationRepository.findById(id)
        .map(dtoMapper::toDto);
  }

  public PaymentInformation saveLoanInformation(LoanInformation loanInformation) {
    LoanInformationEntity loanInformationEntity =
        loanInformationRepository.save(dtoMapper.toEntity(loanInformation));
    PaymentInformationEntity paymentInformation = new PaymentInformationEntity();
    BigDecimal paymentAmount = this.calculatePaymentAmount(loanInformation);

    BigDecimal totalPayment =
        paymentAmount.multiply(BigDecimal.valueOf(loanInformation.getLoanTerm()));
    BigDecimal totalInterest = totalPayment.subtract(loanInformation.getLoanAmount());
    paymentInformation.setTotalPayment(totalPayment);
    paymentInformation.setTotalInterest(totalInterest);
    paymentInformation.setLoanInformation(loanInformationEntity);
    paymentInformation.setAmortizationSchedules(
        this.calculateAmortizationSchedule(loanInformation, paymentAmount, paymentInformation));
    return dtoMapper.toDto(paymentInformationRepository.save(paymentInformation));
  }

  private BigDecimal calculatePaymentAmount(LoanInformation loanInformation) {
    BigDecimal one = BigDecimal.valueOf(1);
    BigDecimal loanAmount = loanInformation.getLoanAmount();
    BigDecimal interestRate = calculateInterestRate(loanInformation);
    BigDecimal interestRatePlusOne = (interestRate.add(one)).pow(loanInformation.getLoanTerm());

    return (loanAmount.multiply(interestRate).multiply(interestRatePlusOne))
        .divide(interestRatePlusOne.subtract(one), 2, RoundingMode.HALF_UP);
  }

  private BigDecimal calculateInterestRate(LoanInformation loanInformation) {
    BigDecimal hundred = BigDecimal.valueOf(100);
    BigDecimal frequency = BigDecimal.valueOf(loanInformation.getFrequency().getValue());
    return loanInformation.getInterestRate()
        .divide(frequency, 5, RoundingMode.HALF_UP).divide(hundred);
  }

  private List<AmortizationScheduleEntity> calculateAmortizationSchedule(
      LoanInformation loanInformation, BigDecimal paymentAmount,
      PaymentInformationEntity paymentInformation) {
    List<AmortizationScheduleEntity> amortizationSchedules = new ArrayList<>();
    BigDecimal balanceOwed = loanInformation.getLoanAmount();
    BigDecimal interestRate = this.calculateInterestRate(loanInformation);
    Integer month = 1;

    for (int i = 0; i < loanInformation.getLoanTerm(); i++, month++) {
      AmortizationScheduleEntity amortizationSchedule = new AmortizationScheduleEntity();
      BigDecimal interestAmount =
          balanceOwed.multiply(interestRate).setScale(2, RoundingMode.HALF_UP);
      BigDecimal principalAmount =
          paymentAmount.subtract(interestAmount).setScale(2, RoundingMode.HALF_UP);
      balanceOwed =
          balanceOwed.subtract(principalAmount).setScale(2, RoundingMode.HALF_UP);

      amortizationSchedule.setMonth(month);
      amortizationSchedule.setPaymentAmount(paymentAmount);
      amortizationSchedule.setPrincipalAmount(principalAmount);
      amortizationSchedule.setInterestAmount(interestAmount);
      amortizationSchedule.setBalanceOwed(balanceOwed);
      amortizationSchedule.setPaymentInformation(paymentInformation);
      amortizationSchedules.add(amortizationSchedule);
    }
    return amortizationSchedules;
  }
}
