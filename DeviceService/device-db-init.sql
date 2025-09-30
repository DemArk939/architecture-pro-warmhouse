-- Create the database if it doesn't exist
CREATE DATABASE device_db;

-- Connect to the database
\c device_db;

CREATE TABLE IF NOT EXISTS devices
(
    id            integer      not null
        primary key,
    device_type   varchar(255) not null,
    house_id      integer,
    is_active     boolean      not null,
    location_id   integer,
    serial_number varchar(255) not null,
    status        varchar(255) not null,
    url           varchar(255),
    user_id       integer
);