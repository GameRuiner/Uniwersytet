book_copy_1_id = ObjectId()
book_copy_2_id = ObjectId()
book_copy_3_id = ObjectId()

db.readers.insert([
{
   "Pesel": "55101011111",
   "Surname": "Kowalski",
   "City":  "Wrocław",
   "Birth_Date": "1955-10-10",
   "Borrowings": [
      {
         "Books_Copy_ID": book_copy_1_id,
         "Date": "2020-02-01",
         "Days_Number":  12
      },
      {
         "Books_Copy_ID": book_copy_3_id,
         "Date": "2020-01-05",
         "Days_Number":  20
      },
   ]
},
{
   "Pesel": "60101033333",
   "Surname": "Maliniak",
   "City":  "Wrocław",
   "Birth_Date": "1955-10-10",
   "Borrowings": [
      {
         "Books_Copy_ID": book_copy_2_id,
         "Date": "2020-01-21",
         "Days_Number":  45
      },
      {
         "Books_Copy_ID": book_copy_3_id,
         "Date": "2020-01-13",
         "Days_Number":  7
      },
   ]
}
])

db.books.insert([
{ 
  "ISBN": "83-246-0279-8", 
  "Title": "Microsoft Access. Podręcznik administratora", 
  "Autor": "Helen Feddema", 
  "Publishing_Year": 2006, 
  "Price": 69,
  "Book_Copy": [
      {"_id" : book_copy_1_id, "Signature": "S0001"},
      {"_id" : book_copy_2_id, "Signature": "S0002"}
  ]
},
{ 
  "ISBN": "83-246-0653-X", 
  "Title": "SQL Server 2005. Programowanie. Od podstaw", 
  "Autor": "Robert Vieira", 
  "Publishing_Year": 2007, 
  "Price": 97,
  "Book_Copy": [
   {"_id" : book_copy_3_id, "Signature": "S0003"}
  ]
}
])
