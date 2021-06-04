package com.leanpay.amortizationCalculator.entity;

import com.leanpay.amortizationCalculator.type.PaymentFrequency;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loan_information")
public class LoanInformationEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "loan_amount")
  private BigDecimal loanAmount;

  @Column(name = "interest_rate")
  private BigDecimal interestRate;

  @Column(name = "loan_term")
  private Integer loanTerm;

  @Column(name = "payment_frequency", columnDefinition = "frequency")
  private PaymentFrequency frequency;

  @OneToMany(mappedBy = "loanInformation", cascade = CascadeType.ALL)
  private List<PaymentInformationEntity> paymentInformations;
}
