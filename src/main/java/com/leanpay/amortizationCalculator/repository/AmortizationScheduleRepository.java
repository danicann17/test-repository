package com.leanpay.amortizationCalculator.repository;

import com.leanpay.amortizationCalculator.entity.AmortizationScheduleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmortizationScheduleRepository extends
    PagingAndSortingRepository<AmortizationScheduleEntity, Long> {
}
