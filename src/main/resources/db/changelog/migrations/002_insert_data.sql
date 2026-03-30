INSERT INTO users(name, surname, age, email, password, phone_number, avatar, account_type, enabled)
VALUES
    ('Aida', 'Sydykova', 22, 'aida@mail.com',
     '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
     '0700111222', null, 'CANDIDATE', true),

    ('Bek', 'Nurbekov', 30, 'bek@mail.com',
     '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
     '0700222333', null, 'EMPLOYER', true);

INSERT INTO categories(name)
VALUES
    ('IT'),
    ('Design');

INSERT INTO resumes(applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    (1, 'Java Developer', 1, 1200, true, now(), now()),
    (1, 'Frontend Developer', 1, 1000, true, now(), now());

INSERT INTO vacancies(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Backend Dev', 'Spring Boot dev', 1, 1500, 1, 3, true, 2, now(), now()),
    ('UI Designer', 'Figma designer', 2, 1200, 1, 2, true, 2, now(), now());

INSERT INTO responses(resume_id, vacancy_id, confirmation)
VALUES
    (1, 1, true);

INSERT INTO authorities(authority)
VALUES
    ('READ'),
    ('WRITE');

INSERT INTO user_authorities(user_id, authority_id)
VALUES
    (1, 1),
    (2, 1),
    (2, 2);