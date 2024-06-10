FROM postgres:13
MAINTAINER Serhii Babanov <serhii.babanov@gmail.com>

EXPOSE 5432
RUN mkdir -p /docker-entrypoint-initdb.d/
COPY ./postgresql/init.sql /docker-entrypoint-initdb.d/init.sql
