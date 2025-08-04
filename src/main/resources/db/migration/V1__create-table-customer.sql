CREATE TABLE customer (
    id VARCHAR(36) PRIMARY KEY, 
    cpf VARCHAR(15) NOT NULL,
    name VARCHAR(255) NOT NULL, 
    birth DATE NOT NULL, 
    password VARCHAR(255) NOT NULL, 
    gender ENUM('MALE', 'WOMAN', 'NOT_INFORMED') NOT NULL,
    ddd VARCHAR(2) NOT NULL, 
    phone VARCHAR(9) NOT NULL,
    type_phone ENUM('MOBILE', 'WHATSAPP', 'FIXED') NOT NULL,
    email VARCHAR(255) NOT NULL,
    
    CONSTRAINT uq_customer_cpf UNIQUE (cpf),
    CONSTRAINT uq_customer_email UNIQUE (email)
);
