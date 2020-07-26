package bm.app.services;

import bm.app.models.AdditionalMiniProduct;
import bm.app.models.RiskLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static bm.app.services.Service.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceTests {

    Service service;
    DiscountProvider discountProvider;

    @Test
    @DisplayName("Should return the value higher by ten percent.")
    void shouldReturnValueHigherByTenPercent() {
        //given
        Mockito.when(discountProvider.getThePriceOfTheProduct()).thenReturn(new BigDecimal("20.00"));
        AdditionalMiniProduct product = new AdditionalMiniProduct(UUID.randomUUID(), new BigDecimal("300.00"), "Yellow Loan");
        //when
        BigDecimal result = service.increaseByGivenAmount(product.getNetPrice(), discountProvider.getThePriceOfTheProduct());
        //then
        assertThat(result).isEqualTo("315.00");
    }

    @Test
    void shouldReturnACorrectRiskLevelValue() {
        int months = 18;
        assertEquals(5, calculateRiskLevelByNumberOfMonths(months));
    }

    @Test
    void shouldReturnACorrectEnumValueBasedOnNumberOfMonths() {
        int months = 26;
        assertEquals(RiskLevel.HIGH, giveTheRiskNameBasedOnNumberOfMonths(months));
    }

    @Test
    void shouldReturnAPriceOfAProvidedFinancialProduct() {
        BigDecimal productPrice = new BigDecimal(1200);
        String productName = "SilverLoan";
        assertEquals(productPrice, findAProductPriceByGivenName(productName));
    }

    @BeforeEach
    void setUp() {
        service = new Service(discountProvider);
        discountProvider = Mockito.mock(DiscountProvider.class);
    }


}
