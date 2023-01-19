package contacts.app;

import contacts.app.objects.Contact;
import contacts.app.objects.ContactManager;

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

    public ContactsApp() {
        this.in = new Scanner(System.in);
        this.contacts = new ArrayList<>();
        this.contactManager = new ContactManager(in);
        menu();
    }

    /**
     * Main menu of the app.
     */
    private void menu() {
        //noinspection InfiniteLoopStatement
        do {
            String menu = "\nEnter action (add, remove, edit, count, info, exit):";
            System.out.println(menu);
            String action = in.nextLine();
            switch (action) {
                case "add" -> addContact();
                case "remove" -> removeContact();
                case "edit" -> editContact();
                case "count" -> countContacts();
                case "info" -> contactInfo();
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
    private void editContact() {
        if (!contacts.isEmpty()) {
            listContacts();
            System.out.println("Select a record:");
            int index = Integer.parseInt(in.nextLine());
            if (index > contacts.size() || index < 1) {
                System.out.println("Wrong index!");
                editContact();
            }
            Contact contact = contacts.get(index - 1);
            contactManager.editContact(contact);
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
     * List all contacts in the phone book.
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

    private void contactInfo() {
        if (!contacts.isEmpty()) {
            listContacts();
            System.out.println("Enter index to show info:");
            int index = Integer.parseInt(in.nextLine());
            if (index > contacts.size() || index < 1) {
                System.out.println("Wrong index!");
                contactInfo();
            }
            Contact contact = contacts.get(index - 1);
            System.out.println(contact);
        } else {
            System.out.println("No records to list!");
        }
    }

    /**
     * Remove a contact from the phone book.
     */
    private void removeContact() {
        if (!contacts.isEmpty()) {
            listContacts();
            System.out.println("Select a record:");
            int index = in.nextInt();
            if (index > contacts.size() || index < 1) {
                System.out.println("Wrong index!");
                removeContact();
            }
            if (contacts.remove(index - 1) != null) {
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
            System.out.println("The record added.");
        } else {
            System.out.println("The record not added.");
        }
    }
}
