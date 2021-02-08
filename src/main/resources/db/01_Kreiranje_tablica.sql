-- ########################################
-- ARTIKL
-- ########################################
CREATE TABLE ARTIKL (
        ARTIKL_ID			NUMBER NOT NULL
,		SIFRA			    VARCHAR2(7 CHAR) NOT NULL
,		NAZIV 			    VARCHAR2(200 CHAR) NOT NULL
,       JEDINICA_MJERE      VARCHAR2(5 CHAR) NOT NULL -- 'KG', 'KOM'
,       STOPA_PDV           NUMBER (9,6) NOT NULL
,       NETO                NUMBER (15,3)
,       BRUTO               NUMBER (15,3)
);

ALTER TABLE ARTIKL
    ADD (CONSTRAINT ART_PK PRIMARY KEY
        (ARTIKL_ID));

ALTER TABLE ARTIKL
	ADD (CONSTRAINT ART_SIF_UK UNIQUE
	    (SIFRA));

ALTER TABLE ARTIKL
	 ADD (CONSTRAINT ART_CC1 CHECK (JEDINICA_MJERE IN ('KG', 'KOM')));


-- ########################################
-- BARKOD
-- ########################################
CREATE TABLE BARKOD (
        BARKOD_ID			NUMBER NOT NULL
,		ARTIKL_ID			NUMBER NOT NULL
,		KOD 			    VARCHAR2(2000 CHAR) NOT NULL
,       KOMADA              NUMBER (5) NOT NULL
);

ALTER TABLE BARKOD
    ADD (CONSTRAINT BAR_PK PRIMARY KEY
        (BARKOD_ID));

ALTER TABLE BARKOD
    ADD (CONSTRAINT ART_BAR_FK FOREIGN KEY
	    (ARTIKL_ID) REFERENCES ARTIKL (ARTIKL_ID));

ALTER TABLE BARKOD
	ADD (CONSTRAINT BAR_KOD_UK UNIQUE
	    (KOD));


-- ########################################
-- DOBAVLJAC
-- ########################################
CREATE TABLE DOBAVLJAC (
        DOBAVLJAC_ID		NUMBER NOT NULL
,		OIB			        VARCHAR2(11 CHAR) NOT NULL
,		NAZIV 			    VARCHAR2(200 CHAR) NOT NULL
,       ADRESA              VARCHAR2(200 CHAR) NOT NULL
,       STATUS				NUMBER(1) NOT NULL -- '0' - NEAKTIVAN, '1' - AKTIVAN
);

ALTER TABLE DOBAVLJAC
    ADD (CONSTRAINT DOB_PK PRIMARY KEY
        (DOBAVLJAC_ID));

ALTER TABLE DOBAVLJAC
	ADD (CONSTRAINT DOB_OIB_UK UNIQUE
	    (OIB));

ALTER TABLE DOBAVLJAC
	ADD (CONSTRAINT DOB_NAZ_UK UNIQUE
	    (NAZIV));

ALTER TABLE DOBAVLJAC
	 ADD (CONSTRAINT DOB_CC1 CHECK (STATUS IN (0, 1)));

-- ########################################
-- ARTIKL_DOBAVLJAC
-- ########################################
CREATE TABLE ARTIKL_DOBAVLJAC (
        ARTIKL_ID			NUMBER NOT NULL
,       DOBAVLJAC_ID        NUMBER NOT NULL
);

ALTER TABLE ARTIKL_DOBAVLJAC
    ADD (CONSTRAINT ART_DOB_PK PRIMARY KEY
        (ARTIKL_ID, DOBAVLJAC_ID));

ALTER TABLE ARTIKL_DOBAVLJAC
	ADD (CONSTRAINT ART_DOB_UK UNIQUE
	    (ARTIKL_ID, DOBAVLJAC_ID));

CREATE FUNCTION get_active_num(artikl_id IN NUMBER)
   RETURN NUMBER
   IS active_num NUMBER(10);
   BEGIN
      SELECT count(*)
      INTO active_num
      FROM artikl_dobavljac
      INNER JOIN dobavljac on artikl_dobavljac.dobavljac_id = dobavljac.dobavljac_id
      WHERE artikl.artikl_id = artikl_id
      AND dobavljac.status = 1;
      RETURN(active_num);
    END;
/

ALTER TABLE ARTIKL_DOBAVLJAC
	 ADD (CONSTRAINT ART_DOB_CC1 CHECK (get_active_num(ARTIKL_ID) > 0));