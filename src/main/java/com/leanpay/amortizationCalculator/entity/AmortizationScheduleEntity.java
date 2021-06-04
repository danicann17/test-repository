package com.leanpay.amortizationCalculator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "amortization_schedule")
public class AmortizationScheduleEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "month")
  private Integer month;

  @Digits(integer=5, fraction=2)
  @Column(name = "payment_amount")
  private BigDecimal paymentAmount;

  @Digits(integer=5, fraction=2)
  @Column(name = "principal_amount")
  private BigDecimal principalAmount;

  @Digits(integer=5, fraction=2)
  @Column(name = "interest_amount")
  private BigDecimal interestAmount;

  @Digits(integer=5, fraction=2)
  @Column(name = "balance_owed")
  private BigDecimal balanceOwed;

  @JsonIgnore
  @ManyToOne(targetEntity = PaymentInformationEntity.class)
  @JoinColumn(nullable = false, name = "payment_information_id")
  private PaymentInformationEntity paymentInformation;

}
