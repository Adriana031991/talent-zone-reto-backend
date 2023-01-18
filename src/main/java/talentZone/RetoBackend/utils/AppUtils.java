package talentZone.RetoBackend.utils;

import org.springframework.beans.BeanUtils;
import talentZone.RetoBackend.dto.ProductDto;
import talentZone.RetoBackend.entity.Product;

public class AppUtils {
    public static ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product dtoToentity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
