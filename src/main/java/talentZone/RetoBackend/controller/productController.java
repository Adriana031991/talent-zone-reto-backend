package talentZone.RetoBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import talentZone.RetoBackend.dto.ProductDto;
import talentZone.RetoBackend.exceptions.ValidationException;
import talentZone.RetoBackend.service.ProductService;
import talentZone.RetoBackend.utils.PageSupport;

@RestController
@RequestMapping("/products")
public class productController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Mono<PageSupport<ProductDto>> getProductsPaginated(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size",  defaultValue = "20") int size, @RequestParam(required = false) Sort sortDirection){
        try {
            return productService.getAllProducts(PageRequest.of(page, size));
        } catch(Exception e) {
            //String message = e.getMessage();
            return Mono.error(e);

        }
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id){
        try{
            return productService.getProduct(id);
        } catch(Exception e) {
            return Mono.error(e);

        }
    }

    @GetMapping("/product-range")
    public Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max){
        try {
            return productService.getProductInRange(min, max);
        } catch(Exception e) {
            return Flux.error(e);

        }
    }


    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        try {
            return productService.saveProduct(productDtoMono);
        } catch(Exception e) {
            return Mono.error(e);
        }
    }

    @PutMapping("{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id){
        try {
            return productService.updateProduct(productDtoMono, id);
        } catch(Exception e) {
            return Mono.error(e);
        }
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        try {
            return productService.deleteProduct(id);
        } catch(Exception e) {
            return Mono.error(e);
        }
    }

}
