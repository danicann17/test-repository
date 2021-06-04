package com.leanpay.amortizationCalculator.dto;

import com.leanpay.amortizationCalculator.entity.AmortizationScheduleEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentInformation {

  private Long id;
  @NotNull
  private BigDecimal totalPayment;
  @NotNull
  private BigDecimal totalInterest;

//private LoanInformationEntity loanInformation;

  private List<AmortizationScheduleEntity> amortizationSchedules;
}
