CREATE TABLE reservation (
    id VARCHAR(36) PRIMARY KEY,
    cart_id VARCHAR(36) ,
    customer_id VARCHAR(36) NOT NULL,
    quantity INT NOT NULL,
    reservation_time TIMESTAMP NOT NULL,
    reserve_status VARCHAR(50) NOT NULL, 
    stock_id VARCHAR(36) NOT NULL,
    
    CONSTRAINT fk_reservation_stock FOREIGN KEY (stock_id) REFERENCES stock(id) ON DELETE CASCADE,
	CONSTRAINT fk_reservation_customer FOREIGN KEY (customer_id) REFERENCES tb_customer(customer_id) ON DELETE CASCADE
);