package talentZone.RetoBackend.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private String id;
    private String name;
    private int minAmount;
    private int maxAmount;
    private double price;
}
