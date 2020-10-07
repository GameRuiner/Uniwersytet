-- Marko Golovko, PGA
-- Zadanie 1

CREATE VIEW ranking AS 
(SELECT displayname, COUNT(A) as liczba_odpowiedzi
FROM users JOIN posts A ON (users.id = A.owneruserid) 
JOIN posts B ON (B.AcceptedAnswerID=A.id)
GROUP BY users.id, displayname
ORDER BY liczba_odpowiedzi DESC, displayname);

-- Zadanie 2
CREATE VIEW ue AS
(SELECT DISTINCT users.* 
                      FROM users 
                      JOIN badges ON (badges.userid = users.id) 
                      WHERE badges.name = 'Enlightened');
CREATE VIEW uwe AS
((SELECT * FROM users) EXCEPT (SELECT * FROM ue));

SELECT id, displayname, reputation FROM 
(SELECT *
FROM users u JOIN (SELECT u.id, count(c.id)
                 FROM uwe u JOIN comments c ON (c.userid = u.id)
                            JOIN posts p ON (p.id = c.postid)
                 WHERE EXTRACT(YEAR FROM p.creationdate) = 2020
                 GROUP BY u.id) comentscount USING (id), 
                (SELECT AVG(upvotes) FROM ue) avgue
WHERE  
        u.upvotes > avgue.avg AND count > 1
ORDER BY u.creationdate) uwr;


-- Zadanie 3

CREATE VIEW usersrec AS 
SELECT DISTINCT u.id, u.displayname
FROM users u JOIN posts p ON (u.id = p.owneruserid)
WHERE p.body LIKE '%recurrence%';

WITH RECURSIVE contact(id, displayname) AS (
SELECT id, displayname FROM usersrec
UNION
SELECT u.id, u.displayname 
FROM users u 
JOIN comments cm ON (cm.userid = u.id)                      
JOIN posts p ON (cm.postid = p.id)                      
JOIN contact c ON (c.id = p.owneruserid))
SELECT * FROM contact;
