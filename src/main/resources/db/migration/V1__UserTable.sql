CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email varchar(100) NOT NULL
);

INSERT INTO users (id, name, email) VALUES
(uuid_generate_v4(), 'drew', 'drew@gmail.com'),
(uuid_generate_v4(), 'seth', 'seth@gmail.com'),
(uuid_generate_v4(), 'oleg', 'oleg@gmail.com'),
(uuid_generate_v4(), 'misha', 'misha@gmail.com'),
(uuid_generate_v4(), 'ivan', 'ivan@gmail.com');
