-- Marko Golovko, PGA
-- Zadanie 1

SELECT id, displayname, reputation, count 
FROM users JOIN 
        (SELECT p.owneruserid, COUNT(p.id)
        FROM posts p JOIN postlinks ON postlinks.postid = p.id 
                     JOIN posts r ON r.id = relatedpostid
        WHERE postlinks.linktypeid = 3
        GROUP BY p.owneruserid) 
usercount ON users.id = owneruserid
ORDER BY count DESC, displayname
LIMIT 20;

-- Zadanie 2

SELECT u.id, u.displayname, u.reputation, count , avg
FROM users u JOIN
    (SELECT users.id as id, users.displayname,reputation, COUNT(comments.id) count , AVG(comments.score) avg
     FROM users JOIN posts ON owneruserid = users.id 
                LEFT JOIN  comments ON postid = posts.id  
     GROUP BY users.id) uca USING (id) JOIN badges ON userid = uca.id
WHERE name = 'Fanatic' AND count <= 100
ORDER BY count DESC, displayname
LIMIT 20;


-- Zadanie 3

ALTER TABLE users ADD PRIMARY KEY(id);

ALTER TABLE badges ADD FOREIGN KEY (userid) REFERENCES users (id);

ALTER TABLE posts DROP COLUMN viewcount;

DELETE FROM posts WHERE body = '' OR body IS NULL;

-- Zadanie 4

INSERT INTO posts (posttypeid, parentid, owneruserid, body, score, creationdate)
SELECT 3,postid, userid, text, score, creationdate FROM comments;



