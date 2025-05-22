package com.store.book.service;

import com.store.book.service.model.Amount;
import com.store.book.service.model.Basket;

public interface PricingService {

	Amount getPrice(Basket basket);

}
