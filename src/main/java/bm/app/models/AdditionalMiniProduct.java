package bm.app.models;

import java.math.BigDecimal;
import java.util.UUID;

public class AdditionalMiniProduct {

    UUID id;
    BigDecimal netPrice;

    public AdditionalMiniProduct(UUID id, BigDecimal netPrice) {
        this.id = id;
        this.netPrice = netPrice;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }
}
