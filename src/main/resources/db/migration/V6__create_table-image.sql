CREATE TABLE image (
    id VARCHAR(255) PRIMARY KEY,
    url TEXT NOT NULL,
    description TEXT NOT NULL,
    product_id VARCHAR(255),
    CONSTRAINT fk_image_product FOREIGN KEY (product_id) REFERENCES product(id)
);
