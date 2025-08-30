package com.api.water_sytem_management_java.services;


import com.api.water_sytem_management_java.controllers.dtos.ProductInput;
import com.api.water_sytem_management_java.controllers.dtos.ProductOutput;
import com.api.water_sytem_management_java.models.Product;
import com.api.water_sytem_management_java.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductOutput> getAllProducts() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(Product::toProductOutput)
                .collect(Collectors.toList());
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Optional<ProductOutput> updateProduct(UUID id, ProductInput input) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(input.name());
                    existingProduct.setDescription(input.description());
                    existingProduct.setPrice(input.price());
                    existingProduct.setStockQuantity(input.stockQuantity());
                    existingProduct.setCategory(input.category());
                    Product updatedProduct = productRepository.save(existingProduct);
                    return mapToProductOutput(updatedProduct);
                });
    }

    private ProductOutput mapToProductOutput(Product product) {
        return product.toProductOutput();
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}