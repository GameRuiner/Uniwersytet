-- zadanie 1
CREATE OR REPLACE FUNCTION pierwszy_zapis(int,int) RETURNS timestamp with time zone AS $X$
    SELECT data
    FROM wybor w JOIN uzytkownik u USING (kod_uz) 
         JOIN grupa g USING (kod_grupy) 
         JOIN przedmiot_semestr s USING (kod_przed_sem)
    WHERE u.kod_uz=$1 and semestr_id=$2;
$X$
LANGUAGE SQL STABLE;

SELECT DISTINCT nazwisko, pierwszy_zapis(u.kod_uz,semestr_id) AS pz
FROM uzytkownik u JOIN wybor w USING (kod_uz)
    JOIN grupa g USING (kod_grupy) 
    JOIN przedmiot_semestr ps USING (kod_przed_sem) 
    JOIN semestr s USING (semestr_id)
WHERE nazwisko LIKE 'A%' AND s.nazwa = 'Semestr zimowy 2016/2017'
ORDER BY pz;


-- zadanie 2
CREATE TYPE plan_zajec AS (nazwa text, rodzaj character(1), termin character(13), sala character varying(3), liczba smallint);

CREATE OR REPLACE FUNCTION plan_zajec_prac(int,int) 
RETURNS SETOF plan_zajec 
AS $X$
    SELECT p.nazwa, rodzaj_zajec, termin, sala, max_osoby
    FROM uzytkownik u JOIN grupa g USING (kod_uz) 
        JOIN przedmiot_semestr ps USING (kod_przed_sem) 
        JOIN przedmiot p USING (kod_przed)
        WHERE u.kod_uz=$1 AND semestr_id=$2;
$X$
LANGUAGE SQL;

SELECT * FROM plan_zajec_prac(187,39); 
