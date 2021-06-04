package com.leanpay.amortizationCalculator.dto;

import com.leanpay.amortizationCalculator.type.PaymentFrequency;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanInformation {

  private Long id;
  @NotNull
  private BigDecimal loanAmount;
  @NotNull
  private BigDecimal interestRate;
  @NotNull
  private Integer loanTerm;
//  @NotNull
  private PaymentFrequency frequency;
}
