package bm.app.models;

import java.math.BigDecimal;
import java.util.UUID;

public class AdditionalMiniProduct {

    UUID id;
    BigDecimal netPrice;
    String type;

    public AdditionalMiniProduct(UUID id, BigDecimal netPrice, String type) {
        this.id = id;
        this.netPrice = netPrice;
        this.type = type;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }


}
