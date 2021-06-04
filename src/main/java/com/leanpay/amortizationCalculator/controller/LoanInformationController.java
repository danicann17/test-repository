package com.leanpay.amortizationCalculator.controller;

import com.leanpay.amortizationCalculator.dto.LoanInformation;
import com.leanpay.amortizationCalculator.dto.PaymentInformation;
import com.leanpay.amortizationCalculator.service.LoanInformationService;
import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanInformationController {

  private final LoanInformationService loanInformationService;

  public LoanInformationController(LoanInformationService loanInformationService) {
    this.loanInformationService = loanInformationService;
  }

  @GetMapping("/loan-information")
  public List<LoanInformation> findAll(Pageable page) {
    return loanInformationService.findAll(page);
  }

  @GetMapping("/loan-information/{id}")
  public LoanInformation getById(@PathVariable("id") Long id) {
    return loanInformationService.findById(id)
        .orElseThrow(NoSuchElementException::new);
  }

  @PostMapping(value = "/loan-information")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public PaymentInformation createLoanInformationWithPayment(
      @Valid @RequestBody LoanInformation loanInformation) {
    return loanInformationService.saveLoanInformation(loanInformation);
  }


}
