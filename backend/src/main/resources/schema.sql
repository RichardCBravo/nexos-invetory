-- Drop tables if they exist to avoid conflicts
DROP TABLE IF EXISTS merchandise;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

-- Create roles table
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Create users table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    entry_date TIMESTAMP NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Create merchandise table
CREATE TABLE merchandise (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL UNIQUE,
    quantity INT NOT NULL,
    entry_date DATE NOT NULL,
    updated_date TIMESTAMP,
    created_by_user_id BIGINT NOT NULL,
    update_by_user_id BIGINT,
    FOREIGN KEY (created_by_user_id) REFERENCES users(id),
    FOREIGN KEY (update_by_user_id) REFERENCES users(id)
);

-- Add indexes for better performance
CREATE INDEX idx_merchandise_product_name ON merchandise(product_name);
CREATE INDEX idx_users_role_id ON users(role_id);
