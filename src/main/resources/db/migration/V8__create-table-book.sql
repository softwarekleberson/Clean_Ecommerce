CREATE TABLE book (
    id VARCHAR(255) PRIMARY KEY,
    synopsis TEXT NOT NULL,
    page INTEGER NOT NULL,
    author VARCHAR(255) NOT NULL,
    edition VARCHAR(255) NOT NULL,
    isbn VARCHAR(25) UNIQUE NOT NULL,
    category_book VARCHAR(255) NOT NULL,
    height DECIMAL(5,2) NOT NULL,
    width DECIMAL(5,2) NOT NULL,
    length DECIMAL(5,2) NOT NULL,
    weight DECIMAL(5,2) NOT NULL,
    publisher_date DATE NOT NULL,
    
    CONSTRAINT uq_book_isbn UNIQUE (isbn),
    CONSTRAINT fk_book_product FOREIGN KEY (id) REFERENCES product(id)
);
