package org.store.books.web.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookActionRequest implements Serializable {

    private long bookId;
    private long userId;
}
