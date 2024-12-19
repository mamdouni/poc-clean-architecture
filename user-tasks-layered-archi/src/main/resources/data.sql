INSERT INTO users (username, email) VALUES
                                                  ('john_doe', 'john.doe@example.com'),
                                                  ('jane_smith', 'jane.smith@example.com'),
                                                  ('alice_jones', 'alice.jones@example.com'),
                                                  ('bob_brown', 'bob.brown@example.com');

INSERT INTO tasks (user_id, title, description, due_date, status) VALUES
                                                                      (1, 'Task 1', 'Description for Task 1', '2023-12-01', 'Pending'),
                                                                      (1, 'Task 2', 'Description for Task 2', '2023-12-05', 'Completed'),
                                                                      (2, 'Task 3', 'Description for Task 3', '2023-12-10', 'In Progress'),
                                                                      (2, 'Task 4', 'Description for Task 4', '2023-12-15', 'Pending'),
                                                                      (3, 'Task 5', 'Description for Task 5', '2023-12-20', 'Completed'),
                                                                      (3, 'Task 6', 'Description for Task 6', '2023-12-25', 'In Progress'),
                                                                      (4, 'Task 7', 'Description for Task 7', '2023-12-30', 'Pending'),
                                                                      (4, 'Task 8', 'Description for Task 8', '2024-01-05', 'Completed');