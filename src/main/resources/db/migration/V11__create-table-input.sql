CREATE TABLE input (
    id VARCHAR(36) PRIMARY KEY,
    quantity INT NOT NULL,
    product_quality VARCHAR(50) NOT NULL, 
    entry_time TIMESTAMP NOT NULL,
    purchase_price DECIMAL(19, 2) NOT NULL,
    supplier VARCHAR(255) NOT NULL,
    stock_id VARCHAR(36) NOT NULL,
    
    CONSTRAINT fk_input_stock FOREIGN KEY (stock_id) REFERENCES stock(id) ON DELETE CASCADE
);
