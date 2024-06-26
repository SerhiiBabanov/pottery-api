CREATE TABLE support_requests
(
    id         UUID NOT NULL,
    name       VARCHAR(255),
    email      VARCHAR(255),
    phone      VARCHAR(255),
    message    VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    token      UUID,
    resolved   BOOLEAN,
    CONSTRAINT pk_support_requests PRIMARY KEY (id)
);
