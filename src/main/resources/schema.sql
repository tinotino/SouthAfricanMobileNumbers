DROP TABLE IF EXISTS phone_numbers;

CREATE TABLE phone_numbers(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    external_id VARCHAR(100) NOT NULL,
    "number"  VARCHAR(20) NOT NULL,
    state     VARCHAR(50) NOT NULL,
    error_type     VARCHAR(50) NOT NULL,
    original  VARCHAR(250) DEFAULT NULL
);
