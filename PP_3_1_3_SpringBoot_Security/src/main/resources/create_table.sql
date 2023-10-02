CREATE TABLE IF NOT EXISTS users
(
    `id`
    BIGINT
    NOT
    NULL
    AUTO_INCREMENT,
    `name`
    VARCHAR
(
    45
) NOT NULL,
    `last_name` VARCHAR
(
    45
) NOT NULL,
    `age` INT NOT NULL,
    `email` VARCHAR
(
    45
) NOT NULL,
    `username` VARCHAR
(
    45
) NOT NULL,
    `password` VARCHAR
(
    100
) NOT NULL,
    PRIMARY KEY
(
    `id`
),
    UNIQUE INDEX `username_UNIQUE`
(
    `username` ASC
) VISIBLE);

CREATE TABLE IF NOT EXISTS roles
(
    id
    bigint
    auto_increment,
    role
    varchar
(
    25
) not null,
    primary key
(
    id
)
    );

create table users_roles
(
    user_id bigint not null,
    role_id bigint null,
    constraint users_roles_pk
        unique (role_id, user_id),
    constraint users_roles_roles_id_fk
        foreign key (role_id) references roles (id),
    constraint users_roles_users_id_fk
        foreign key (user_id) references users (id)
);

INSERT INTO users (name, last_name, age, email, username, password)
VALUES ('Янa', 'Гулак', 33, 'gulak@mail.ru', 'yana',
        '{bcrypt}$2a$10$uoFl.ZsYRGbd1btsZpTMUOsqfOI4qpQj67T9KiVntqzHbyVjnVFUq}');


INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2);
    INSERT
INTO users (name, last_name, age, email, username, password)
VALUES ('Ирина', 'Еж', 24, 'yoj@mail.ru', 'irina', '{bcrypt}$2a$10$K04L6.n72wMjhWjJwTUxn.XEfL1ynqim2jlsXruCWWlGeSaK2Zyza');

INSERT INTO users (name, last_name, age, email, username, password)
VALUES ('Полина', 'Филипова', 37, 'filipova@mail.ru', 'polina',
        '{bcrypt}$2a$10$IZM8TOSEUK/IDNL2.f1i9us.wHkzmZBPMcqqz47JOrIAg3LjISMmu');

INSERT INTO users (name, last_name, age, email, username, password)
VALUES ('Петр', 'Якутчик', 36, 'yakutchik@mail.ru', 'petr',
        '{bcrypt}$2a$10$Tc6507HMuXEyweeRiwq8Pug1DUtBcQT8/BryHzIvlsqBEj3jXNgim');

INSERT INTO users (name, last_name, age, email, username, password)
VALUES ('Максим', 'Мамонтов', 22, 'mamontov@mail.ru', 'maksim',
        '$2a$10$Suk22vdBTW7TVCZkzZcvgOD66zceRBPgddGsFK9FcBBj.y0yG0zty');

