
INSERT INTO status (id, status)
VALUES (1, 'ACTIVE'),
       (2, 'BLOCKED'),
       (3, 'DELETED');


INSERT INTO role (id, role)
VALUES (1, 'USER'),
       (2, 'MODERATOR'),
       (3, 'ADMIN');


INSERT INTO gender (id, gender)
VALUES (1, 'UNDEFINE'),
       (2, 'MALE'),
       (3, 'FEMALE');


INSERT INTO complain_status (id, status)
VALUES (1, 'OPEN'),
       (2, 'CLOSE'),
       (3, 'APPROVED'),
       (4, 'REJECTED');


INSERT INTO post_status (id, status)
VALUES (1, 'PUBLIC'),
       (2, 'DRAFT'),
       (3, 'BLOCKED');


INSERT INTO account (username, password, role_id, status_id)
VALUES ('moderator', 'moderator', 2, 1),
       ('admin', 'admin', 3, 1);


INSERT INTO profile(account_id, gender_id)
VALUES ((SELECT id FROM account WHERE username = 'moderator'), 1),
       ((SELECT id FROM account WHERE username = 'admin'), 1);





