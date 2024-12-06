CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(100) NOT NULL
);

CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY,
                       user_id INTEGER NOT NULL,
                       title VARCHAR(100) NOT NULL,
                       description TEXT,
                       due_date TIMESTAMP,
                       status VARCHAR(20),
                       FOREIGN KEY (user_id) REFERENCES users(id)
);
