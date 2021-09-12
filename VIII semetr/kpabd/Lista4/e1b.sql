-- dirty read Read uncommitted

BEGIN TRAN;
UPDATE users SET age = 21 WHERE id = 1;
WAITFOR DELAY '00:00:5';
ROLLBACK;

SELECT age FROM users WHERE id = 1;

--- Non-repeatable reads Read committed

BEGIN TRAN;
UPDATE users SET age = 21 WHERE id = 1;
COMMIT;

--- Non-repeatable reads Read committed

BEGIN TRAN;
INSERT INTO users(age) VALUES (27);
COMMIT;
