CREATE TABLE PLAYER (
  player_no NUMBER NOT NULL PRIMARY KEY,
  id VARCHAR2(20) NOT NULL UNIQUE, 
  password VARCHAR2(20) NOT NULL,
  nickname VARCHAR2(20) NOT NULL
);

CREATE SEQUENCE player_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE;

 
CREATE OR REPLACE TRIGGER player_trigger
BEFORE INSERT ON PLAYER
FOR EACH ROW
BEGIN
  SELECT player_seq.NEXTVAL INTO :new.player_no FROM dual;
END;

CREATE TABLE STATUS (
   status_no      number NOT NULL PRIMARY KEY,
    player_no NUMBER NOT NULL,
   health number DEFAULT 100   NOT NULL,
   action         number  DEFAULT 20 NOT NULL,
   intelligence   number DEFAULT 20 NOT NULL,
    stress        number DEFAULT 30 NOT NULL,
   stage         number   DEFAULT 1 NOT NULL
);


CREATE SEQUENCE status_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
 

CREATE OR REPLACE TRIGGER status_trigger
BEFORE INSERT ON STATUS
FOR EACH ROW
BEGIN
  SELECT status_seq.NEXTVAL INTO :new.status_no FROM dual;
END;

CREATE OR REPLACE TRIGGER new_status_trigger
AFTER INSERT ON PLAYER
FOR EACH ROW
BEGIN
  INSERT INTO status(PLAYER_NO) VALUES (:new.player_no);
END;

CREATE TABLE quiz (
   quiz_no   number   NOT NULL primary key,
   quiz_type   varchar2(10)   NOT NULL,
   question   varchar2(400) NOT NULL,
   answer   varchar2(300) NOT NULL
);

CREATE SEQUENCE quiz_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
  
CREATE OR REPLACE TRIGGER quiz_trigger
BEFORE INSERT ON quiz
FOR EACH ROW
BEGIN
  SELECT quiz_seq.NEXTVAL INTO :new.quiz_no FROM dual;
END;

ALTER TABLE quiz ADD CONSTRAINT CHECK_QUIZ_TYPE CHECK(QUIZ_TYPE IN('normal','bonus','jobfair'));

CREATE TABLE  game (
   game_no   number   NOT NULL primary key,
   status_no   number   NOT NULL,
   player_no   number   NOT NULL
);

CREATE SEQUENCE game_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE;
  
CREATE OR REPLACE TRIGGER game_trigger
BEFORE INSERT ON game
FOR EACH ROW
BEGIN
  SELECT game_seq.NEXTVAL INTO :new.game_no FROM dual;
END;


ALTER TABLE status ADD CONSTRAINT FK_player_TO_status FOREIGN KEY (
   player_no
) REFERENCES player (
   player_no
);

ALTER TABLE game ADD CONSTRAINT FK_status_TO_game FOREIGN KEY (
   status_no
) REFERENCES status (
   status_no
);

ALTER TABLE game ADD CONSTRAINT FK_player_TO_game FOREIGN KEY (
   player_no
) REFERENCES player (
   player_no
);

drop table player;
drop table status;

CREATE OR REPLACE TRIGGER delete_status_trigger
AFTER DELETE ON PLAYER
FOR EACH ROW
BEGIN
  DELETE FROM status WHERE player_no = (:old.player_no);
END;

CREATE OR REPLACE TRIGGER save_game_trigger
AFTER UPDATE ON status
FOR EACH ROW
BEGIN
  INSERT INTO game(STATUS_NO, PLAYER_NO) VALUES (:old.status_no, :old.player_no);
END;

CREATE OR REPLACE TRIGGER delete_game_trigger
AFTER DELETE ON PLAYER
FOR EACH ROW
BEGIN
  DELETE FROM game WHERE player_no = (:old.player_no);
END;

select * from quiz where quiz_type = 'normal';

