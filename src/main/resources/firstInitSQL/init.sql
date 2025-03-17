INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role_name) VALUES ('ROLE_USER');

INSERT INTO users (first_name, last_name, email, age, password)
VALUES ('a', 'a', 'admin', 100,'$2a$12$vb6goF0132r1cOIlZkF9/.HTduPaosiEgEtVkTH1tiiUXj3D65sVC');

INSERT INTO user_role (user_id, role_id)
VALUES (
           (SELECT id FROM users WHERE email = 'admin'),
           (SELECT id FROM roles WHERE role_name = 'ROLE_ADMIN')
       );
# admin/admin
