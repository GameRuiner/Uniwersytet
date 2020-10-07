1.
1) 

CREATE DOMIAN semestry AS text 
CHECK(VALUE IN ('zimowy', 'letni') AND NOT NULL);

2) 

CREATE SEQUENCE numer_semestru;

SELECT setval('numer_semestru', max(semestr_id)) 
FROM semestr;

ALTER TABLE semestr 
ALTER COLUMN semestr_id 
SET DEFAULT nextval('numer_semestru');

ALTER SEQUENCE numer_semestru OWNED BY semestr.semestr_id;

3)

ALTER TABLE semestr ADD COLUMN semestr semestry DEFAULT 'zimowy';

ALTER TABLE semestr ADD COLUMN rok char(9);

4)

UPDATE semestr SET semestr = 'letni' WHERE nazwa LIKE '%letni%';

UPDATE semestr SET rok = substring(nazwa FROM position('2' IN nazwa));

5)

ALTER TABLE semestr DROP COLUMN nazwa;

6)

ALTER TABLE semestr ALTER COLUMN semestr DROP DEFAULT;

ALTER TABLE semestr ALTER COLUMN semestr SET DEFAULT
CASE WHEN EXTRACT(month FROM current_date) BETWEEN 1 AND 6
THEN 'letni' ELSE 'zimowy' END;

ALTER TABLE semestr ALTER COLUMN rok SET DEFAULT
CASE WHEN EXTRACT(month FROM current_date) BETWEEN 1 AND 6
THEN EXTRACT(year FROM current_date)-1 || '/' ||
EXTRACT(year FROM current_date) ELSE
EXTRACT(year FROM current_date) || '/' || 
EXTRACT(year FROM current_date) + 1 END;


2.

0)

INSERT INTO semestr(semestr,rok) VALUES
   ('letni','2018/2019'),
   ('zimowy','2018/2019');

1)

CREATE SEQUENCE numer_przedmiot_semestr;
CREATE SEQUENCE numer_grupy;

SELECT setval('numer_przedmiot_semestr', max(kod_przed_sem)) FROM przedmiot_semestr;
SELECT setval ('numer_grupy', max(kod_grupy)) FROM grupa;

ALTER TABLE przedmiot_semestr
ALTER COLUMN kod_przed_sem
SET DEFAULT nextval('numer_przedmiot_semestr');
ALTER TABLE grupa            
ALTER COLUMN kod_grupy    
SET DEFAULT nextval('numer_grupy');

ALTER SEQUENCE numer_przedmiot_semestr OWNED BY przedmiot_semestr.kod_przed_sem;
ALTER SEQUENCE numer_grupy OWNED BY grupa.kod_grupy;

2)

INSERT INTO przedmiot_semestr (semestr_id, kod_przed, strona_domowa, angielski) SELECT semestr_id, kod_przed, strona_domowa, angielski                                                          
FROM semestr s JOIN przedmiot_semestr ps USING (semestr_id) JOIN przedmiot p USING (kod_przed)
WHERE s.rok = '2016/2017' AND (p.rodzaj = 'o' OR p.rodzaj = 'p');

3)

INSERT INTO grupa (max_osoby, rodzaj_zajec)
SELECT 100,rodzaj FROM przedmiot;

ALTER TABLE grupa ALTER COLUMN kod_uz DROP NOT NULL;
ALTER TABLE grupa ALTER COLUMN kod_przed_sem DROP NOT NULL;

INSERT INTO grupa (max_osoby, rodzaj_zajec)
SELECT 100,rodzaj FROM przedmiot;

4)

SELECT kod_uz, max_osoby FROM grupa WHERE kod_uz IS NULL AND max_osoby = 100;

3.

1)

CREATE TABLE student (
kod_uz integer NOT NULL,
imie character varying(15) NOT NULL,
nazwisko character varying(30) NOT NULL,
semestr smallint);

CREATE TABLE pracownik (
kod_uz integer NOT NULL,
imie character varying(15) NOT NULL,
nazwisko character varying(30) NOT NULL,
semestr smallint);

ALTER TABLE pracownik DROP COLUMN semestr;

ALTER TABLE student ADD CONSTRAINT pk_student PRIMARY KEY(kod_uz);
ALTER TABLE pracownik ADD CONSTRAINT pk_pracownik PRIMARY KEY(kod_uz);

2)

INSERT INTO pracownik(kod_uz, imie, nazwisko) SELECT DISTINCT kod_uz, imie, nazwisko
FROM uzytkownik JOIN grupa USING(kod_uz);

3)

INSERT INTO student(kod_uz, imie, nazwisko, semestr) SELECT DISTINCT kod_uz, imie, nazwisko, semestr
FROM uzytkownik JOIN wybor USING (kod_uz);

4)

ALTER TABLE grupa DROP CONSTRAINT fk_grupa_uz;

ALTER TABLE wybor DROP CONSTRAINT fk_wybor_uz;

ALTER TABLE grupa ADD CONSTRAINT 

ALTER TABLE wybor
ADD CONSTRAINT fk_wybor_uz FOREIGN KEY (kod_uz) 
REFERENCES student(kod_uz) DEFERRABLE;

ALTER TABLE grupa
ADD CONSTRAINT fk_grupa_uz FOREIGN KEY (kod_uz) 
REFERENCES pracownik(kod_uz) DEFERRABLE;

DROP TABLE uzytkownik;


4.

(SELECT kod_uz, imie, nazwisko
FROM pracownik JOIN grupa USING (kod_uz)
WHERE rodzaj_zajec = 'w') EXCEPT (SELECT kod_uz, imie, nazwisko
FROM pracownik JOIN grupa USING (kod_uz)
WHERE rodzaj_zajec = 's');

5.

SELECT nazwa, COUNT(DISTINCT wybor.kod_uz)
FROM przedmiot 
LEFT JOIN przedmiot_semestr USING (kod_przed) 
LEFT JOIN grupa USING (kod_przed_sem) 
LEFT JOIN wybor USING(kod_grupy)
WHERE rodzaj= 'k'
GROUP BY nazwa, kod_przed;


6.

SELECT DISTINCT kod_uz, imie, nazwisko
FROM pracownik JOIN grupa USING (kod_uz) JOIN
(SELECT grupa.kod_uz, kod_grupy, count(DISTINCT wybor.kod_uz)
FROM grupa JOIN wybor USING (kod_grupy)
GROUP BY grupa.kod_uz, kod_grupy) kkc USING (kod_uz)
WHERE count > max_osoby AND grupa.kod_grupy = kkc.kod_grupy;

SELECT DISTINCT pracownik.kod_uz, imie, nazwisko 
FROM wybor JOIN grupa USING(kod_grupy)
JOIN pracownik ON (pracownik.kod_uz = grupa.kod_uz)
GROUP BY grupa.kod_grupy, pracownik.kod_uz, imie, nazwisko, max_osoby
HAVING max_osoby < COUNT(wybor.kod_uz);


