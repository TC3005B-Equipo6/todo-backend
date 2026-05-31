INSERT INTO user (id, full_name, email, role, active, firebase_uuid, created_at, updated_at)
VALUES ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Diego Herrera', 'diego@example.com', 'USER', true, 'aTYXytcEIje4oT6nrHmyW7tB65N2', NOW(), NOW());

INSERT INTO list (id, name, owner_id)
VALUES('11111111-1111-1111-1111-555555555555', 'Default', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890');

INSERT INTO todo (id, title, description, completed, dueDate, created_at, list_id, owner_id)
VALUES ('11111111-2222-3333-4444-555555555555', 'Comprar despensa', 'Leche, huevos, pan y frutas', false, NOW(),NOW(), '11111111-1111-1111-1111-555555555555', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'),
       ('66666666-7777-8888-9999-aaaaaaaaaaaa', 'Estudiar Quarkus', 'Revisar documentación de Panache y CDI', false, NOW(), NOW(), '11111111-1111-1111-1111-555555555555', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890'),
       ('bbbbbbbb-cccc-dddd-eeee-ffffffffffff', 'Hacer ejercicio', 'Correr 30 minutos en la mañana', true, NOW(),NOW(), '11111111-1111-1111-1111-555555555555', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890');
