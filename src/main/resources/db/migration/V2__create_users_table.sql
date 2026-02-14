-- Create users table
CREATE TABLE users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER'
);

-- Create index on username for faster lookups
CREATE INDEX idx_users_username ON users(username);
