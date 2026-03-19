create table if not exists users (
    id int auto_increment primary key,
    name varchar(50),
    email varchar(100) unique,
    phone varchar(20),
    password varchar(50),
    role varchar(20)
    );

create table if not exists categories (
      id int auto_increment primary key,
      name varchar(50)
    );

create table if not exists resumes (
    id int auto_increment primary key,
    title varchar(100),
    description text,
    user_id int,
    category_id int,
    foreign key (user_id) references users(id),
    foreign key (category_id) references categories(id)
    );

create table if not exists vacancies (
     id int auto_increment primary key,
     title varchar(100),
    description text,
    salary int,
    user_id int,
    category_id int,
    foreign key (user_id) references users(id),
    foreign key (category_id) references categories(id)
    );

create table if not exists responses (
     id int auto_increment primary key,
     user_id int,
     vacancy_id int,
     foreign key (user_id) references users(id),
    foreign key (vacancy_id) references vacancies(id)
    );

insert into users(name, email, phone, password, role)
values
    ('Aida', 'aida@mail.com', '0700111222', '1234', 'CANDIDATE'),
    ('Bek', 'bek@mail.com', '0700222333', '1234', 'EMPLOYER');

insert into categories(name)
values
    ('IT'),
    ('Design');

insert into resumes(title, description, user_id, category_id)
values
    ('Java Developer', 'Junior Java dev', 1, 1),
    ('Frontend Developer', 'HTML CSS JS', 1, 1);

insert into vacancies(title, description, salary, user_id, category_id)
values
    ('Backend Dev', 'Spring Boot dev', 1500, 2, 1),
    ('UI Designer', 'Figma designer', 1200, 2, 2);

insert into responses(user_id, vacancy_id)
values
    (1, 1);