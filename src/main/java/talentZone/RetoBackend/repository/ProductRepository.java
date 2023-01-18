package talentZone.RetoBackend.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import talentZone.RetoBackend.dto.ProductDto;
import talentZone.RetoBackend.entity.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
