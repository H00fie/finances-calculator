package bm.app.services;

import bm.app.models.AdditionalMiniProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VatServiceTest {
    VatService vatService;

    @Test
    void shouldCalculateGrossPriceForDefaultVat() {
        //given
        AdditionalMiniProduct product = generateProductWithPrice("400.00");
        //when
        BigDecimal result = vatService.getGrossPriceForDefaultVat(product);
        //then
        assertEquals(new BigDecimal("492.00"), result);

    }

    @Test
    void shouldCalculateGrossPriceForDifferentVat() {
        //given
        AdditionalMiniProduct product = generateProductWithPrice("100");
        //when
        BigDecimal result = vatService.getGrossPrice(product.getNetPrice(), new BigDecimal("0.08"));
        //then
        assertEquals(new BigDecimal("108.00"), result);
    }

    private AdditionalMiniProduct generateProductWithPrice(String vat){
        return new AdditionalMiniProduct(UUID.randomUUID(), new BigDecimal(vat));
    }

    @BeforeEach
    void setUp(){
        vatService = new VatService();
    }
}
