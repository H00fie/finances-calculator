package bm.app.services;

import bm.app.models.AdditionalMiniProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VatServiceTest {
    VatService vatService;

    @Test
    @DisplayName("Should calculate the gross price for the default VAT value.")
    void shouldCalculateGrossPriceForDefaultVat() throws Exception {
        //given
        AdditionalMiniProduct product = generateProductWithPrice("400.00");
        //when
        BigDecimal result = vatService.getGrossPriceForDefaultVat(product);
        //then
        assertThat(result).isEqualTo(new BigDecimal("492.00"));
    }

    @Test
    @DisplayName("Should calculate the gross price for a different VAT value.")
    void shouldCalculateGrossPriceForDifferentVat() throws Exception {
        //given
        AdditionalMiniProduct product = generateProductWithPrice("100");
        //when
        BigDecimal result = vatService.getGrossPrice(product.getNetPrice(), new BigDecimal("0.08"));
        //then
        assertThat(result).isEqualTo(new BigDecimal("108.00"));
    }

    @Test
    @DisplayName("Should throw Exception when VAT value is too high.")
    void shouldThrowExceptionWhenVatIsTooHigh(){
        //given
        AdditionalMiniProduct additionalMiniProduct = generateProductWithPrice("1000.00");
        //then
        assertThrows(Exception.class, () -> {
            vatService.getGrossPrice(additionalMiniProduct.getNetPrice(), BigDecimal.TEN);
        });
    }

    private AdditionalMiniProduct generateProductWithPrice(String price){
        return new AdditionalMiniProduct(UUID.randomUUID(), new BigDecimal(price));
    }

    @BeforeEach
    void setUp(){
        vatService = new VatService();
    }
}
