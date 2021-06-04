package com.leanpay.amortizationCalculator.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AmortizationSchedule {

  private Long id;
  @NotNull
  private Integer month;
  @NotNull
  private BigDecimal paymentAmount;
  @NotNull
  private BigDecimal principalAmount;
  @NotNull
  private BigDecimal interestAmount;
  @NotNull
  private BigDecimal balanceOwed;
}
