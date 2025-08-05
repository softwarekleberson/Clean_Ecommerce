CREATE TABLE output (
    id VARCHAR(36) PRIMARY KEY,
    orders_id VARCHAR(36) NOT NULL,
    product_id VARCHAR(36) NOT NULL,
    quantity INT NOT NULL,
    stock_id VARCHAR(36) NOT NULL,
    
    CONSTRAINT fk_output_stock FOREIGN KEY (stock_id) REFERENCES stock(id) ON DELETE CASCADE,
	CONSTRAINT fk_output_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);
