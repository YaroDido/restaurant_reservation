CREATE TABLE reservations (
     id INT AUTO_INCREMENT PRIMARY KEY,
     customer_name VARCHAR(255),
     quantity_of_customers INT,
     table_number INT,
     reservation_date_time DATETIME
);