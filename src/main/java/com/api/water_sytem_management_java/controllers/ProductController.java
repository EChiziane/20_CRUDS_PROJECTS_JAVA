package com.api.water_sytem_management_java.controllers;


import com.api.water_sytem_management_java.controllers.dtos.ProductInput;
import com.api.water_sytem_management_java.controllers.dtos.ProductOutput;
import com.api.water_sytem_management_java.models.Product;
import com.api.water_sytem_management_java.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductOutput>> getAllProducts() {
        List<ProductOutput> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductInput productInput) {
        Product product = productInput.toProduct();
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOutput> updateProduct(@PathVariable UUID id, @RequestBody ProductInput productInput) {
        Optional<ProductOutput> updatedProduct = productService.updateProduct(id, productInput);
        return updatedProduct.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}