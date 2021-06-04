package com.leanpay.amortizationCalculator.repository;

import com.leanpay.amortizationCalculator.entity.PaymentInformationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentInformationRepository extends
    PagingAndSortingRepository<PaymentInformationEntity, Long> {
}
