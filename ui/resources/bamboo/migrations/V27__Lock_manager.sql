CREATE TABLE named_lock
(
  name VARCHAR(512) PRIMARY KEY NOT NULL,
  owner VARCHAR(512) NOT NULL,
  checkin_time BIGINT NOT NULL,
  acquire_time BIGINT NOT NULL
);

CREATE INDEX named_lock_owner_name_index ON named_lock (owner, name);