--liquibase formatted sql


--changeset dlatyshev:2
CREATE INDEX student_name_index ON student (name)

--changeset dlatyshev:3
CREATE INDEX faculty_name_color_index ON faculty (name, color)