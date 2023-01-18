package talentZone.RetoBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import talentZone.RetoBackend.dto.PaginationProductDTO;
import talentZone.RetoBackend.dto.ProductDto;
import talentZone.RetoBackend.entity.Product;
import talentZone.RetoBackend.service.ProductService;

@RestController
@RequestMapping("/products")
public class productController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<PaginationProductDTO> products(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }

    @GetMapping("/product-range")
    public Flux<ProductDto> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max){
        return productService.getProductInRange(min, max);
    }


    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return productService.saveProduct(productDtoMono);
    }

    @PutMapping("{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id){
        return productService.updateProduct(productDtoMono, id);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id);
    }

}
