package com.leanpay.amortizationCalculator.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.leanpay.amortizationCalculator.dto.LoanInformation;
import com.leanpay.amortizationCalculator.service.LoanInformationService;
import com.leanpay.amortizationCalculator.type.PaymentFrequency;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(LoanInformationController.class)
@ExtendWith(SpringExtension.class)
public class LoanInformationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LoanInformationService loanInformationService;

  private final LoanInformation loanInformationFirst =
      new LoanInformation(1L, BigDecimal.valueOf(2000), BigDecimal.valueOf(7.5), 10,
          PaymentFrequency.MONTHLY);
  private final LoanInformation loanInformationSecond =
      new LoanInformation(2L, BigDecimal.valueOf(3000), BigDecimal.valueOf(15), 20,
          PaymentFrequency.WEEKLY);

  @Test
  public void findById_LoanInfoNotFound_ShouldReturn404() throws Exception {
    final Long id = 22L;
    when(loanInformationService.findById(id))
        .thenReturn(Optional.empty());

    mockMvc
        .perform(MockMvcRequestBuilders.get("/loan-information/{id}", id))
        .andExpect(status().isNotFound());

    verify(loanInformationService, times(1)).findById(id);
    verifyNoMoreInteractions(loanInformationService);
  }

}
