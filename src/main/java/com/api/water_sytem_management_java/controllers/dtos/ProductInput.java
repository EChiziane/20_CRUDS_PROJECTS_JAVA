package com.api.water_sytem_management_java.controllers.dtos;

public record ProductInput(
        String name,
        String description,
        Double price,
        Integer stockQuantity,
        String category
) {
    public Product toProduct() {
        return new Product(name, description, price, stockQuantity, category);
    }
}
