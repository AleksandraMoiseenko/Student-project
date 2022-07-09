-- changeset liquibase:3
ALTER TABLE student_jooq.student DROP COLUMN id;

ALTER TABLE student_jooq.student ADD COLUMN id SERIAL PRIMARY KEY;