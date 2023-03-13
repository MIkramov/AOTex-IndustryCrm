package com.max.AOTexIndustryCrm.service;

import com.max.AOTexIndustryCrm.Dto.ProductDto;
import com.max.AOTexIndustryCrm.model.Product;
import com.max.AOTexIndustryCrm.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product createProduct(ProductDto productDto){
        Product product = Product.builder()
                .modelName(productDto.getModelName())
                .color(productDto.getColor())
                .sortType(productDto.getSortType())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();
        Product savedProduct = productRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return savedProduct;
    }


    public List<Product> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return   productPage.getContent();
        }


    public Product getProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent())
            return optionalProduct.get();
        return null;
    }

    public Product editProduct(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setColor(product.getColor());
            product.setDescription(product.getDescription());
            product.setPrice(productDto.getPrice());
            product.setModelName(product.getModelName());
            product.setSortType(product.getSortType());
            return productRepository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(Integer id) {

        try {


            productRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }

        }
}

