package bm.app.services;

import bm.app.exceptions.IncorrectVatException;
import bm.app.models.AdditionalMiniProduct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;


public class VatService {

    VatProvider vatProvider;

    public VatService(Optional<VatProvider> vatProvider){
        vatProvider.ifPresent(vatProvider1 -> {this.vatProvider = vatProvider1;});
    }

    public BigDecimal getGrossPriceForDefaultVat(AdditionalMiniProduct additionalMiniProduct) throws IncorrectVatException {
        return calculateGrossPrice(additionalMiniProduct.getNetPrice(), vatProvider.getDefaultVat());
    }

    public BigDecimal getGrossPrice(BigDecimal netPrice, String productType) throws IncorrectVatException {
        BigDecimal vatValue = vatProvider.getVatForType(productType);
        return calculateGrossPrice(netPrice, vatValue);
    }

    private BigDecimal calculateGrossPrice(BigDecimal netPrice, BigDecimal vatValue) throws IncorrectVatException {
        MathContext mathContext = new MathContext(5);
        if (vatValue.compareTo(BigDecimal.ONE) == 1){
            throw new IncorrectVatException("VAT has to be lower than one.");
        }
        return netPrice.multiply(vatValue.add(BigDecimal.ONE)).round(mathContext);
    }
}




