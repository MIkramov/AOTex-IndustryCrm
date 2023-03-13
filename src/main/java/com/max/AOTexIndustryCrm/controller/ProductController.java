package com.max.AOTexIndustryCrm.controller;

import com.max.AOTexIndustryCrm.Dto.ProductDto;
import com.max.AOTexIndustryCrm.model.Product;
import com.max.AOTexIndustryCrm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;



    @GetMapping
    public HttpEntity<?> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
       return ResponseEntity.ok(productService.getProducts(page, size));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getproduct(@PathVariable Integer id){
        Product product = productService.getProduct(id);
        return ResponseEntity.status(product!=null?200:409).body(product);
    }

    @PostMapping
    public HttpEntity<?> createProduct(@RequestBody ProductDto productDto) {
        Product savedProduct = productService.createProduct(productDto);
        return ResponseEntity.status(201).body(savedProduct);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        Product editedProduct = productService.editProduct(id, productDto);
        return ResponseEntity.status(editedProduct!=null?202:409).body(editedProduct);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer id){
      boolean deleted = productService.deleteProduct(id);
      if (deleted)
      return ResponseEntity.noContent().build();
      return ResponseEntity.notFound().build();
    }

    }
