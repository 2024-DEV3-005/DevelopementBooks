package com.store.book.service;

import java.math.BigDecimal;

import com.store.book.request.model.ShoppingBasket;

public interface PricingService {
	
	BigDecimal getPrice (ShoppingBasket basket);

}
