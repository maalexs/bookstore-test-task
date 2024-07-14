package org.store.books.domain.mapper;

import org.springframework.stereotype.Component;
import org.store.books.web.dto.BookRequest;
import org.store.books.persistence.entity.Book;

@Component
public class BookMapper {

    public Book toBookFromParams(Book book, BookRequest bookRequest) {
        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());
        book.setAuthorId(bookRequest.getAuthorId());
        return book;
    }
}
