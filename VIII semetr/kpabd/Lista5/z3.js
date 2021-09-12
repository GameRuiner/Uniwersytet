db.runCommand( {
   collMod: "readers",
   validator: { $jsonSchema: {
      bsonType: "object",
      required: [ "Surname", "Pesel", "City"],
      properties: {
         Surname: {
            bsonType: "string",
            description: "must be a string and is required"
         },
         Pesel: {
            bsonType: "string",
            description: "must be a string and is required"
         },
         City: {
            bsonType: "string",
            description: "must be a string"
         },
         Borrowings: {
            bsonType: ["array"],
            items: {
               bsonType: ["object"],
               required: [ "Books_Copy_ID" ],
               properties: {
                  Books_Copy_ID: {
                     bsonType: "ObjectId"
                  },
                  Date: {
                     bsonType: "string"
                  },
                  Days_Number: {
                     bsonType: "Int32"
                  }
               }
            }
         }
      }
   } },
   validationLevel: "moderate"
} )



{
  $jsonSchema: {
    bsonType: 'object',
    required: [
      'Surname',
      'Pesel',
      'City'
    ],
    properties: {
      Surname: {
        bsonType: 'string',
        description: 'must be a string and is required'
      },
      Pesel: {
        bsonType: 'string',
        description: 'must be a string and is required'
      },
      City: {
        bsonType: 'string',
        description: 'must be a string'
      },
      Borrowings: {
        bsonType: [
          'array'
        ],
        items: {
          bsonType: [
            'object'
          ],
          required: [
            'Books_Copy_ID'
          ],
          properties: {
            Date: {
              bsonType: 'string'
            },
            Days_Number: {
              bsonType: 'number'
            }
          }
        }
      }
    }
  }
}