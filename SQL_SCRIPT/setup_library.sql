CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO users (name, email) VALUES
('Rafee', 'rafee@gmail.com'), ('Ben', 'ben@yahoo.com'), ('Ted', 'ted@gmail.com');

SELECT * FROM users;


CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    user_id INTEGER REFERENCES users(id),
    checked_out BOOLEAN DEFAULT FALSE
);

INSERT INTO books (title, author) VALUES
('Harry Potter and the Sorcerer''s Stone', 'JK Rowling'),
('Harry Potter and the Chamber of Secrets', 'JK Rowling'),
('The Count of Monte Cristo', 'Alexander Dumas'),
('1984', 'George Orwell'),
('Musashi', 'Eiji Yoshikawa');


SELECT * FROM books;