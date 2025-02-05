-- insert multiple rows query with ignore in postgresql
INSERT INTO tag (id, tag)
VALUES ("<UUIDV4>", "<Tag Name>"),
("<UUIDV4>", "<Tag Name>")
ON CONFLICT (tag) DO NOTHING;
-- Note: give comma separated column's name inside round brackets to ignore conflicts on that column

-- Example query
INSERT INTO tag (id, tag)
VALUES (uuid_generate_v4(), 'test-1'),
(uuid_generate_v4(), 'test-2'),
(uuid_generate_v4(), 'test-6'),
(uuid_generate_v4(), 'test-7')
ON CONFLICT (tag) DO NOTHING;

-- Note: to use uuid_generate_v4() function, we need to have "uuid-ossp" extension.