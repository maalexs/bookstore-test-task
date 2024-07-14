package org.store.books.domain.service;

import org.store.books.web.dto.BookActionRequest;
import org.store.books.web.dto.BookRequest;
import org.store.books.persistence.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book addBook(BookRequest bookRequest);
    Book updateBook(long bookId, BookRequest bookRequest);
    void removeBook(long bookId);
    Book borrowBook(BookActionRequest bookActionRequest);
    Book returnBook(BookActionRequest bookActionRequest);
}
