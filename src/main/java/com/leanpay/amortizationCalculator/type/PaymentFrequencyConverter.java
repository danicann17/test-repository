package com.leanpay.amortizationCalculator.type;

import javax.persistence.AttributeConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@javax.persistence.Converter(autoApply = true)
public class PaymentFrequencyConverter
    implements AttributeConverter<PaymentFrequency, String>, Converter<String, PaymentFrequency>
{

  @Override
  public String convertToDatabaseColumn(PaymentFrequency frequency) {
    return frequency.getValue().toString();
  }

  @Override
  public PaymentFrequency convertToEntityAttribute(String dbData) {
    return PaymentFrequency.fromString(dbData);
  }

  @Override
  public PaymentFrequency convert(String source) {
    return PaymentFrequency.fromString(source);
  }
}