package org.store.books.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.store.books.web.dto.BookActionRequest;
import org.store.books.web.dto.BookRequest;
import org.store.books.domain.service.BookService;
import org.store.books.persistence.entity.Book;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return ResponseEntity
                .ok()
                .body(allBooks);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(BookRequest bookRequest) {
        Book newBook = bookService.addBook(bookRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newBook);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") long bookId, BookRequest bookRequest) {
        Book updatedBook = bookService.updateBook(bookId, bookRequest);
        return ResponseEntity
                .ok()
                .body(updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> removeBook(long bookId) {
        bookService.removeBook(bookId);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/borrow")
    public ResponseEntity<Void> borrowBook(BookActionRequest bookActionRequest) {
        bookService.borrowBook(bookActionRequest);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/return")
    public ResponseEntity<Void> returnBook(BookActionRequest bookActionRequest) {
        bookService.returnBook(bookActionRequest);
        return ResponseEntity
                .ok()
                .build();
    }

}
