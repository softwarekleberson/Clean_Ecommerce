CREATE TABLE address (
    db_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id BINARY(16) NOT NULL,
    receiver VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(50) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    observation VARCHAR(255),
    street_type VARCHAR(255) NOT NULL,
    type_residence VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    customer_id BINARY(16), 

    CONSTRAINT fk_address_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer(id)
    	ON DELETE CASCADE
);
