package talentZone.RetoBackend.dto;

import lombok.*;
import reactor.core.publisher.Flux;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaginationProductDTO {

    private Flux<ProductDto> products;

    private int totalPages;
}
