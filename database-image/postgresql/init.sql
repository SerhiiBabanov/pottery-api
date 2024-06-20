CREATE ROLE service_products WITH LOGIN PASSWORD 'srv-pds';
CREATE ROLE service_subscribe WITH LOGIN PASSWORD 'srv-sbs';

CREATE SCHEMA service_products AUTHORIZATION service_products;
CREATE SCHEMA service_subscribe AUTHORIZATION service_subscribe;
