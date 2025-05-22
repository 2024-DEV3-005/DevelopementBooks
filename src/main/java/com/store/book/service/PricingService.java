package com.store.book.service;

import com.store.book.request.model.ShoppingBasket;
import com.store.book.service.model.Amount;

public interface PricingService {

	Amount getPrice(ShoppingBasket basket);

}
