package com.leanpay.amortizationCalculator.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;

public enum PaymentFrequency {

  DAILY(365),
  WEEKLY(52),
  BI_WEEKLY(26),
  SEMI_MONTH(24),
  MONTHLY(12),
  BI_MONTHLY(6),
  QUARTERLY(4),
  SEMI_ANNUAL(2),
  ANNUAL(1);

  private final Integer value;

  private final static Map<Integer, PaymentFrequency> VALUES = new HashMap<>();

  PaymentFrequency(Integer value) {
    this.value = value;
  }

  static {
    for (PaymentFrequency frequency : PaymentFrequency.values()) {
      VALUES.put(frequency.value, frequency);
    }
  }

  @Override
  public String toString() {
    return String.valueOf(this.getValue());
  }

  public Integer getValue() {
    return value;
  }

  public static PaymentFrequency fromString(String value) {
    return VALUES.get(value);
  }

}
