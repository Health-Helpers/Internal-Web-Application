
-- Table: ehh.language
-- DROP TABLE ehh."language";

CREATE TABLE ehh.language
(
  "languageId" numeric NOT NULL,
  name text,
  code text,
  CONSTRAINT "LangPK" PRIMARY KEY ("languageId")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ehh.language
  OWNER TO postgres;


-- Table: ehh."user"
-- DROP TABLE ehh."user";

CREATE TABLE ehh."user"
(
  "userId" numeric NOT NULL,
  type integer,
  iddoc text,
  name text,
  surname text,
  birthdate date,
  phone text,
  adress text,
  langid numeric,
  CONSTRAINT "userPK" PRIMARY KEY ("userId"),
  CONSTRAINT "langFK" FOREIGN KEY (langid)
      REFERENCES ehh.language ("languageId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ehh."user"
  OWNER TO postgres;


CREATE INDEX "fki_langFK"
  ON ehh."user"
  USING btree
  (langid);

-- Table: ehh."adjBackup"

-- Table: ehh."adjBackup"

-- DROP TABLE ehh."adjBackup";

CREATE TABLE ehh."adjBackup"
(
  id numeric NOT NULL,
  json text,
  date date,
  "userId" numeric,
  CONSTRAINT "adjBckPK" PRIMARY KEY (id),
  CONSTRAINT "userFK" FOREIGN KEY ("userId")
      REFERENCES ehh."user" ("userId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ehh."adjBackup"
  OWNER TO postgres;

-- Table: ehh.adjustements

-- DROP TABLE ehh.adjustements;

CREATE TABLE ehh.adjustements
(
  "idAdjustements" numeric NOT NULL,
  json text,
  "userId" numeric,
  CONSTRAINT "adjustementsPK" PRIMARY KEY ("idAdjustements"),
  CONSTRAINT "userFK" FOREIGN KEY ("userId")
      REFERENCES ehh."user" ("userId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ehh.adjustements
  OWNER TO postgres;

-- Table: ehh.patient

-- DROP TABLE ehh.patient;

CREATE TABLE ehh.patient
(
  "patientId" numeric NOT NULL,
  "dependencyGrade" text,
  disease text,
  "userId" numeric,
  CONSTRAINT "patientPK" PRIMARY KEY ("patientId"),
  CONSTRAINT "userFK" FOREIGN KEY ("userId")
      REFERENCES ehh."user" ("userId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ehh.patient
  OWNER TO postgres;

-- Table: ehh.responsible

-- DROP TABLE ehh.responsible;

CREATE TABLE ehh.responsible
(
  "responsibleId" numeric NOT NULL,
  "userId" numeric NOT NULL,
  CONSTRAINT "responsiblePK" PRIMARY KEY ("responsibleId"),
  CONSTRAINT "responsibleUserFK" FOREIGN KEY ("userId")
      REFERENCES ehh."user" ("userId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ehh.responsible
  OWNER TO postgres;


-- Table: ehh."patientResponsible"

-- DROP TABLE ehh."patientResponsible";

CREATE TABLE ehh."patientResponsible"
(
  "patientResponsibleId" numeric NOT NULL,
  "responsibleId" numeric NOT NULL,
  "userId" numeric NOT NULL,
  CONSTRAINT "patientResponsiblePK" PRIMARY KEY ("patientResponsibleId"),
  CONSTRAINT "patientresponsibleFK" FOREIGN KEY ("responsibleId")
      REFERENCES ehh.responsible ("responsibleId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "userId" FOREIGN KEY ("userId")
      REFERENCES ehh."user" ("userId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ehh."patientResponsible"
  OWNER TO postgres;

-- Table: ehh."auxiliar-data"

-- DROP TABLE ehh."auxiliar-data";

CREATE TABLE ehh."auxiliar-data"
(
  "idAuxiliarData" numeric NOT NULL,
  "patientId" numeric NOT NULL,
  "auxiliar-data" numeric,
  CONSTRAINT "auxiliarDataPK" PRIMARY KEY ("idAuxiliarData"),
  CONSTRAINT "patientDataFK" FOREIGN KEY ("patientId")
      REFERENCES ehh.patient ("patientId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ehh."auxiliar-data"
  OWNER TO postgres;

