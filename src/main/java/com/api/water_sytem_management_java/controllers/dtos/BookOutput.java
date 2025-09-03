package com.api.water_sytem_management_java.controllers.dtos;


import java.util.UUID;

public record BookOutput(
        UUID id,
        String title,
        String author,
        String publisher,
        Integer publicationYear,
        String isbn
) {
}
