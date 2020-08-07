-- Marko Golovko, PGA
-- Zadanie 1
SELECT creationdate
FROM posts
WHERE body LIKE '%Turing%'
ORDER BY creationdate DESC;
-- Zadanie 2
SELECT id,title
FROM posts
WHERE creationdate > '2018-10-10' AND
      EXTRACT(MONTH FROM creationdate) BETWEEN 9 AND 12 AND
      title IS NOT NULL AND
      score >= 9
ORDER BY title;
-- Zadanie 3
SELECT DISTINCT displayname, reputation
FROM users JOIN posts ON users.id = posts.owneruserid JOIN comments ON posts.id = comments.postid
WHERE body LIKE '%deterministic%' AND
      text LIKE '%deterministic%'
ORDER BY reputation DESC;
-- Zadanie 4
(SELECT displayname
FROM posts JOIN users ON posts.owneruserid = users.id EXCEPT SELECT displayname
FROM comments JOIN users ON comments.userid = users.id) ORDER BY displayname LIMIT 10;
