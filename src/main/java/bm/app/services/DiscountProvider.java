package bm.app.services;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface DiscountProvider {

    BigDecimal getThePriceOfTheProduct();
}
