-- Insert roles
INSERT INTO roles (name) VALUES
('Asesor de ventas'),
('Administrador'),
('Soporte');

-- Insert users (with entry dates from recent years)
INSERT INTO users (name, age, entry_date, role_id) VALUES
('Juan Pérez', 32, '2023-06-15 09:00:00', 1),       -- Asesor de ventas
('María García', 28, '2022-03-10 08:30:00', 2),     -- Administrador
('Carlos Rodríguez', 35, '2021-11-22 10:15:00', 3), -- Soporte
('Ana Martínez', 30, '2022-09-05 08:00:00', 1),     -- Asesor de ventas
('Luis López', 40, '2020-07-18 09:30:00', 2),       -- Administrador
('Elena Sánchez', 27, '2023-01-30 08:45:00', 3),    -- Soporte
('Pablo Ramírez', 33, '2021-05-12 09:15:00', 1);    -- Asesor de ventas

-- Insert merchandise (car parts with appropriate dates)
INSERT INTO merchandise (product_name, quantity, entry_date, updated_date, created_by_user_id, update_by_user_id) VALUES
('Amortiguadores Monroe', 25, '2024-01-15', '2024-01-15 14:30:00', 2, NULL),
('Baterías Bosch S4', 30, '2024-02-10', '2024-03-05 11:20:00', 2, 5),
('Filtro de Aceite FRAM PH7317', 85, '2024-02-20', '2024-02-20 09:45:00', 1, NULL),
('Pastillas de Freno Brembo', 40, '2024-03-05', '2024-03-05 10:30:00', 4, NULL),
('Alternador Denso 120A', 12, '2024-03-18', '2024-04-10 16:15:00', 2, 3),
('Radiador Valeo', 18, '2024-04-02', '2024-04-02 13:40:00', 7, NULL),
('Kit de Embrague Sachs', 15, '2024-04-15', '2024-05-01 09:10:00', 5, 2),
('Bomba de Agua Gates', 22, '2024-05-05', '2024-05-05 11:55:00', 3, NULL),
('Bujías NGK Iridium', 60, '2024-05-20', '2024-05-20 14:20:00', 4, NULL),
('Correa de Distribución Continental', 28, '2024-06-10', '2024-06-10 10:05:00', 1, NULL);
