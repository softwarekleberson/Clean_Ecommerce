CREATE TABLE delivery (
    db_id BIGINT PRIMARY KEY,
    delivery_phrase VARCHAR(255),

    CONSTRAINT fk_delivery_address
    FOREIGN KEY (db_id)
    REFERENCES address(db_id)
    ON DELETE CASCADE
);
