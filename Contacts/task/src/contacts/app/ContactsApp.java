package contacts.app;

import contacts.app.classes.Contact;
import contacts.app.classes.ContactManager;
import contacts.app.classes.SerializationUtils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a contact book.
 * It contains a list of contacts and methods to manipulate them.
 *
 * @author Junior Javier Brito Perez
 */
public class ContactsApp {
    private final ArrayList<Contact> contacts;
    private final Scanner in;
    private final ContactManager contactManager;
    private final String fileName;

    public ContactsApp(String fileName) {
        this.in = new Scanner(System.in);
        this.fileName = fileName;
        this.contacts = loadData();
        this.contactManager = new ContactManager(in);
        menu();
    }

    /**
     * Main menu of the app.
     */
    private void menu() {
        //noinspection InfiniteLoopStatement
        do {
            String menu = "\n[menu] Enter action (add, list, search, count, exit):";
            System.out.println(menu);
            String action = in.nextLine();
            switch (action) {
                case "add" -> addContact();
                case "count" -> countContacts();
                case "search" -> searchContacts();
                case "list" -> contactInfo();
                case "exit" -> exit();
                default -> {
                    System.out.println("Wrong action!");
                    menu();
                }
            }
        } while (true);
    }

    /**
     * Exit the program.
     */
    private void exit() {
        System.exit(0);
    }

    /**
     * Edit contact by index in the list of contacts.
     */
    private void editContact(int index) {
        if (!contacts.isEmpty()) {
            Contact contact = contacts.get(index);
            contactManager.editContact(contact);
            saveData();
        } else {
            System.out.println("No records to edit!");
        }

    }

    /**
     * Count contacts in the list of contacts.
     */
    private void countContacts() {
        System.out.println("The Phone Book has " + contacts.size() + " records.");
    }

    /**
     * Lists all contacts in the phone book.
     */
    private void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No records to list!");
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i).getName());
            }
        }
    }

    /**
     * List all contacts in the phone book.
     * The user can choose a contact to edit or delete.
     */
    private void contactInfo() {
        if (!contacts.isEmpty()) {
            listContacts();
            System.out.println("\n[list] Enter action ([number], back):");
            String action = in.nextLine();
            if (action.equals("back")) {
                menu();
            } else {
                try {
                    int index = Integer.parseInt(action) - 1;
                    if (index >= 0 && index < contacts.size()) {
                        recordMenu(index);
                    } else {
                        System.out.println("Wrong index!");
                        contactInfo();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Wrong index!");
                    contactInfo();
                }
            }
        } else {
            System.out.println("No records to list!");
        }
    }

    /**
     * Remove a contact from the phone book.
     */
    private void removeContact(int index) {
        if (!contacts.isEmpty()) {
            if (contacts.remove(index) != null) {
                System.out.println("The record removed!");
            } else {
                System.out.println("The record not removed!");
            }
        } else {
            System.out.println("No records to remove!");
        }
    }

    /**
     * Add a contact to the phone book.
     */
    private void addContact() {
        System.out.println("Enter the type (person, organization):");
        String type = in.nextLine();
        Contact contact = contactManager.createContact(type);

        if (contact != null) {
            contacts.add(contact);
            saveData();
            System.out.println("The record added.");
        } else {
            System.out.println("The record not added.");
        }
    }

    /**
     * Search contacts in the phone book.
     */
    private void searchContacts() {
        System.out.println("Enter search query:");
        String query = in.nextLine();
        ArrayList<Contact> searchResults = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(contact);
            } else if (contact.getPhoneNumber().contains(query)) {
                searchResults.add(contact);
            }
        }
        if (searchResults.isEmpty()) {
            System.out.println("No matching records found!");
        } else {
            System.out.println("Found " + searchResults.size() + " results:");
            for (int i = 0; i < searchResults.size(); i++) {
                System.out.println((i + 1) + ". " + searchResults.get(i).getName());
            }

            System.out.println("\n[search] Enter action ([number], back, again):");
            String action = in.nextLine();
            switch (action) {
                case "back" -> menu();
                case "again" -> searchContacts();
                default -> {
                    int index = Integer.parseInt(action) - 1;
                    if (index > searchResults.size() || index < 0) {
                        System.out.println("Wrong index!");
                        searchContacts();
                    }
                    System.out.println(searchResults.get(index));
                    recordMenu(contacts.indexOf(searchResults.get(index)));
                }
            }
        }
    }

    /**
     * Menu for when a contact is selected.
     *
     * @param index index of the contact in the list of contacts.
     */
    private void recordMenu(int index) {
        System.out.println(contacts.get(index));
        System.out.println("[record] Enter action (edit, delete, menu):");
        String action = in.nextLine();
        switch (action) {
            case "edit" -> editContact(index);
            case "delete" -> removeContact(index);
            case "menu" -> menu();
            default -> {
                System.out.println("Wrong action!");
                recordMenu(index);
            }
        }
    }

    /**
     * Save data to file.
     */
    private void saveData() {
        try {
            SerializationUtils.serialize(this.contacts, this.fileName);
        } catch (Exception e) {
            System.out.println("Error saving data! " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Load data from file.
     *
     * @return List of contacts from file.
     */
    private ArrayList<Contact> loadData() {
        try {
            return (ArrayList<Contact>) SerializationUtils.deserialize(this.fileName);
        } catch (Exception e) {
            System.out.println("Error loading data! " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
