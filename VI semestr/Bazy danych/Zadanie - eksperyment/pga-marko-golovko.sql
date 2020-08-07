-- 51,GIST,box_ops,<@                    -- Marko Golovko, 297354
-- Jedną z zalet GiST jest to, że umożliwia tworzenie niestandardowych typów danych za pomocą odpowiednich metod dostępu.
-- Indeksy GiST są najbardziej przydatne, gdy masz dane, które mogą w pewien sposób pokrywać się z wartością tej samej kolumny, ale z innego wiersza.
-- Najczęstsze typy danych, w których są wykorzystywane indeksy GiST, to: typy geometrii i tekst podczas wyszukiwania pełnotekstowego
-- Operator <@ (contained in), pokazuję relację zawierania dla wartości typu box

CREATE TABLE boxes(id SERIAL, box box);

INSERT INTO boxes(box)
SELECT box(point(n*random()/1000,n*random()/1000),point(n*random()/1000,n*random()/1000))
FROM generate_series(1,10000000) AS n;

 VACUUM ANALYZE;
 
 
EXPLAIN ANALYZE SELECT box 
					 FROM boxes
					 WHERE box <@ box(point(0,0),point(0.4,0.4));

 /*
 Gather  (cost=1000.00..137417.75 rows=10000 width=32) (actual time=316.182..1164.586 rows=525 loops=1)
   Workers Planned: 2
   Workers Launched: 2
   ->  Parallel Seq Scan on boxes  (cost=0.00..135417.75 rows=4167 width=32) (actual time=216.009..1038.953 rows=175 loops=3)
         Filter: (box <@ '(0.4,0.4),(0,0)'::box)
         Rows Removed by Filter: 3333158
 Planning time: 0.638 ms
 Execution time: 1164.645 ms
(8 rows) */

EXPLAIN ANALYZE SELECT box 
					 FROM boxes
					 WHERE box <@ box(point(0,0),point(4000,4000));

 /*					 
 Gather  (cost=1000.00..137417.75 rows=10000 width=32) (actual time=0.959..2636.753 rows=5248294 loops=1)
   Workers Planned: 2
   Workers Launched: 2
   ->  Parallel Seq Scan on boxes  (cost=0.00..135417.75 rows=4167 width=32) (actual time=0.608..1175.610 rows=1749431 loops=3)
         Filter: (box <@ '(4000,4000),(0,0)'::box)
         Rows Removed by Filter: 1583902
 Planning time: 1.280 ms
 Execution time: 2850.416 ms
(8 rows) */

CREATE INDEX box_ind ON boxes USING GIST(box);

 VACUUM ANALYZE;
 
 EXPLAIN ANALYZE SELECT box 
					 FROM boxes
					 WHERE box <@ box(point(0,0),point(0.4,0.4));

 /*
 Index Only Scan using box_ind on boxes  (cost=0.42..647.42 rows=10000 width=32) (actual time=0.907..8.501 rows=525 loops=1)
   Index Cond: (box <@ '(0.4,0.4),(0,0)'::box)
   Heap Fetches: 0
 Planning time: 78.773 ms
 Execution time: 8.594 ms
(5 rows) */

EXPLAIN ANALYZE SELECT box 
					 FROM boxes
					 WHERE box <@ box(point(0,0),point(4000,4000));


 /*
 Index Only Scan using box_ind on boxes  (cost=0.42..647.42 rows=10000 width=32) (actual time=0.434..4028.388 rows=5248294 loops=1)
   Index Cond: (box <@ '(4000,4000),(0,0)'::box)
   Heap Fetches: 0
 Planning time: 0.129 ms
 Execution time: 4147.362 ms
(5 rows) */


 