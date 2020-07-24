package bm.app.services;

import bm.app.models.AdditionalMiniProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class VatServiceTest {
    VatService vatService;
    VatProvider vatProvider;

    @Test
    @DisplayName("Should calculate the gross price for the default VAT value.")
    void shouldCalculateGrossPriceForDefaultVat() throws Exception {
        //given
        Mockito.when(vatProvider.getDefaultVat()).thenReturn(new BigDecimal("0.23"));
        AdditionalMiniProduct product = generateProduct("400.00", "Small insurance");
        //when
        BigDecimal result = vatService.getGrossPriceForDefaultVat(product);
        //then
        assertThat(result).isEqualTo(new BigDecimal("492.00"));
    }

    @Test
    @DisplayName("Should calculate the gross price for a different VAT value.")
    void shouldCalculateGrossPriceForDifferentVat() throws Exception {
        //given
        String type = "SMS reminder service";
        AdditionalMiniProduct product = generateProduct("100", type);
        Mockito.when(vatProvider.getVatForType(type)).thenReturn(new BigDecimal("0.08"));
        //when
        BigDecimal result = vatService.getGrossPrice(product.getNetPrice(), type);
        //then
        assertThat(result).isEqualTo(new BigDecimal("108.00"));
    }

    @Test
    @DisplayName("Should throw Exception when VAT value is too high.")
    void shouldThrowExceptionWhenVatIsTooHigh() {
        //given
        String type = "Regular advice service";
        AdditionalMiniProduct additionalMiniProduct = generateProduct("1000.00", type);
        Mockito.when(vatProvider.getVatForType(type)).thenReturn(BigDecimal.TEN);
        //then
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            vatService.getGrossPrice(additionalMiniProduct.getNetPrice(), type);
        });

    }

    private AdditionalMiniProduct generateProduct(String price, String type) {
        return new AdditionalMiniProduct(UUID.randomUUID(), new BigDecimal(price), type);
    }

    @BeforeEach
    void setUp() {
        vatProvider = Mockito.mock(VatProvider.class);
        vatService = new VatService(vatProvider);
    }
}
