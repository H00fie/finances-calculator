package bm.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
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


}
