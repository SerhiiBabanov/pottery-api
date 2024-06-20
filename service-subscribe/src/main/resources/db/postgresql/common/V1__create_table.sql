CREATE TABLE subscribes
(
    email VARCHAR(255) NOT NULL,
    token UUID,
    CONSTRAINT pk_subscribes PRIMARY KEY (email)
);
