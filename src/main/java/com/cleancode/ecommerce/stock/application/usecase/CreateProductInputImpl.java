package com.cleancode.ecommerce.stock.application.usecase;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.event.EventPublisher;
import com.cleancode.ecommerce.event.ProductActivatedEvent;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.stock.application.dto.CreateInputStockDto;
import com.cleancode.ecommerce.stock.application.dto.ListStockDto;
import com.cleancode.ecommerce.stock.application.service.ProductActivationService;
import com.cleancode.ecommerce.stock.application.service.ProductPriceService;
import com.cleancode.ecommerce.stock.domain.Stock;
import com.cleancode.ecommerce.stock.domain.repository.StockRepository;

public class CreateProductInputImpl implements CreateProductInput{

	private final StockRepository repository;
	private final ProductRepository productRepository;
	private final ProductActivationService service;
	private final ProductPriceService productPriceService;
    private final EventPublisher eventPublisher;
	
	public CreateProductInputImpl(StockRepository repository, ProductRepository productRepository,ProductActivationService service, ProductPriceService productPriceService, EventPublisher eventPublisher) {
		this.repository = repository;
		this.productRepository = productRepository;
		this.service = service;
		this.productPriceService = productPriceService;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public ListStockDto execute (CreateInputStockDto dto) {
		Stock stock = repository.getStock(dto.getProductId());
		stock.addProductInput(dto.getQuantity(), dto.getProductQuality(), new Price(dto.getPurchasePrice(), dto.getCoin()), dto.getSupplier());

		Product product = productRepository.listProduct(dto.getProductId())
	    .orElseThrow(() -> new IllegalDomainException("Product with id : " + dto.getProductId() + "not found"));
		
		product = service.activateProductIfStockAvailable(product, stock);
		product = productPriceService.productPriceService(product, stock);
		
		eventPublisher.publish(new ProductActivatedEvent(product.getProductId().getProductId(), product.isActive()));
		repository.create(stock);
		productRepository.create(product);
		
		return new ListStockDto(stock);
	}
}