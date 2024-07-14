package org.store.books.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private long id;

    private String title;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Author author;

    private String isbn;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "author_id")
    private long authorId;
}
