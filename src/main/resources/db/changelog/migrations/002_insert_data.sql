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
    (
        (select id from users where email = 'aida@mail.com'),
        'Java Developer',
        (select id from categories where name = 'IT'),
        1200,
        true,
        now(),
        now()
    ),
    (
        (select id from users where email = 'aida@mail.com'),
        'Frontend Developer',
        (select id from categories where name = 'IT'),
        1000,
        true,
        now(),
        now()
    );

INSERT INTO vacancies(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    (
        'Backend Dev',
        'Spring Boot dev',
        (select id from categories where name = 'IT'),
        1500,
        1,
        3,
        true,
        (select id from users where email = 'bek@mail.com'),
        now(),
        now()
    ),
    (
        'UI Designer',
        'Figma designer',
        (select id from categories where name = 'Design'),
        1200,
        1,
        2,
        true,
        (select id from users where email = 'bek@mail.com'),
        now(),
        now()
    );

INSERT INTO responses(resume_id, vacancy_id, confirmation)
VALUES
    (
        (select id from resumes where name = 'Java Developer'
                                  and applicant_id = (select id from users where email = 'aida@mail.com')),
        (select id from vacancies where name = 'Backend Dev'
                                    and author_id = (select id from users where email = 'bek@mail.com')),
        true
    );