CREATE TABLE tb_replacement (
    voucher_id   VARCHAR(36) PRIMARY KEY,
    message      VARCHAR(255) NOT NULL COMMENT 'Coupon related message',
    emission     DATE NOT NULL COMMENT 'Coupon issue date',
    type_voucher VARCHAR(50) NOT NULL COMMENT 'Type of voucher',
    customer_id  VARCHAR(36) NOT NULL COMMENT 'Reference to customer (no FK constraint)',
    discount     TINYINT UNSIGNED NOT NULL COMMENT 'Coupon discount percentage',
    user_id      VARCHAR(36) NOT NULL COMMENT 'Reference to the administrator who created the voucher',
    
    CONSTRAINT fk_replacement_adm FOREIGN KEY (user_id)
        REFERENCES tb_adm(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE INDEX idx_replacement_customer_id ON tb_replacement(customer_id);
CREATE INDEX idx_replacement_user_id ON tb_replacement(user_id);