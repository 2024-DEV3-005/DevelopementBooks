package com.store.book.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.store.book.request.model.ShoppingBasket;
import com.store.book.service.PricingService;
import com.store.book.service.model.Book;

@Service("pricingService")
public class PricingServiceImpl implements PricingService {

	@Override
	public BigDecimal getPrice(ShoppingBasket basket) {
		return getTotalPrice(basket);
	}
	
	
	
	private BigDecimal getTotalPrice(ShoppingBasket basket) {
        return basket.getSerialNumberOfBook().stream().map(slNo->Book.fetchBySerialNo(slNo).getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}