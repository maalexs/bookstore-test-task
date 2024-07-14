package org.store.books.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.store.books.domain.mapper.BookMapper;
import org.store.books.infrastructure.exception.InvalidBookLinkException;
import org.store.books.infrastructure.exception.MissingBookException;
import org.store.books.web.dto.BookActionRequest;
import org.store.books.web.dto.BookRequest;
import org.store.books.domain.service.BookService;
import org.store.books.persistence.entity.Book;
import org.store.books.persistence.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasicBookService implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper; // can be replaced with mapstruct, for example

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(BookRequest bookRequest) {
        Book book = new Book();
        bookMapper.toBookFromParams(book, bookRequest);
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book updateBook(long bookId, BookRequest bookRequest) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (bookOpt.isEmpty()) {
            throw new IllegalArgumentException(String.format("Book with id %s not found", bookId));
        }

        Book book = bookOpt.get();
        bookMapper.toBookFromParams(book, bookRequest);
        bookRepository.save(book);

        return book;
    }

    @Override
    public void removeBook(long bookId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);

        if (bookOpt.isEmpty()) {
            throw new IllegalArgumentException(String.format("Book with id %s not found", bookId));
        }

        bookRepository.delete(bookOpt.get());
    }

    @Override
    public Book borrowBook(BookActionRequest bookActionRequest) {
        Optional<Book> bookOpt = bookRepository.findById(bookActionRequest.getBookId());

        if (bookOpt.isEmpty()) {
            throw new MissingBookException(String.format("Book with id %s is not exist", bookActionRequest.getBookId()));
        }

        Book borrowedBook = bookOpt.get();

        if (borrowedBook.getUserId() != bookActionRequest.getBookId()) {
            throw new InvalidBookLinkException(String.format("Book with id %s linked to another user",
                    bookActionRequest.getBookId()));
        }

        borrowedBook.setUserId(bookActionRequest.getUserId());
        bookRepository.save(borrowedBook);

        return borrowedBook;
    }

    @Override
    public Book returnBook(BookActionRequest bookActionRequest) {
        Optional<Book> bookOpt = bookRepository.findById(bookActionRequest.getBookId());

        if (bookOpt.isEmpty()) {
            throw new MissingBookException(String.format("Book with id %s is not exist", bookActionRequest.getBookId()));
        }

        Book returnedBook = bookOpt.get();

        if (returnedBook.getUserId() != bookActionRequest.getBookId()) {
            throw new InvalidBookLinkException(String.format("Book with id %s linked to another user",
                    bookActionRequest.getBookId()));
        }

        returnedBook.setUserId(null);
        bookRepository.save(returnedBook);

        return returnedBook;
    }
}
