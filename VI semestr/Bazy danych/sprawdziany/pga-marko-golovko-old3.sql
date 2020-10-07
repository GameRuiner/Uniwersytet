-- Marko Golovko, PGA

-- Zadanie 1
ALTER TABLE comments ADD COLUMN lasteditdate timestamp NOT NULL DEFAULT now();

UPDATE comments SET lasteditdate = creationdate;

CREATE TABLE commenthistory (
    id SERIAL PRIMARY KEY,
    commentid integer,
    creationdate timestamp,
    text text);
    
-- Zadanie 2


CREATE OR REPLACE FUNCTION comment_edit() RETURNS trigger AS $comment_edit$
    BEGIN
        NEW.creationdate := OLD.creationdate;
        
        IF NEW.id <> OLD.id OR
           NEW.postid <> OLD.postid OR
           NEW.lasteditdate <> OLD.lasteditdate THEN
               RAISE EXCEPTION ' error in UPDATE';
        END IF;
        
        IF NEW.text NOT LIKE OLD.text THEN
            NEW.lasteditdate = now();
            INSERT INTO commenthistory 
                VALUES (
                    default, OLD.id, 
                    OLD.lasteditdate, 
                    OLD.text);
        END IF;
        RETURN NEW;
    END;
$comment_edit$ LANGUAGE plpgsql;

CREATE TRIGGER comment_edit BEFORE UPDATE ON comments FOR EACH ROW EXECUTE PROCEDURE comment_edit();
 
    
-- Zadanie 3


CREATE OR REPLACE FUNCTION comment_insert() RETURNS trigger AS $comment_insert$
    BEGIN
    NEW.lasteditdate =  NEW.creationdate;
    RETURN NEW;
    END;
$comment_insert$ LANGUAGE plpgsql;

CREATE TRIGGER comment_insert BEFORE INSERT ON comments FOR EACH ROW EXECUTE PROCEDURE comment_insert();


