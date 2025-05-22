package com.store.book.request.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingBasket {

	private List<SelectedBook> selectedBooks;
}
