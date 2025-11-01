package com.cleancode.ecommerce.stock.application.usecase;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;
import com.cleancode.ecommerce.stock.application.dto.CreateStockDto;
import com.cleancode.ecommerce.stock.application.dto.ListStockDto;
import com.cleancode.ecommerce.stock.domain.Stock;
import com.cleancode.ecommerce.stock.domain.repository.StockRepository;

public class CreateStockImpl implements CreateStock {

	private final StockRepository stockRepository;
	private final ProductRepository productRepository;

	public CreateStockImpl(ProductRepository productRepository, StockRepository stockRepository) {
		this.productRepository = productRepository;
		this.stockRepository = stockRepository;
	}

	@Override
	public ListStockDto execute(CreateStockDto dto) {
		Product product = productRepository.findById(dto.getProductId())
		.orElseThrow(() -> new IllegalDomainException
		("Product with id : " + dto.getProductId() + "not found"));

		Stock stock = dto.createStock();
		stockRepository.save(stock);
		
		return new ListStockDto(stock);
	}
}