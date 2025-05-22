package com.store.book.service;

import com.store.book.service.model.Basket;
import com.store.book.service.model.OrderSummary;

public interface PricingService {

	OrderSummary getOrderSummary(Basket basket);

}
