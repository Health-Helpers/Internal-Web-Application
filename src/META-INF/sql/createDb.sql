-- Table: public.language
-- DROP TABLE public."language";

CREATE TABLE public.language
(
  "languageId" numeric NOT NULL,
  name text,
  code text,
  CONSTRAINT "LangPK" PRIMARY KEY ("languageId")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.language
  OWNER TO postgres;


-- Table: public."user"
-- DROP TABLE public."user";

CREATE TABLE public."user"
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
  CONSTRAINT "userId" PRIMARY KEY ("userId"),
  CONSTRAINT "langFK" FOREIGN KEY (langid)
      REFERENCES public.language ("languageId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."user"
  OWNER TO postgres;


CREATE INDEX "fki_langFK"
  ON public."user"
  USING btree
  (langid);

-- Table: public."adjBackup"

-- Table: public."adjBackup"

-- DROP TABLE public."adjBackup";

CREATE TABLE public."adjBackup"
(
  id numeric NOT NULL,
  json text,
  date date,
  "userId" numeric,
  CONSTRAINT "adjBckPK" PRIMARY KEY (id),
  CONSTRAINT "userFK" FOREIGN KEY ("userId")
      REFERENCES public."user" ("userId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."adjBackup"
  OWNER TO postgres;

-- Table: public.adjustements

-- DROP TABLE public.adjustements;

CREATE TABLE public.adjustements
(
  "idAdjustements" numeric NOT NULL,
  json text,
  "userId" numeric,
  CONSTRAINT "adjustementsPK" PRIMARY KEY ("idAdjustements"),
  CONSTRAINT "userFK" FOREIGN KEY ("userId")
      REFERENCES public."user" ("userId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.adjustements
  OWNER TO postgres;

-- Table: public.patient

-- DROP TABLE public.patient;

CREATE TABLE public.patient
(
  "patientId" numeric NOT NULL,
  "dependencyGrade" text,
  disease text,
  "userId" numeric,
  CONSTRAINT "patientPK" PRIMARY KEY ("patientId"),
  CONSTRAINT "userFK" FOREIGN KEY ("userId")
      REFERENCES public."user" ("userId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.patient
  OWNER TO postgres;

-- Table: public.responsible

-- DROP TABLE public.responsible;

CREATE TABLE public.responsible
(
  "responsibleId" numeric NOT NULL,
  "userId" numeric NOT NULL,
  CONSTRAINT "responsiblePK" PRIMARY KEY ("responsibleId"),
  CONSTRAINT "responsibleUserFK" FOREIGN KEY ("userId")
      REFERENCES public."user" ("userId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.responsible
  OWNER TO postgres;


-- Table: public."patientResponsible"

-- DROP TABLE public."patientResponsible";

CREATE TABLE public."patientResponsible"
(
  "patientResponsibleId" numeric NOT NULL,
  "responsibleId" numeric NOT NULL,
  "userId" numeric NOT NULL,
  CONSTRAINT "patientResponsiblePK" PRIMARY KEY ("patientResponsibleId"),
  CONSTRAINT "patientresponsibleFK" FOREIGN KEY ("responsibleId")
      REFERENCES public.responsible ("responsibleId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "userId" FOREIGN KEY ("userId")
      REFERENCES public."user" ("userId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."patientResponsible"
  OWNER TO postgres;

-- Table: public."auxiliar-data"

-- DROP TABLE public."auxiliar-data";

CREATE TABLE public."auxiliar-data"
(
  "idAuxiliarData" numeric NOT NULL,
  "patientId" numeric NOT NULL,
  "auxiliar-data" numeric,
  CONSTRAINT "auxiliarDataPK" PRIMARY KEY ("idAuxiliarData"),
  CONSTRAINT "patientDataFK" FOREIGN KEY ("patientId")
      REFERENCES public.patient ("patientId") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."auxiliar-data"
  OWNER TO postgres;