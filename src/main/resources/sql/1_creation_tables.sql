-- Script qui construit les tables pour la base de données --
-- Philippe CORTEZ --


CREATE TABLE PATHOLOGIE
(
    ID BIGSERIAL,
    NOM VARCHAR,
    DESCRIPTION VARCHAR,

    PRIMARY KEY (ID),

    UNIQUE (NOM)
);


CREATE TABLE SOIN
(
    ID BIGSERIAL,
    NOM VARCHAR,
    DESCRIPTION VARCHAR,
    PRIX FLOAT,
    POURCENTAGE_CHARGE FLOAT,

    PRIMARY KEY (ID),

    UNIQUE (NOM),
    CHECK ( POURCENTAGE_CHARGE <= 1.0 AND POURCENTAGE_CHARGE >= 0.0)
);


CREATE TABLE REFUGE
(
    ID BIGSERIAL,
    NOM VARCHAR,
    ADRESSE VARCHAR,
    DESCRIPTION VARCHAR,

    PRIMARY KEY (ID),

    UNIQUE (NOM)
);


CREATE TABLE PERSONNE
(
    NUM_MAITRE BIGSERIAL,
    NOM VARCHAR,
    PRENOM VARCHAR,
    DATE_NAISSANCE DATE,
    ADRESSE VARCHAR,

    PRIMARY KEY (NUM_MAITRE),

    UNIQUE (NOM, PRENOM, DATE_NAISSANCE, ADRESSE)
);


CREATE TABLE ANIMAL
(
    NUM_PUCE BIGINT,
    NOM VARCHAR,
    DATE_NAISSANCE DATE,
    SEXE VARCHAR,
    ESPECE VARCHAR,
    RACE VARCHAR,
    DESCRIPTION VARCHAR,
    SOS BOOLEAN,
    PRIX FLOAT,

    PRIMARY KEY (NUM_PUCE)
);


CREATE TABLE PATHOLOGIES_ANIMAUX
(
    NUM_PUCE_ANIMAL BIGINT,
    ID_PATHOLOGIE BIGINT,

    PRIMARY KEY (NUM_PUCE_ANIMAL, ID_PATHOLOGIE),
    FOREIGN KEY (NUM_PUCE_ANIMAL) REFERENCES ANIMAL(NUM_PUCE) ON DELETE CASCADE,
    FOREIGN KEY (ID_PATHOLOGIE) REFERENCES PATHOLOGIE(ID)
);


CREATE TABLE SOINS_ANIMAUX
(
    NUM_PUCE_ANIMAL BIGINT,
    ID_SOIN BIGINT,

    PRIMARY KEY (NUM_PUCE_ANIMAL, ID_SOIN),
    FOREIGN KEY (NUM_PUCE_ANIMAL) REFERENCES ANIMAL(NUM_PUCE) ON DELETE CASCADE ,
    FOREIGN KEY (ID_SOIN) REFERENCES SOIN(ID) ON DELETE CASCADE
);


CREATE TABLE INFORMATION_SEJOUR
(
    ID BIGSERIAL,
    NUM_PUCE_ANIMAL BIGINT,
    ID_REFUGE BIGINT,
    DATE_DEBUT_SEJOUR DATE,
    DATE_FIN_SEJOUR DATE,
    MOTIF_FIN_SEJOUR VARCHAR,
    REFUGE_ACTUEL BOOLEAN,

    PRIMARY KEY (ID),

    FOREIGN KEY (NUM_PUCE_ANIMAL) REFERENCES ANIMAL(NUM_PUCE) ON DELETE CASCADE,
    FOREIGN KEY (ID_REFUGE) REFERENCES REFUGE(ID),

    UNIQUE (NUM_PUCE_ANIMAL, ID_REFUGE, DATE_DEBUT_SEJOUR),

    CHECK ( DATE_DEBUT_SEJOUR <= INFORMATION_SEJOUR.DATE_FIN_SEJOUR )
);


CREATE TABLE INFORMATION_ADOPTION
(
    ID BIGSERIAL,
    NUM_PUCE_ANIMAL BIGINT,
    NUM_MAITRE BIGINT,
    DATE_ADOPTION DATE,
    DATE_RETOUR DATE,
    RETOUR_ADOPTION BOOLEAN,
    COTISATION FLOAT,
    DON FLOAT,

    PRIMARY KEY (ID),

    FOREIGN KEY (NUM_PUCE_ANIMAL) REFERENCES ANIMAL(NUM_PUCE) ON DELETE CASCADE ,
    FOREIGN KEY (NUM_MAITRE) REFERENCES PERSONNE(NUM_MAITRE),

    UNIQUE (NUM_PUCE_ANIMAL, NUM_MAITRE),

    CHECK ( DATE_ADOPTION <= INFORMATION_ADOPTION.DATE_RETOUR ),
    CHECK ( COTISATION > 0 ),
    CHECK ( DON >= 0 )
);
