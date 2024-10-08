CREATE TABLE IF NOT EXISTS users (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email varchar NOT NULL,
    name varchar,
    CONSTRAINT uq_email UNIQUE(email)
);

CREATE TABLE IF NOT EXISTS requests (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    description varchar NOT NULL,
    owner_id INTEGER REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    created TIMESTAMP
);

CREATE TABLE IF NOT EXISTS items (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar NOT NULL,
    description varchar NOT NULL,
    available boolean NOT NULL,
    owner_id INTEGER REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    request_id INTEGER REFERENCES requests (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS bookings (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    item_id INTEGER REFERENCES items (id) ON DELETE CASCADE ON UPDATE CASCADE,
    booker_id INTEGER REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    status varchar(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS comments (
    id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    text varchar NOT NULL,
    item_id INTEGER REFERENCES items (id) ON DELETE CASCADE ON UPDATE CASCADE,
    author_id INTEGER REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    created TIMESTAMP
);