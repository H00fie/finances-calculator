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
public class FinanceProductModel {

    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private int validityperiod;


}
