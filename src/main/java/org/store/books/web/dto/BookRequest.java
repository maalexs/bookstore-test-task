package org.store.books.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class BookRequest implements Serializable {

    @NotBlank(message = "Book title cannot be blank")
    private String title;

    private Long authorId;

    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;
}
