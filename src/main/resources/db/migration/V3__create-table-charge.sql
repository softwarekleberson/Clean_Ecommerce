CREATE TABLE charge (
	db_id BIGINT PRIMARY KEY,
	
	CONSTRAINT fk_charge_address
	FOREIGN KEY (db_id)
	REFERENCES address(db_id)
	ON DELETE CASCADE
);