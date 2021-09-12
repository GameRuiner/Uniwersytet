db.readers.insert([
{
   "Pesel": "55101011111",
   "Surname": "Golovko",
   "City":  "Wrocław",
   "Birth_Date": "1998-10-10",
   "Borrowings": [
      {
         "Books_Copy_ID": ObjectId(),
         "Date": "2020-02-01",
         "Days_Number":  12
      },
      {
         "Books_Copy_ID": ObjectId(),
         "Date": "2020-01-05",
         "Days_Number":  20
      },
   ]
}
])