CREATE TABLE stock (
    id VARCHAR(36) PRIMARY KEY,
    product_id VARCHAR(36) NOT NULL,
    total_quantity INT,
    quantity_available INT,
    
    CONSTRAINT fk_stock_product FOREIGN KEY (product_id) REFERENCES product(id),
	CONSTRAINT uq_stock_product UNIQUE (product_id)
);
