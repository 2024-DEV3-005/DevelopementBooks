package com.store.book.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class BookQuanityDetail  {
    Book book;
    Integer quantity;
}
