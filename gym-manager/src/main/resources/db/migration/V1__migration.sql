CREATE TABLE IF NOT EXISTS trainee (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    subscription_type VARCHAR(50) NOT NULL,
    expiration_date TIMESTAMP NOT NULL
);