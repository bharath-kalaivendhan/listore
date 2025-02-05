-- DROP DATABASE IF EXISTS lstore;
-- CREATE DATABASE lstore;

-- DROP TABLE IF EXISTS link;
CREATE TABLE link (
  id VARCHAR(36),
  title VARCHAR(256) NOT NULL,
  link VARCHAR(2048) NOT NULL,
  PRIMARY KEY(id)
);

--                         Table "public.link"
--  Column |          Type           | Collation | Nullable | Default
-- --------+-------------------------+-----------+----------+---------
--  id     | character varying(36)   |           | not null |
--  title  | character varying(256)  |           | not null |
--  link   | character varying(2048) |           | not null |
-- Indexes:
--     "link_pkey" PRIMARY KEY, btree (id)
-- Referenced by:
--     TABLE "link_tag_map" CONSTRAINT "fk_link_id" FOREIGN KEY (link_id) REFERENCES link(id) ON DELETE CASCADE
--     TABLE "thought" CONSTRAINT "fk_link_id" FOREIGN KEY (link_id) REFERENCES link(id) ON DELETE CASCADE


-- DROP TABLE IF EXISTS tag;
CREATE TABLE tag (
  id VARCHAR(36),
  tag VARCHAR(128) NOT NULL,
  PRIMARY KEY(id),
  UNIQUE (tag)
);

--                         Table "public.tag"
--  Column |          Type          | Collation | Nullable | Default
-- --------+------------------------+-----------+----------+---------
--  id     | character varying(36)  |           | not null |
--  tag    | character varying(128) |           | not null |
-- Indexes:
--     "tag_pkey" PRIMARY KEY, btree (id)
--     "tag_tag_key" UNIQUE CONSTRAINT, btree (tag)
-- Referenced by:
--    TABLE "link_tag_map" CONSTRAINT "fk_tag_id" FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE


-- DROP TABLE IF EXISTS link_tag_map;
CREATE TABLE link_tag_map (
  id VARCHAR(36),
  link_id VARCHAR(36) NOT NULL,
  tag VARCHAR(36) NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_link_id
   FOREIGN KEY(link_id)
      REFERENCES link(id)
        ON DELETE CASCADE,
  CONSTRAINT fk_tag_id
   FOREIGN KEY(tag)
      REFERENCES tag(id)
        ON DELETE CASCADE,
  UNIQUE (link_id, tag)
);

--                    Table "public.link_tag_map"
--  Column  |         Type          | Collation | Nullable | Default
-- ---------+-----------------------+-----------+----------+---------
--  id      | character varying(36) |           | not null |
--  link_id | character varying(36) |           | not null |
--  tag     | character varying(36) |           | not null |
-- Indexes:
--    "link_tag_map_pkey" PRIMARY KEY, btree (id)
--    "link_tag_map_link_id_tag_id_key" UNIQUE CONSTRAINT, btree (link_id, tag)
-- Foreign-key constraints:
--    "fk_link_id" FOREIGN KEY (link_id) REFERENCES link(id) ON DELETE CASCADE
--    "fk_tag" FOREIGN KEY (tag) REFERENCES tag(tag) ON DELETE CASCADE


-- DROP TABLE IF EXISTS thought;
CREATE TABLE thought (
  id VARCHAR(36),
  thought VARCHAR(4096) NOT NULL,
  link_id VARCHAR(36) NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_link_id
   FOREIGN KEY(link_id)
      REFERENCES link(id)
        ON DELETE CASCADE
);

--                        Table "public.thought"
--  Column  |          Type           | Collation | Nullable | Default
-- ---------+-------------------------+-----------+----------+---------
--  id      | character varying(36)   |           | not null |
--  thought | character varying(4096) |           | not null |
--  link_id | character varying(36)   |           | not null |
-- Indexes:
--     "thought_pkey" PRIMARY KEY, btree (id)
-- Foreign-key constraints:
--     "fk_link_id" FOREIGN KEY (link_id) REFERENCES link(id) ON DELETE CASCADE