create table if not exists users (
     id int auto_increment primary key,
     name varchar(50),
    surname varchar(50),
    age int,
    email varchar(100) unique,
    password varchar(50),
    phone_number varchar(20),
    avatar varchar(255),
    account_type varchar(20)
    );

create table if not exists categories (
      id int auto_increment primary key,
      name varchar(50)
    );

create table if not exists resumes (
   id int auto_increment primary key,
   applicant_id int,
   name varchar(100),
    category_id int,
    salary double,
    is_active boolean,
    created_date timestamp,
    update_time timestamp,
    foreign key (applicant_id) references users(id),
    foreign key (category_id) references categories(id)
    );

create table if not exists vacancies (
     id int auto_increment primary key,
     name varchar(100),
    description text,
    category_id int,
    salary double,
    exp_from int,
    exp_to int,
    is_active boolean,
    author_id int,
    created_date timestamp,
    update_time timestamp,
    foreign key (author_id) references users(id),
    foreign key (category_id) references categories(id)
    );

create table if not exists responses (
     id int auto_increment primary key,
     resume_id int,
     vacancy_id int,
     confirmation boolean,
     foreign key (resume_id) references resumes(id),
    foreign key (vacancy_id) references vacancies(id)
    );

insert into users(name, surname, age, email, password, phone_number, avatar, account_type)
values
    ('Aida', 'Sydykova', 22, 'aida@mail.com', '1234', '0700111222', null, 'CANDIDATE'),
    ('Bek', 'Nurbekov', 30, 'bek@mail.com', '1234', '0700222333', null, 'EMPLOYER');

insert into categories(name)
values
    ('IT'),
    ('Design');

insert into resumes(applicant_id, name, category_id, salary, is_active, created_date, update_time)
values
    (1, 'Java Developer', 1, 1200, true, now(), now()),
    (1, 'Frontend Developer', 1, 1000, true, now(), now());

insert into vacancies(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
values
    ('Backend Dev', 'Spring Boot dev', 1, 1500, 1, 3, true, 2, now(), now()),
    ('UI Designer', 'Figma designer', 2, 1200, 1, 2, true, 2, now(), now());

insert into responses(resume_id, vacancy_id, confirmation)
values
    (1, 1, true);