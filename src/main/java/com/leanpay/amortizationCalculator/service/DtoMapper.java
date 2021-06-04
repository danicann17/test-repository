package com.leanpay.amortizationCalculator.service;

import com.leanpay.amortizationCalculator.dto.AmortizationSchedule;
import com.leanpay.amortizationCalculator.dto.LoanInformation;
import com.leanpay.amortizationCalculator.dto.PaymentInformation;
import com.leanpay.amortizationCalculator.entity.AmortizationScheduleEntity;
import com.leanpay.amortizationCalculator.entity.LoanInformationEntity;
import com.leanpay.amortizationCalculator.entity.PaymentInformationEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper {

  private final ModelMapper modelMapper;

  @Autowired
  public DtoMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public AmortizationSchedule toDto(AmortizationScheduleEntity entity) {
    return modelMapper.map(entity, AmortizationSchedule.class);
  }

  public AmortizationScheduleEntity toEntity(AmortizationSchedule dto) {
    return modelMapper.map(dto, AmortizationScheduleEntity.class);
  }

  public LoanInformation toDto(LoanInformationEntity entity) {
    return modelMapper.map(entity, LoanInformation.class);
  }

  public LoanInformationEntity toEntity(LoanInformation dto) {
    return modelMapper.map(dto, LoanInformationEntity.class);
  }

  public PaymentInformation toDto(PaymentInformationEntity entity) {
    return modelMapper.map(entity, PaymentInformation.class);
  }

  public PaymentInformationEntity toEntity(PaymentInformation dto) {
    return modelMapper.map(dto, PaymentInformationEntity.class);
  }


}
