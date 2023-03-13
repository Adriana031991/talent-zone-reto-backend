package talentZone.RetoBackend.dto;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import talentZone.RetoBackend.exceptions.ValidationException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private String id;

    @NotBlank(message = "Name is mandatory")
    private String name;
    private int minAmount;
    @NotNull
    private int maxAmount;

    @NotNull
    @Range(min = 1)
    private double price;

    @NotBlank(message = "Give a quantity")
    @Size(min = 10)
    private int inventory;
    private boolean enabled;

    public ProductDto () {
        enabled = true;
        minAmount = 1;

    }

}
