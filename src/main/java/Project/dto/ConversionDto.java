package Project.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ConversionDto {

    private Long id;

    private Double sum;

    private String currency;

    private String targetCurrency;

}
