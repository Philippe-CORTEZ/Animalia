# Dockerfile pour la partie serveur
# Philippe CORTEZ

# Image base sur PostgreSQL avec ajout de scripts d'initialisation
FROM postgres

COPY ./src/main/resources/sql/1_creation_tables.sql /docker-entrypoint-initdb.d/1_creation_tables.sql
COPY ./src/main/resources/sql/2_donnees_exemple.sql /docker-entrypoint-initdb.d/2_donnees_exemple.sql
