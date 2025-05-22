package com.store.book.service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Basket {

    List<Book> distinctBooks;

}
