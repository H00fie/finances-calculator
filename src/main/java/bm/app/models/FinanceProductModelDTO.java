package bm.app.models;

import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class FinanceProductModelDTO {

    private int id;
    private String name;
    private BigDecimal price;
    private int validityperiod;
    private RiskLevel riskLevel;


}
