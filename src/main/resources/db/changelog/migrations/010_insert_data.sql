INSERT INTO authorities(authority)
VALUES
    ('CREATE_RESUME'),
    ('CREATE_VACANCY');

INSERT INTO user_authorities(user_id, authority_id)
VALUES
    (
        (select id from users where email = 'aida@mail.com'),
        (select id from authorities where authority = 'CREATE_RESUME')
    ),
    (
        (select id from users where email = 'bek@mail.com'),
        (select id from authorities where authority = 'CREATE_VACANCY')
    );