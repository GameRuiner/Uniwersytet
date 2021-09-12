count = db.test.count({});
skipCount = Math.ceil(count/2) - 1;
db.test.find().sort({ type: 1}).skip(skipCount).limit(2);

db.readers.find({"Borrowings.Days_Number": 45});