

CREATE TABLE account
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    phone varchar(32) UNIQUE,
    email varchar(64) UNIQUE,
    username varchar(64) UNIQUE NOT NULL,
    password varchar(64) NOT NULL,
    role varchar(8) NOT NULL DEFAULT 'USER',
    is_active boolean NOT NULL DEFAULT true,
    is_verified_phone boolean NOT NULL DEFAULT false,
    is_verified_email boolean NOT NULL DEFAULT false,
    create_date timestamp with time zone DEFAULT now()
);


CREATE TABLE icon
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar(64),
    type varchar(64),
    data bytea
);


CREATE TABLE profile
(
    account_id uuid REFERENCES account (id),
    full_name varchar(128),
    birthdate date,
    gender char(1),
    contacts jsonb,
    about_me text,
    PRIMARY KEY (account_id)
);



