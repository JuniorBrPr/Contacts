# Contacts
A Java-based contact book application.

---
## Features
- Add a contact (person or organization)
- Edit a contact
- List all contacts
- Search for a specific contact
- Remove a contact
- Count total number of contacts
- Exit and save data

---
## How to run
Compile the project and run the main class, passing the file name to be used to store the contacts as a parameter,
will use the default cotacts.db file if no parameter is given.

---
## Package Structure
The main package is contacts.app which contains the following classes:
- **ContactsApp** : main class of the application.
- **Contact** : abstract class to represent a contact.
- **Person** : concrete class to represent a person contact.
- **Organization** : concrete class to represent an organization contact.
- **ContactManager** : class to manage the creation and edition of contacts.
- **SerializationUtils** : class to save and load data from disk.

---
### Author
*Junior Javier Brito Perez*
