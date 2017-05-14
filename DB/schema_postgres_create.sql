CREATE TABLE "users" (
	"id" serial NOT NULL,
	"username" character varying(255) NOT NULL,
	"password" character varying(255) NOT NULL,
	"full_name" character varying(255) NOT NULL,
	"role" character varying(255) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "services" (
	"id" serial NOT NULL,
	"cost" integer NOT NULL,
	"period" integer NOT NULL,
	"title_en" character varying(255) NOT NULL,
	"title_ru" character varying(255) NOT NULL,
	CONSTRAINT services_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "payments" (
	"id" serial NOT NULL,
	"cost" integer NOT NULL,
	"paid" character(1) NOT NULL,
	"payment_date" DATE NOT NULL,
	"call_id" bigint NOT NULL,
	"contract_id" bigint NOT NULL,
	"user_id" bigint NOT NULL,
	CONSTRAINT payments_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "contracts" (
	"id" serial NOT NULL,
	"end_date" DATE NOT NULL,
	"service_id" bigint NOT NULL,
	"user_id" bigint NOT NULL,
	CONSTRAINT contracts_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "calls" (
	"id" serial NOT NULL,
	"cost" integer NOT NULL,
	"date_call" DATE NOT NULL,
	"time_call" TIME NOT NULL,
	"user_id" bigint NOT NULL,
	CONSTRAINT calls_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "log4j_messages" (
	"message" character varying(2000) NOT NULL,
	"class" character varying(255) NOT NULL,
	"priority" character varying(64) NOT NULL,
	"log_date" TIMESTAMP NOT NULL
) WITH (
  OIDS=FALSE
);





ALTER TABLE "payments" ADD CONSTRAINT "payments_fk0" FOREIGN KEY ("call_id") REFERENCES "calls"("id");
ALTER TABLE "payments" ADD CONSTRAINT "payments_fk1" FOREIGN KEY ("contract_id") REFERENCES "contracts"("id");
ALTER TABLE "payments" ADD CONSTRAINT "payments_fk2" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "contracts" ADD CONSTRAINT "contracts_fk0" FOREIGN KEY ("service_id") REFERENCES "services"("id");
ALTER TABLE "contracts" ADD CONSTRAINT "contracts_fk1" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "calls" ADD CONSTRAINT "calls_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");


