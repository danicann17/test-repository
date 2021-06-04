package com.leanpay.amortizationCalculator.repository;

import com.leanpay.amortizationCalculator.entity.LoanInformationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanInformationRepository extends
    PagingAndSortingRepository<LoanInformationEntity, Long> {
}
