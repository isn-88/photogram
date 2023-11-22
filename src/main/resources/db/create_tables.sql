

CREATE TABLE contact
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    email varchar(64) UNIQUE NOT NULL,
    phone varchar(32) UNIQUE,
    contacts jsonb
);

CREATE TABLE icon
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name varchar(64),
    type varchar(64),
    data bytea
);

CREATE TABLE role
(
    id smallserial PRIMARY KEY,
    name varchar(16) UNIQUE NOT NULL
);

CREATE TABLE person
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name varchar(64) NOT NULL,
    last_name varchar(64) NOT NULL,
    middle_name varchar(64),
    birth_date date,
    contact_id uuid REFERENCES contact (id),
    icon_id uuid REFERENCES icon (id)
);

CREATE TABLE account
(
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    person_id bigint,
    login varchar(64) UNIQUE NOT NULL,
    password varchar(64) NOT NULL,
    role_id smallint,
    is_active boolean NOT NULL DEFAULT false,
    description text,
    create_date timestamp with time zone DEFAULT now(),
    last_activity timestamp with time zone DEFAULT now()
);





