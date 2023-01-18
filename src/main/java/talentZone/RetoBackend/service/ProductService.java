package talentZone.RetoBackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import talentZone.RetoBackend.dto.PaginationProductDTO;
import talentZone.RetoBackend.dto.ProductDto;
import talentZone.RetoBackend.repository.ProductRepository;
import talentZone.RetoBackend.utils.AppUtils;
import talentZone.RetoBackend.utils.PageSupport;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository repository;

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

    public Mono<Void> deleteProduct(String id) {
        return repository.findById(id)
                .map(p->{
                    p.setEnabled(false);
                    return p;
                }).then();
    }


    public Mono<PageSupport<ProductDto>> getAllProducts(Pageable page) {
        return repository.findAll()
                .collectList()
                .map(list -> new PageSupport<>(
                        list
                                .stream()
                                .skip(page.getPageNumber() * page.getPageSize())
                                .limit(page.getPageSize())
                                .map(AppUtils::entityToDto)
                                .collect(Collectors.toList()),
                        page.getPageNumber(),
                        page.getPageSize(),
                        list.size()))
                ;
    }

}

