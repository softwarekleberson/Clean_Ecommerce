package com.cleancode.ecommerce.order.application.useCase;

import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.order.application.dtos.input.CreateCartDto;
import com.cleancode.ecommerce.order.application.dtos.output.CartDto;
import com.cleancode.ecommerce.order.application.service.ReservationResultDto;
import com.cleancode.ecommerce.order.application.service.ValidateProductHasStock;
import com.cleancode.ecommerce.order.application.useCase.contract.AddProductToCart;
import com.cleancode.ecommerce.order.domain.cart.Cart;
import com.cleancode.ecommerce.order.domain.cart.CartId;
import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.order.domain.cart.UrlProduct;
import com.cleancode.ecommerce.order.domain.cart.repository.CartRepository;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.product.domain.repository.ProductRepository;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.stock.domain.Quantity;
import com.cleancode.ecommerce.stock.domain.ReservationId;
import com.cleancode.ecommerce.stock.domain.Stock;
import com.cleancode.ecommerce.stock.domain.repository.StockRepository;

public class AddProductToCartImpl implements AddProductToCart {

	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;
	private final StockRepository stockRepository;
	private final CartRepository cartRepository;
	private final ValidateProductHasStock validateProduct;

	public AddProductToCartImpl(CustomerRepository customerRepository, ProductRepository productRepository,
			StockRepository stockRepository, CartRepository cartRepository, ValidateProductHasStock validateProduct) {
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
		this.stockRepository = stockRepository;
		this.cartRepository = cartRepository;
		this.validateProduct = validateProduct;
	}

	@Override
	public CartDto execute(CreateCartDto dto) {

		Customer customer = findCustomer(dto);
		Product product = findProduct(dto);
		Stock stock = findStock(dto, product);
		Cart cart = getCartOrCreate(dto);

		ReservationResultDto stockAfterReservation = 
		validateProduct.reserve(stock, dto.getQuantity(),
		customer.getId().getValue(),
		cart.getCartId().getCartId());
		
		cart.addProductToCart(
				new CartItemId(),
				new ProductId(product.getProductId().getProductId()),
				new Name(product.getName().getName()),
				new UrlProduct(product.getMidia().get(0).getUrl()),
				new Quantity(dto.getQuantity()),
				new Price(product.getPrice().getPrice(), product.getPrice().getCoin()),
				new ReservationId(stockAfterReservation.reservationId()));

		stockRepository.save(stockAfterReservation.stock());
		cartRepository.save(cart);
		return new CartDto(cart);
	}

	private Cart getCartOrCreate(CreateCartDto dto) {
		Cart cart = cartRepository.getCartCustomer(dto.getCustomerId()).orElseGet(() -> {
			Cart newCart = new Cart(new CartId(UUID.randomUUID().toString()), new CustomerId(dto.getCustomerId()));
			cartRepository.save(newCart);
			return newCart;
		});
		return cart;
	}

	private Stock findStock(CreateCartDto dto, Product product) {
		Stock stock = stockRepository.getStock(product.getProductId().getProductId())
				.orElseThrow(() -> new IllegalDomainException("Stock with id :" + dto.getProductId() + " not found "));
		return stock;
	}

	private Product findProduct(CreateCartDto dto) {
		Product product = productRepository.findById(dto.getProductId()).orElseThrow(
				() -> new IllegalArgumentException("Product with id : " + dto.getProductId() + " not found"));
		return product;
	}

	private Customer findCustomer(CreateCartDto dto) {
		Customer customer = customerRepository.getCustomerById(dto.getCustomerId()).orElseThrow(
				() -> new IllegalArgumentException("Customer with id : " + dto.getCustomerId() + " not found"));
		return customer;
	}
}