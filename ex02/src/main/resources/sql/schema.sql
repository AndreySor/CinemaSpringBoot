CREATE TABLE IF NOT EXISTS cinema_halls
(
    hall_id bigserial PRIMARY KEY,
    serial_number INTEGER UNIQUE NOT NULL,
    seats_number INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS cinema_films
(
    film_id bigserial PRIMARY KEY,
    title VARCHAR(64) UNIQUE NOT NULL,
    release_year INTEGER NOT NULL,
    age_restrictions INTEGER NOT NULL,
    description TEXT,
    poster TEXT
);

CREATE TABLE IF NOT EXISTS cinema_sessions
(
    id bigserial PRIMARY KEY,
    ticket_cost INTEGER NOT NULL,
    session_date TIMESTAMP WITHOUT TIME ZONE,
    hall_id INTEGER REFERENCES cinema_halls (hall_id),
    film_id INTEGER REFERENCES cinema_films (film_id)
);

CREATE TABLE IF NOT EXISTS users
(
    id    BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    email VARCHAR(64) UNIQUE NOT NULL,
    phone VARCHAR(64) NOT NULL,
    login VARCHAR(64) UNIQUE NOT NULL,
    password TEXT NOT NULL,
    avatar VARCHAR(64),
    user_role VARCHAR(64) NOT NULL,
    is_confirmed BOOLEAN DEFAULT false,
    verification_code varchar(64) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS cinema_authentications
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL REFERENCES users (id),
    authentication_date TIMESTAMP WITHOUT TIME ZONE,
    ip_address VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS cinema_messages
(
    id BIGSERIAL PRIMARY KEY,
    message TEXT NOT NULL,
    author_id BIGSERIAL REFERENCES users (id),
    message_date TIMESTAMP WITHOUT TIME ZONE,
    film_id BIGSERIAL REFERENCES cinema_films (film_id)
);

CREATE TABLE IF NOT EXISTS persistent_logins (
    username varchar(100) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
);