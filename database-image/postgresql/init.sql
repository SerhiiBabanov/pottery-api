CREATE ROLE service_products WITH LOGIN PASSWORD 'srv-pds';
CREATE ROLE service_subscribe WITH LOGIN PASSWORD 'srv-sbs';
CREATE ROLE service_support WITH LOGIN PASSWORD 'srv-sup';

CREATE SCHEMA service_products AUTHORIZATION service_products;
CREATE SCHEMA service_subscribe AUTHORIZATION service_subscribe;
CREATE SCHEMA service_support AUTHORIZATION service_support;
