-- add clerk_id to users table for linking with Clerk auth
ALTER TABLE users ADD COLUMN clerk_id VARCHAR(255) UNIQUE;

-- password and username are no longer mandatory (clerk handles auth)
ALTER TABLE users ALTER COLUMN password DROP NOT NULL;
ALTER TABLE users ALTER COLUMN username DROP NOT NULL;

CREATE INDEX idx_users_clerk_id ON users(clerk_id);
