-- liquibase formatted sql

--changeset shulga:1

CREATE TABLE publication
(
    id bigint primary key,
    text       TEXT
);

CREATE TABLE photo
(
    id bigint primary key,
    file_size  bigint,
    media_type varchar(255),
    photo      bytea,
    id_publication     bigint REFERENCES publication (id) ON DELETE CASCADE
);