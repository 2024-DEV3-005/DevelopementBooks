package com.store.book.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.store.book.service.PricingService;
import com.store.book.service.model.Book;

@Service("pricingService")
public class PricingServiceImpl implements PricingService {

	@Override
	public BigDecimal getPrice(String serialNumber) {
		return Book.fetchBySerialNo(serialNumber).getPrice();
	}
}