--DROP EXTENSION 'uuid-ossp';

CREATE TABLE users (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email varchar(100) NOT NULL
);

