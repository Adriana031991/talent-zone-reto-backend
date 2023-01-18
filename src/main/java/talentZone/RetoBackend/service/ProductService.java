package talentZone.RetoBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import talentZone.RetoBackend.dto.PaginationProductDTO;
import talentZone.RetoBackend.dto.ProductDto;
import talentZone.RetoBackend.entity.Product;
import talentZone.RetoBackend.repository.ProductRepository;
import talentZone.RetoBackend.utils.AppUtils;

import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Flux<PaginationProductDTO> getProducts() {



        PaginationProductDTO paginationProductDTO = new PaginationProductDTO();
        paginationProductDTO.setProducts(repository.findAll()
                        .skip(0)
                        .take(1)
                .map(AppUtils::entityToDto));
        paginationProductDTO.getProducts().count().subscribe(c->{
            paginationProductDTO.setTotalPages(c.intValue());
        });


        return Flux.just(paginationProductDTO);
    }

    public Mono<ProductDto> getProduct(String id) {
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Flux<ProductDto> getProductInRange(double min, double max) {
        return repository.findByPriceBetween(Range.closed(min,max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(AppUtils::dtoToentity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }
    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id) {
        return repository.findById(id)
                .flatMap(p->productDtoMono.map(AppUtils::dtoToentity))
                .doOnNext(e->e.setId(id))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto);
    }

//    public Mono<Void> deleteProduct(Mono<ProductDto> productDtoMono, String id) {
//        repository.findById(id)
//                .flatMap(p->productDtoMono.map(AppUtils::dtoToentity))
//                .doOnNext(e->e.setId(id))
//                .flatMap(repository::save)
//                .map(AppUtils::entityToDto);
//    }

    public Mono<Void> deleteProduct(String id) {

        return repository.findById(id)
                .map(p->{
                    p.setName("Disable");
                    return p;
                }).then();
    }
}

