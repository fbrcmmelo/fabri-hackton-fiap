CREATE TABLE IF NOT EXISTS tb_role (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tb_user (
    dtype VARCHAR(50) DEFAULT 'USER',
    id INT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    address VARCHAR(255),
    city VARCHAR(100),
    cpf VARCHAR(14),
    crm VARCHAR(20),
    specialization VARCHAR(100),
    name VARCHAR(100),
    number INT,
    state VARCHAR(50),
    version INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS tb_user_role (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES tb_user(id),
    FOREIGN KEY (role_id) REFERENCES tb_role(id)
);

INSERT INTO tb_role (id, name)
SELECT 1,'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM tb_role WHERE name = 'ADMIN');

INSERT INTO tb_role (id, name)
SELECT 2,'DOCTOR'
WHERE NOT EXISTS (SELECT 1 FROM tb_role WHERE name = 'DOCTOR');

INSERT INTO tb_role (id, name)
SELECT 3,'PATIENT'
WHERE NOT EXISTS (SELECT 1 FROM tb_role WHERE name = 'PATIENT');

INSERT INTO tb_role (id, name)
SELECT 4,'DOCTOR_PENDING'
WHERE NOT EXISTS (SELECT 1 FROM tb_role WHERE name = 'DOCTOR_PENDING');

INSERT INTO tb_user (dtype,id, email, password, username, address, city, cpf, name, number, state, version)
SELECT 'USER',
    1,
    'fabri@hackton.com',
       '$2a$10$eW8b1z5Z3f7Q9j1k1l1m1uO8d5h6s7f8g9h0i1j2k3l4m5n6o7p8q',
       'admin',
       'Rua',
       'city',
         '123.456.789-00',
         'Admin User',
         0000,
            'state',
         0
WHERE NOT EXISTS (SELECT 1 FROM tb_user WHERE email = 'fabri@hackton.com' AND username = 'admin');

INSERT INTO tb_user_role (user_id, role_id)
SELECT 1, 1
WHERE NOT EXISTS (SELECT 1 FROM tb_user_role WHERE user_id = 1 AND role_id = 1);