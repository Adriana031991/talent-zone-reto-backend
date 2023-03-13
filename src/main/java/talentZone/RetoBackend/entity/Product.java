package talentZone.RetoBackend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
@Builder
public class Product {
    @Id
    private String id;
    private String name;
    private int minAmount;
    private int maxAmount;

    private double price;


    private int inventory;
    private boolean enabled;




}
