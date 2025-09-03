package com.api.water_sytem_management_java.controllers.dtos;

public record BookInput(
        String title,
        String author,
        String publisher,
        Integer publicationYear,
        String isbn
) {
    public Book toBook() {
        return new Book(title, author, publisher, publicationYear, isbn);
    }
}