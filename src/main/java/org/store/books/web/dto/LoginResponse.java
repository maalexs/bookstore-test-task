package org.store.books.web.dto;

import lombok.Data;

@Data
public class LoginResponse {

   private final String email;
   private final String token;
}