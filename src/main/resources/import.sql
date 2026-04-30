INSERT INTO users (id, full_name, email, role, active, firebase_uuid, created_at, updated_at)
VALUES ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Omar Perez', 'omar@example.com', 'USER', true, 'firebase-uid-001', NOW(), NOW());

INSERT INTO todos (id, title, description, completed, created_at)
VALUES ('11111111-2222-3333-4444-555555555555', 'Comprar despensa', 'Leche, huevos, pan y frutas', false, NOW()),
       ('66666666-7777-8888-9999-aaaaaaaaaaaa', 'Estudiar Quarkus', 'Revisar documentación de Panache y CDI', false, NOW()),
       ('bbbbbbbb-cccc-dddd-eeee-ffffffffffff', 'Hacer ejercicio', 'Correr 30 minutos en la mañana', true, NOW());
