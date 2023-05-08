CREATE TABLE CARD_TRANSACTIONS(
ID_OPERATION NUMBER(10) PRIMARY KEY,
NUMBER_PAN NUMBER(19) NOT NULL,
REFERENCE_NUMBER NUMBER(6) NOT NULL,
TOTAL_AMOUNT NUMBER (10) NOT NULL,
ADRESS_OPERATION VARCHAR2(100) NOT NULL,
STATUS_OPERATION NUMBER(1,0) NOT NULL,
DATE_OPERATION DATE NOT NULL,

CONSTRAINT fk_card_bank FOREIGN KEY (NUMBER_PAN) REFERENCES CARD_BANK(NUMBER_PAN)
)


CREATE SEQUENCE CARD_TRANSACTIONS_SEQ INCREMENT BY 1 MINVALUE 1 MAXVALUE 999 NOCYCLE CACHE 20 NOORDER;

CREATE TRIGGER "CARD_BANK_TRG"
BEFORE INSERT ON CARD_TRANSACTIONS
FOR EACH ROW
BEGIN
	SELECT CARD_TRANSACTIONS_SEQ.nextval
	INTO :NEW.ID_OPERATION
	FROM dual;
END;