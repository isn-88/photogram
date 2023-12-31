
CREATE TABLE status
(
    id smallint PRIMARY KEY,
    status varchar(8) UNIQUE NOT NULL,
    CONSTRAINT status_ch CHECK (status IN ('ACTIVE', 'BLOCKED', 'DELETED'))
);


CREATE TABLE role
(
    id smallint PRIMARY KEY,
    role varchar(16) UNIQUE NOT NULL,
    CONSTRAINT role_ch CHECK (role IN ('USER', 'MODERATOR', 'ADMIN'))
);


CREATE TABLE gender
(
    id smallint PRIMARY KEY,
    gender varchar(8),
    CONSTRAINT gender_ch CHECK (gender IN ('MALE', 'FEMALE', 'UNDEFINE'))
);


CREATE TABLE complain_status
(
    id smallint PRIMARY KEY,
    status varchar(16),
    CONSTRAINT gender_ch CHECK (status IN ('OPEN', 'CLOSE', 'APPROVED', 'REJECTED'))
);

CREATE TABLE post_status
(
    id smallint PRIMARY KEY,
    status varchar(16),
    CONSTRAINT post_status_ch CHECK (status IN ('PUBLIC', 'DRAFT', 'BLOCKED'))
);


CREATE TABLE account
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    phone varchar(20) UNIQUE,
    email varchar(320) UNIQUE,
    username varchar(32) UNIQUE NOT NULL,
    password varchar(64) NOT NULL,
    role_id smallint REFERENCES role (id) NOT NULL,
    status_id smallint REFERENCES status (id) NOT NULL,
    is_verified_phone boolean NOT NULL DEFAULT false,
    is_verified_email boolean NOT NULL DEFAULT false,
    create_date timestamp with time zone DEFAULT now()
);


CREATE TABLE icon
(
    account_id uuid REFERENCES account (id),
    data bytea,
    PRIMARY KEY (account_id)
);


CREATE TABLE profile
(
    account_id uuid REFERENCES account (id),
    full_name varchar(128),
    birthdate date,
    gender_id smallint REFERENCES gender (id) NOT NULL,
    contacts jsonb,
    about_me text,
    PRIMARY KEY (account_id)
);


CREATE TABLE post
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    account_id uuid REFERENCES account (id) NOT NULL,
    status_id smallint REFERENCES post_status (id) NOT NULL,
    create_date timestamp with time zone NOT NULL DEFAULT now(),
    description text
);


CREATE TABLE image
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    account_id uuid REFERENCES account (id) NOT NULL,
    post_id uuid REFERENCES post (id) NOT NULL,
    file_name varchar(256) NOT NULL,
    full_path varchar(512) NOT NULL,
    ordinal smallint NOT NULL
);


CREATE TABLE comment
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    account_id uuid REFERENCES account (id) NOT NULL,
    post_id uuid REFERENCES post (id) NOT NULL,
    create_time timestamp with time zone NOT NULL DEFAULT now(),
    change_time timestamp with time zone,
    is_deleted boolean NOT NULL DEFAULT false,
    message text NOT NULL
);


CREATE TABLE subscribe
(
    account_id uuid REFERENCES account (id),
    subscribe_id uuid REFERENCES account (id),
    subscribe_time timestamp with time zone NOT NULL DEFAULT now(),
    PRIMARY KEY (account_id, subscribe_id)
);


CREATE TABLE complaint
(
    account_id uuid REFERENCES account (id) NOT NULL,
    post_id uuid REFERENCES post (id) NOT NULL,
    status_id smallint REFERENCES complain_status (id) NOT NULL,
    create_time timestamp with time zone NOT NULL DEFAULT now(),
    close_time timestamp with time zone,
    message text,
    PRIMARY KEY (account_id, post_id)
);


CREATE TABLE likes
(
    account_id uuid REFERENCES account (id) NOT NULL,
    post_id uuid REFERENCES post (id) NOT NULL,
    score smallint NOT NULL DEFAULT 0,
    PRIMARY KEY (account_id, post_id),
    CHECK (score >= -1 OR score <= 1)
)
