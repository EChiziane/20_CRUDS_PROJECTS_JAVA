package com.api.water_sytem_management_java.controllers;

import com.api.water_sytem_management_java.controllers.dtos.BookInput;
import com.api.water_sytem_management_java.controllers.dtos.BookOutput;
import com.api.water_sytem_management_java.models.Book;
import com.api.water_sytem_management_java.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookOutput>> getAllBooks() {
        List<BookOutput> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookInput bookInput) {
        Book book = bookInput.toBook();
        Book savedBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookOutput> updateBook(@PathVariable UUID id, @RequestBody BookInput bookInput) {
        Optional<BookOutput> updatedBook = bookService.updateBook(id, bookInput);
        return updatedBook.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}