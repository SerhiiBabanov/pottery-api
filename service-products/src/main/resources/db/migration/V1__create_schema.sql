CREATE SEQUENCE IF NOT EXISTS categories_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS collections_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS colors_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS materials_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS products_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS properties_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE categories
(
    id   BIGINT NOT NULL,
    name VARCHAR(50),
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE categories_categories
(
    categories_2_id BIGINT NOT NULL,
    category_1_id   BIGINT NOT NULL,
    CONSTRAINT pk_categories_categories PRIMARY KEY (categories_2_id, category_1_id)
);

CREATE TABLE collections
(
    id          BIGINT NOT NULL,
    name        VARCHAR(50),
    description VARCHAR(500),
    CONSTRAINT pk_collections PRIMARY KEY (id)
);

CREATE TABLE colors
(
    id   BIGINT NOT NULL,
    name VARCHAR(50),
    CONSTRAINT pk_colors PRIMARY KEY (id)
);

CREATE TABLE materials
(
    id   BIGINT NOT NULL,
    name VARCHAR(50),
    CONSTRAINT pk_materials PRIMARY KEY (id)
);

CREATE TABLE products
(
    id                     BIGINT NOT NULL,
    name                   VARCHAR(255),
    description            VARCHAR(500),
    care_guide             VARCHAR(1000),
    images                 JSONB,
    category_id            BIGINT NOT NULL,
    catalog_price          DECIMAL(6, 2),
    discount_catalog_price DECIMAL(6, 2),
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE products_collections
(
    collections_id BIGINT NOT NULL,
    product_id     BIGINT NOT NULL,
    CONSTRAINT pk_products_collections PRIMARY KEY (collections_id, product_id)
);

CREATE TABLE properties
(
    id             BIGINT NOT NULL,
    dimensions     VARCHAR(100),
    material_id    BIGINT,
    color_id       BIGINT,
    default_price  DECIMAL(6, 2),
    discount_price DECIMAL(6, 2),
    quantity       INTEGER,
    sold           INTEGER,
    product_id     BIGINT,
    CONSTRAINT pk_properties PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE properties
    ADD CONSTRAINT FK_PROPERTIES_ON_COLOR FOREIGN KEY (color_id) REFERENCES colors (id);

ALTER TABLE properties
    ADD CONSTRAINT FK_PROPERTIES_ON_MATERIAL FOREIGN KEY (material_id) REFERENCES materials (id);

ALTER TABLE properties
    ADD CONSTRAINT FK_PROPERTIES_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE categories_categories
    ADD CONSTRAINT fk_catcat_on_categories_2 FOREIGN KEY (categories_2_id) REFERENCES categories (id);

ALTER TABLE categories_categories
    ADD CONSTRAINT fk_catcat_on_category_1 FOREIGN KEY (category_1_id) REFERENCES categories (id);

ALTER TABLE products_collections
    ADD CONSTRAINT fk_procol_on_collection FOREIGN KEY (collections_id) REFERENCES collections (id);

ALTER TABLE products_collections
    ADD CONSTRAINT fk_procol_on_product FOREIGN KEY (product_id) REFERENCES products (id);
