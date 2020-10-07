-- Marko Golovko, PGA

-- Zadanie 1

SELECT b.name as odznaka, COUNT(p.id) as liczba
FROM badges b LEFT JOIN users u ON (b.userid = u.id) 
LEFT JOIN posts p ON (p.owneruserid = u.id)
GROUP BY b.name
ORDER BY COUNT(p.id) DESC;

-- Zadanie 2

CREATE OR REPLACE FUNCTION z2() RETURNS SETOF text AS $X$
DECLARE
    t text;
    w text;
    res text;
    c int;
BEGIN
    FOR t IN
        SELECT text FROM comments
      LOOP
        c = 0;
        FOR w IN
            SELECT match[1] FROM regexp_matches(t, '\s+(\w{3,})\s+','g') as match
            LOOP
             IF char_length(w) >= c THEN 
                    c = char_length(w);
                    res = w;
             END IF;
             END LOOP;
         RETURN NEXT res;
       END LOOP;
END $X$ LANGUAGE plpgsql;

SELECT text 
FROM (SELECT z2 as text, COUNT(z2) FROM z2() GROUP BY text) as words 
WHERE count > 1 ORDER BY text;
                    

-- Zadanie 3

ALTER TABLE users
ADD CONSTRAINT unique_id UNIQUE (id);

ALTER TABLE posts 
ADD FOREIGN KEY (owneruserid) REFERENCES users(id);

UPDATE posts SET owneruserid = 0 WHERE owneruserid IS NULL;


