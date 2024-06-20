FROM postgres:13
MAINTAINER Serhii Babanov <serhii.babanov@gmail.com>

EXPOSE 5432
COPY ./postgresql/init.sql /docker-entrypoint-initdb.d/
