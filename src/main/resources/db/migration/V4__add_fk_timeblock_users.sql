-- clean up old test data that has non-uuid user_ids
DELETE FROM time_block WHERE user_id !~ '^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$';

ALTER TABLE time_block ALTER COLUMN user_id TYPE UUID USING user_id::uuid;

ALTER TABLE time_block
    ADD CONSTRAINT fk_time_block_user
    FOREIGN KEY (user_id) REFERENCES users(id);
