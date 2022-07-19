CREATE DATABASE popular_home_db;


CREATE TABLE public.rule (
                             id VARCHAR(255) NOT NULL,
                             code INTEGER NOT NULL,
                             type VARCHAR(255) NOT NULL,
                             active BOOLEAN NOT NULL,
                             created_at TIMESTAMP NOT NULL,
                             updated_at TIMESTAMP NOT NULL,
                             CONSTRAINT rule_pk PRIMARY KEY (id)
);


CREATE TABLE public.state (
                              id VARCHAR(255) NOT NULL,
                              name VARCHAR(200) NOT NULL,
                              code INTEGER NOT NULL,
                              active BOOLEAN NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              updated_at TIMESTAMP NOT NULL,
                              CONSTRAINT state_pk PRIMARY KEY (id)
);


CREATE TABLE public.city (
                             id VARCHAR(255) NOT NULL,
                             state_id VARCHAR(255) NOT NULL,
                             name VARCHAR(200) NOT NULL,
                             code INTEGER NOT NULL,
                             active BOOLEAN NOT NULL,
                             created_at TIMESTAMP NOT NULL,
                             updated_at TIMESTAMP NOT NULL,
                             CONSTRAINT city_pk PRIMARY KEY (id)
);


CREATE TABLE public.selection (
                                  id VARCHAR(255) NOT NULL,
                                  ordinance INTEGER NOT NULL,
                                  city_id VARCHAR(255) NOT NULL,
                                  start TIMESTAMP NOT NULL,
                                  end_1 TIMESTAMP NOT NULL,
                                  active BOOLEAN NOT NULL,
                                  created_at TIMESTAMP NOT NULL,
                                  updated_at TIMESTAMP NOT NULL,
                                  CONSTRAINT selection_pk PRIMARY KEY (id)
);


CREATE TABLE public.selection_rule (
                                       selection_id VARCHAR(255) NOT NULL,
                                       rule_id VARCHAR(255) NOT NULL,
                                       active BOOLEAN NOT NULL,
                                       created_at TIMESTAMP NOT NULL,
                                       updated_at TIMESTAMP NOT NULL,
                                       CONSTRAINT selection_rule_pk PRIMARY KEY (selection_id, rule_id)
);


CREATE TABLE public.district (
                                 id VARCHAR(255) NOT NULL,
                                 city_id VARCHAR(255) NOT NULL,
                                 name VARCHAR(200) NOT NULL,
                                 code INTEGER NOT NULL,
                                 active BOOLEAN NOT NULL,
                                 created_at TIMESTAMP NOT NULL,
                                 updated_at TIMESTAMP NOT NULL,
                                 CONSTRAINT district_pk PRIMARY KEY (id)
);


CREATE TABLE public.address (
                                id VARCHAR(255) NOT NULL,
                                district_id VARCHAR(255) NOT NULL,
                                street VARCHAR(255) NOT NULL,
                                number INTEGER,
                                zip_code VARCHAR(255) NOT NULL,
                                complement VARCHAR(255),
                                active BOOLEAN NOT NULL,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP NOT NULL,
                                CONSTRAINT address_pk PRIMARY KEY (id)
);


CREATE TABLE public.person (
                               id VARCHAR(255) NOT NULL,
                               name VARCHAR(255) NOT NULL,
                               age INTEGER NOT NULL,
                               cpf VARCHAR(255),
                               active BOOLEAN NOT NULL,
                               created_at TIMESTAMP NOT NULL,
                               updated_at TIMESTAMP NOT NULL,
                               CONSTRAINT person_pk PRIMARY KEY (id)
);


CREATE TABLE public.spouse (
                               id VARCHAR(255) NOT NULL,
                               active BOOLEAN NOT NULL,
                               created_at TIMESTAMP NOT NULL,
                               updated_at TIMESTAMP NOT NULL,
                               CONSTRAINT spouse_pk PRIMARY KEY (id)
);


CREATE TABLE public.suitor (
                               id VARCHAR(255) NOT NULL,
                               phone VARCHAR(255) NOT NULL,
                               email VARCHAR(255) NOT NULL,
                               active BOOLEAN NOT NULL,
                               created_at TIMESTAMP NOT NULL,
                               updated_at TIMESTAMP NOT NULL,
                               CONSTRAINT suitor_pk PRIMARY KEY (id)
);


CREATE TABLE public.family (
                               id VARCHAR(255) NOT NULL,
                               suitor_id VARCHAR(255) NOT NULL,
                               spouse_id VARCHAR(255) NOT NULL,
                               address_id VARCHAR(255) NOT NULL,
                               active BOOLEAN NOT NULL,
                               created_at TIMESTAMP NOT NULL,
                               updated_at TIMESTAMP NOT NULL,
                               CONSTRAINT family_pk PRIMARY KEY (id)
);


CREATE TABLE public.family_selection (
                                         family_id VARCHAR(255) NOT NULL,
                                         selection_id VARCHAR(255) NOT NULL,
                                         active BOOLEAN NOT NULL,
                                         created_at TIMESTAMP NOT NULL,
                                         updated_at TIMESTAMP NOT NULL,
                                         CONSTRAINT family_selection_pk PRIMARY KEY (family_id, selection_id)
);


CREATE TABLE public.dependents (
                                   family_id VARCHAR(255) NOT NULL,
                                   person_id VARCHAR(255) NOT NULL,
                                   active BOOLEAN NOT NULL,
                                   created_at TIMESTAMP NOT NULL,
                                   updated_at TIMESTAMP NOT NULL,
                                   CONSTRAINT dependents_pk PRIMARY KEY (family_id, person_id)
);


ALTER TABLE public.selection_rule ADD CONSTRAINT rule_selection_rule_fk
    FOREIGN KEY (rule_id)
        REFERENCES public.rule (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.city ADD CONSTRAINT state_city_fk
    FOREIGN KEY (state_id)
        REFERENCES public.state (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.district ADD CONSTRAINT city_district_fk
    FOREIGN KEY (city_id)
        REFERENCES public.city (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.selection ADD CONSTRAINT city_selection_fk
    FOREIGN KEY (city_id)
        REFERENCES public.city (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.selection_rule ADD CONSTRAINT selection_selection_rule_fk
    FOREIGN KEY (selection_id)
        REFERENCES public.selection (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.family_selection ADD CONSTRAINT selection_family_selection_fk
    FOREIGN KEY (selection_id)
        REFERENCES public.selection (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.address ADD CONSTRAINT district_address_fk
    FOREIGN KEY (district_id)
        REFERENCES public.district (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.family ADD CONSTRAINT address_family_fk
    FOREIGN KEY (address_id)
        REFERENCES public.address (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.suitor ADD CONSTRAINT person_suitor_fk
    FOREIGN KEY (id)
        REFERENCES public.person (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.spouse ADD CONSTRAINT person_spouse_fk
    FOREIGN KEY (id)
        REFERENCES public.person (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.dependents ADD CONSTRAINT person_dependents_fk
    FOREIGN KEY (person_id)
        REFERENCES public.person (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.family ADD CONSTRAINT spouse_family_fk
    FOREIGN KEY (spouse_id)
        REFERENCES public.spouse (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.family ADD CONSTRAINT suitor_family_fk
    FOREIGN KEY (suitor_id)
        REFERENCES public.suitor (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.dependents ADD CONSTRAINT family_dependents_fk
    FOREIGN KEY (family_id)
        REFERENCES public.family (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

ALTER TABLE public.family_selection ADD CONSTRAINT family_family_selection_fk
    FOREIGN KEY (family_id)
        REFERENCES public.family (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;