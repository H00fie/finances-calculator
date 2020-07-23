package bm.app.services;

import bm.app.models.AdditionalMiniProduct;

import java.math.BigDecimal;
import java.math.MathContext;

public class VatService {

    BigDecimal vatValue;

    public VatService(){
        this.vatValue = new BigDecimal("0.23");
    }

    public BigDecimal getGrossPriceForDefaultVat(AdditionalMiniProduct additionalMiniProduct){
        return getGrossPrice(additionalMiniProduct.getNetPrice(), vatValue);
    }

    public BigDecimal getGrossPrice(BigDecimal netPrice, BigDecimal vatValue){
        MathContext mathContext = new MathContext(4);
        return netPrice.multiply(vatValue.add(BigDecimal.ONE)).round(mathContext);
    }
}
