package com.leanpay.amortizationCalculator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_information")
public class PaymentInformationEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Digits(integer=5, fraction=2)
  @Column(name = "total_payment")
  private BigDecimal totalPayment;

  @Digits(integer=5, fraction=2)
  @Column(name = "total_interest")
  private BigDecimal totalInterest;

  @ManyToOne(targetEntity = LoanInformationEntity.class)
  @JoinColumn(nullable = false, name = "loan_information_id")
  private LoanInformationEntity loanInformation;

  @OneToMany(mappedBy = "paymentInformation", cascade = CascadeType.ALL)
  private List<AmortizationScheduleEntity> amortizationSchedules;

}
