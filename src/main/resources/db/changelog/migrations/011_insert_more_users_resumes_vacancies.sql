INSERT INTO users(name, surname, age, email, password, phone_number, avatar, account_type, enabled) VALUES
                                                                                                        ('Azamat', 'Kadyrov', 24, 'azamat@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0700333444', null, 'CANDIDATE', true),
                                                                                                        ('Alina', 'Temirova', 23, 'alina@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0700444555', null, 'CANDIDATE', true),
                                                                                                        ('Nursultan', 'Ermekov', 27, 'nursultan@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0700555666', null, 'CANDIDATE', true),
                                                                                                        ('Ainura', 'Toktosunova', 25, 'ainura@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0700666777', null, 'CANDIDATE', true),
                                                                                                        ('Ruslan', 'Alymbaev', 29, 'ruslan@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0700777888', null, 'CANDIDATE', true),
                                                                                                        ('Madina', 'Saparova', 26, 'madina@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0700888999', null, 'CANDIDATE', true),
                                                                                                        ('Erlan', 'Jusupov', 28, 'erlan@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0700999000', null, 'CANDIDATE', true),

                                                                                                        ('Dastan', 'Isakov', 34, 'dastan@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0777000111', null, 'EMPLOYER', true),
                                                                                                        ('Elvira', 'Musaeva', 31, 'elvira@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0777000222', null, 'EMPLOYER', true),
                                                                                                        ('Adilet', 'Boronov', 36, 'adilet@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0777000333', null, 'EMPLOYER', true),
                                                                                                        ('Aigerim', 'Osmonova', 29, 'aigerim@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0777000444', null, 'EMPLOYER', true),
                                                                                                        ('Timur', 'Sultanov', 33, 'timur@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0777000555', null, 'EMPLOYER', true),
                                                                                                        ('Kamila', 'Abdyldaeva', 30, 'kamila@mail.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '0777000666', null, 'EMPLOYER', true);

INSERT INTO categories(name) VALUES
                                 ('Marketing'),
                                 ('Finance'),
                                 ('HR'),
                                 ('Management');

INSERT INTO user_authorities(user_id, authority_id) VALUES
                                                        ((select id from users where email = 'azamat@mail.com'), (select id from authorities where authority = 'CREATE_RESUME')),
                                                        ((select id from users where email = 'alina@mail.com'), (select id from authorities where authority = 'CREATE_RESUME')),
                                                        ((select id from users where email = 'nursultan@mail.com'), (select id from authorities where authority = 'CREATE_RESUME')),
                                                        ((select id from users where email = 'ainura@mail.com'), (select id from authorities where authority = 'CREATE_RESUME')),
                                                        ((select id from users where email = 'ruslan@mail.com'), (select id from authorities where authority = 'CREATE_RESUME')),
                                                        ((select id from users where email = 'madina@mail.com'), (select id from authorities where authority = 'CREATE_RESUME')),
                                                        ((select id from users where email = 'erlan@mail.com'), (select id from authorities where authority = 'CREATE_RESUME')),

                                                        ((select id from users where email = 'dastan@mail.com'), (select id from authorities where authority = 'CREATE_VACANCY')),
                                                        ((select id from users where email = 'elvira@mail.com'), (select id from authorities where authority = 'CREATE_VACANCY')),
                                                        ((select id from users where email = 'adilet@mail.com'), (select id from authorities where authority = 'CREATE_VACANCY')),
                                                        ((select id from users where email = 'aigerim@mail.com'), (select id from authorities where authority = 'CREATE_VACANCY')),
                                                        ((select id from users where email = 'timur@mail.com'), (select id from authorities where authority = 'CREATE_VACANCY')),
                                                        ((select id from users where email = 'kamila@mail.com'), (select id from authorities where authority = 'CREATE_VACANCY'));

INSERT INTO resumes(applicant_id, name, category_id, salary, is_active, created_date, update_time) VALUES
                                                                                                       ((select id from users where email = 'azamat@mail.com'), 'Java Backend Developer', (select id from categories where name = 'IT'), 1400, true, now(), now()),
                                                                                                       ((select id from users where email = 'azamat@mail.com'), 'QA Engineer', (select id from categories where name = 'IT'), 1100, true, now(), now()),

                                                                                                       ((select id from users where email = 'alina@mail.com'), 'UI UX Designer', (select id from categories where name = 'Design'), 1300, true, now(), now()),
                                                                                                       ((select id from users where email = 'alina@mail.com'), 'Graphic Designer', (select id from categories where name = 'Design'), 1000, true, now(), now()),

                                                                                                       ((select id from users where email = 'nursultan@mail.com'), 'Digital Marketer', (select id from categories where name = 'Marketing'), 1200, true, now(), now()),
                                                                                                       ((select id from users where email = 'nursultan@mail.com'), 'SMM Specialist', (select id from categories where name = 'Marketing'), 950, true, now(), now()),

                                                                                                       ((select id from users where email = 'ainura@mail.com'), 'Financial Analyst', (select id from categories where name = 'Finance'), 1500, true, now(), now()),
                                                                                                       ((select id from users where email = 'ainura@mail.com'), 'Accountant', (select id from categories where name = 'Finance'), 1000, true, now(), now()),

                                                                                                       ((select id from users where email = 'ruslan@mail.com'), 'HR Manager', (select id from categories where name = 'HR'), 1250, true, now(), now()),
                                                                                                       ((select id from users where email = 'madina@mail.com'), 'Recruiter', (select id from categories where name = 'HR'), 1100, true, now(), now()),

                                                                                                       ((select id from users where email = 'erlan@mail.com'), 'Project Manager', (select id from categories where name = 'Management'), 1700, true, now(), now()),
                                                                                                       ((select id from users where email = 'erlan@mail.com'), 'Operations Manager', (select id from categories where name = 'Management'), 1600, true, now(), now()),

                                                                                                       ((select id from users where email = 'ruslan@mail.com'), 'Frontend Developer', (select id from categories where name = 'IT'), 1350, true, now(), now());

INSERT INTO vacancies(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time) VALUES
                                                                                                                                     ('Java Developer', 'Spring Boot and REST API development', (select id from categories where name = 'IT'), 1800, 1, 3, true, (select id from users where email = 'dastan@mail.com'), now(), now()),
                                                                                                                                     ('Frontend React Developer', 'React and modern UI development', (select id from categories where name = 'IT'), 1700, 1, 3, true, (select id from users where email = 'dastan@mail.com'), now(), now()),

                                                                                                                                     ('Product Designer', 'Design interfaces in Figma', (select id from categories where name = 'Design'), 1400, 1, 2, true, (select id from users where email = 'elvira@mail.com'), now(), now()),
                                                                                                                                     ('Motion Designer', 'Create animations and marketing visuals', (select id from categories where name = 'Design'), 1200, 1, 2, true, (select id from users where email = 'elvira@mail.com'), now(), now()),

                                                                                                                                     ('Marketing Specialist', 'Run campaigns and analytics', (select id from categories where name = 'Marketing'), 1300, 1, 3, true, (select id from users where email = 'adilet@mail.com'), now(), now()),
                                                                                                                                     ('Content Manager', 'Manage content plans and social posts', (select id from categories where name = 'Marketing'), 1100, 1, 2, true, (select id from users where email = 'adilet@mail.com'), now(), now()),

                                                                                                                                     ('Financial Manager', 'Budgeting and financial planning', (select id from categories where name = 'Finance'), 1900, 2, 4, true, (select id from users where email = 'aigerim@mail.com'), now(), now()),
                                                                                                                                     ('Junior Accountant', 'Accounting documents and reporting', (select id from categories where name = 'Finance'), 1000, 0, 1, true, (select id from users where email = 'aigerim@mail.com'), now(), now()),

                                                                                                                                     ('HR Specialist', 'Hiring and onboarding employees', (select id from categories where name = 'HR'), 1250, 1, 2, true, (select id from users where email = 'timur@mail.com'), now(), now()),
                                                                                                                                     ('Talent Acquisition Partner', 'Full cycle recruiting', (select id from categories where name = 'HR'), 1450, 2, 4, true, (select id from users where email = 'timur@mail.com'), now(), now()),

                                                                                                                                     ('Project Coordinator', 'Coordinate team tasks and deadlines', (select id from categories where name = 'Management'), 1350, 1, 2, true, (select id from users where email = 'kamila@mail.com'), now(), now()),
                                                                                                                                     ('Operations Lead', 'Manage daily business operations', (select id from categories where name = 'Management'), 1750, 2, 5, true, (select id from users where email = 'kamila@mail.com'), now(), now()),

                                                                                                                                     ('Business Analyst', 'Gather requirements and analyze processes', (select id from categories where name = 'Management'), 1650, 1, 3, true, (select id from users where email = 'kamila@mail.com'), now(), now());