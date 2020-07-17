import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static bm.app.services.Service.*;

public class Tests {

    @Test
    void shouldReturnValueHigherByTenPercent(){
        BigDecimal amount = new BigDecimal("100");
        BigDecimal expected = new BigDecimal("110");
        assertEquals(expected, increaseByTenPercent(amount));
    }


    //TODO -> introduce a mock

    @Test
    void shouldReturnAPriceOfAProvidedFinancialProduct(){
        BigDecimal productPrice = new BigDecimal(1200);
        String productName = "SilverLoan";
        assertEquals(productPrice, findAProductPriceByGivenName(productName));
    }




}
