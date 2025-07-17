INSERT INTO tb_role (id, name)
SELECT 1,
       'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM tb_role WHERE name = 'ADMIN');

INSERT INTO tb_role (id, name)
SELECT 2,
       'DOCTOR'
WHERE NOT EXISTS (SELECT 1 FROM tb_role WHERE name = 'DOCTOR');

INSERT INTO tb_role (id, name)
SELECT 3,
       'PATIENT'
WHERE NOT EXISTS (SELECT 1 FROM tb_role WHERE name = 'PATIENT');

INSERT INTO tb_role (id, name)
SELECT 4,
       'DOCTOR_PENDING'
WHERE NOT EXISTS (SELECT 1 FROM tb_role WHERE name = 'DOCTOR_PENDING');

INSERT INTO tb_user (email, password, username, address, city, cpf, name, number, state, version)
SELECT 'fabri@hackton.com',
       '$2a$10$eW8b1z5Z3f7Q9j1k1l1m1uO8d5h6s7f8g9h0i1j2k3l4m5n6o7p8q',
       'admin',
       '',
       '',
         '',
         'Admin User',
         '',
            '',
         0
WHERE NOT EXISTS (SELECT 1 FROM tb_user WHERE email = 'fabri@hackton.com' AND username = 'admin');