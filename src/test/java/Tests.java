import bm.app.models.RiskLevel;
import ch.qos.logback.classic.BasicConfigurator;
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

    @Test
    void shouldReturnACorrectRiskLevelValue(){
        int months = 18;
        assertEquals(5, calculateRiskLevelByNumberOfMonths(months));
    }

    @Test
    void shouldReturnACorrectEnumValueBasedOnNumberOfMonths(){
        int months = 26;
        assertEquals(RiskLevel.HIGH, giveTheRiskNameBasedOnNumberOfMonths(months));
    }

    //TODO -> introduce a mock

    @Test
    void shouldReturnAPriceOfAProvidedFinancialProduct(){
        BigDecimal productPrice = new BigDecimal(1200);
        String productName = "SilverLoan";
        assertEquals(productPrice, findAProductPriceByGivenName(productName));
    }




}
