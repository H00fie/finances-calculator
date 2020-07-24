package bm.app.services;

import bm.app.models.AdditionalMiniProduct;

import java.math.BigDecimal;
import java.math.MathContext;

public class VatService {

    BigDecimal vatValue;

    public VatService(){
        this.vatValue = new BigDecimal("0.23");
    }

    public BigDecimal getGrossPriceForDefaultVat(AdditionalMiniProduct additionalMiniProduct) throws Exception {
        return getGrossPrice(additionalMiniProduct.getNetPrice(), vatValue);
    }

    public BigDecimal getGrossPrice(BigDecimal netPrice, BigDecimal vatValue) throws Exception {
        MathContext mathContext = new MathContext(5);
        if (vatValue.compareTo(BigDecimal.ONE) == 1){
            throw new Exception("VAT has to be lower than one.");
        }
        return netPrice.multiply(vatValue.add(BigDecimal.ONE)).round(mathContext);
    }
}
