package com.api.water_sytem_management_java.services;


import com.api.water_sytem_management_java.controllers.dtos.BookInput;
import com.api.water_sytem_management_java.controllers.dtos.BookOutput;
import com.api.water_sytem_management_java.models.Book;
import com.api.water_sytem_management_java.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookOutput> getAllBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "title")).stream()
                .map(Book::toBookOutput)
                .collect(Collectors.toList());
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Optional<BookOutput> updateBook(UUID id, BookInput input) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(input.title());
                    existingBook.setAuthor(input.author());
                    existingBook.setPublisher(input.publisher());
                    existingBook.setPublicationYear(input.publicationYear());
                    existingBook.setIsbn(input.isbn());
                    Book updatedBook = bookRepository.save(existingBook);
                    return mapToBookOutput(updatedBook);
                });
    }

    private BookOutput mapToBookOutput(Book book) {
        return book.toBookOutput();
    }

    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}
