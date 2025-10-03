-- Create the database if it doesn't exist
CREATE DATABASE telemetry_db;

-- Connect to the database
\c telemetry_db;

CREATE TABLE IF NOT EXISTS telemetry
(
    id             integer not null
        primary key,
    device_id      integer,
    parameters     varchar(255),
    status         varchar(255),
    telemetry_date timestamp
);