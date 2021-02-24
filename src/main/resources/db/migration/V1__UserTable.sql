CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pgcrypto";


DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS journals;
DROP TABLE IF EXISTS records;

CREATE TABLE IF NOT EXISTS journals (
    journal_id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    user_id  UUID NOT NULL REFERENCES users ON DELETE CASCADE,
    journal_name VARCHAR(100) NOT NULL,
    createdAt TIMESTAMP NOT NULL DEFAULT NOW(),
    updatedAt TIMESTAMP NOT NULL DEFAULT NOW()
);


CREATE TABLE IF NOT EXISTS users (
     user_id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
     name VARCHAR(100) NOT NULL,
     email VARCHAR(100) NOT NULL,
     grantedAuthorities text ARRAY, --does this array need a specific number of values? i.e. array[4]
     username VARCHAR(100) NOT NULL,
     password VARCHAR(100) NOT NULL,
     isAccountNonExpired BOOLEAN NOT NULL,
     isAccountNonLocked BOOLEAN NOT NULL,
     isCredentialsNonExpired BOOLEAN NOT NULL,
     isEnabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS records (
    record_id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    journal_id UUID NOT NULL REFERENCES journals ON DELETE CASCADE,
    record_title VARCHAR(100) NOT NULL,
--    TEXT is a character datatype for variable length
    content TEXT,
    createdAt TIMESTAMP NOT NULL DEFAULT NOW(),
    updatedAt TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO users (name, email, grantedAuthorities, username, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled) VALUES
('drew', 'drew@gmail.com', 'ADMIN', 'drewski', '', true, true, true, true),
('seth', 'seth@gmail.com', 'ADMIN', 'sethy', '', true, true, true, true),
('oleg', 'oleg@gmail.com', 'READER', 'broleg', '', true, true, true, true),
('misha', 'misha@gmail.com', 'READER', 'mishamisha', '', false, true, false, false),
('ivan', 'ivan@gmail.com', 'READER', 'ivanko', '', true, true, true, true);

INSERT INTO users (user_id, name, email) VALUES
('8046aac5-1025-41ef-a7b8-a3ba3f266c8d','drew', 'drew@gmail.com');

INSERT INTO journals (journal_id, user_id, journal_name)
VALUES ('7a4b41bb-6824-4404-9beb-ab2ba10a978b', '8046aac5-1025-41ef-a7b8-a3ba3f266c8d', 'Full Stack Journal');

INSERT INTO records (journal_id, record_title, content) VALUES
('7a4b41bb-6824-4404-9beb-ab2ba10a978b', 'Day One','I am learning how to pronounced Olegs name'),
('7a4b41bb-6824-4404-9beb-ab2ba10a978b', 'Day Two', 'Seth and Oleg go hunting together'),
('7a4b41bb-6824-4404-9beb-ab2ba10a978b', 'The Russian Hacker', 'The rh is upon us...'),
('7a4b41bb-6824-4404-9beb-ab2ba10a978b', 'Day Five', 'Project is completed in only five days. #goodone');


--Run this query to check for correct configuration of the database--

select u.name, j.journal_name, r.record_title, r.content
from users u
join journals j
on u.user_id = j.user_id
join records r
on r.journal_id = j.journal_id;