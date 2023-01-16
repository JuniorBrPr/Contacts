package contacts.app;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class represents a contact book.
 * It contains a list of contacts and methods to manipulate them.
 *
 * @author Junior Javier Brito Perez
 */
public class ContactsApp {
    private static final String MENU = "Enter action (add, remove, edit, count, list, exit):";
    private final ArrayList<Contact> contacts = new ArrayList<>();
    private final Scanner in = new Scanner(System.in);

    public ContactsApp() {
        menu();
    }

    /**
     * Main menu of the app.
     */
    private void menu() {
        //noinspection InfiniteLoopStatement
        do {
            System.out.println(MENU);
            String action = in.nextLine();
            switch (action) {
                case "add" -> addContact();
                case "remove" -> removeContact();
                case "edit" -> editContact();
                case "count" -> countContacts();
                case "list" -> listContacts();
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
            System.out.println("Select a field (name, surname, number):");
            String field = in.nextLine();
            switch (field) {
                case "name" -> {
                    System.out.println("Enter name:");
                    contact.setName(in.nextLine());
                }
                case "surname" -> {
                    System.out.println("Enter surname:");
                    contact.setSurname(in.nextLine());
                }
                case "number" -> {
                    System.out.println("Enter number:");
                    contact.setPhoneNumber(in.nextLine());
                }
                default -> {
                    System.out.println("Wrong field!");
                    editContact();
                }
            }
            System.out.println("The record updated!");
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
                String number = Objects.equals(contacts.get(i).getPhoneNumber(), "") ?
                        "[no number]" :
                        contacts.get(i).getPhoneNumber();
                System.out.printf("%d. %s %s, %s \n",
                        i + 1,
                        contacts.get(i).getName(),
                        contacts.get(i).getSurname(),
                        number);
            }
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
        Contact newContact = new Contact();
        System.out.println("Enter the name:");
        newContact.setName(in.nextLine());
        System.out.println("Enter the surname:");
        newContact.setSurname(in.nextLine());
        System.out.println("Enter the number:\n");
        newContact.setPhoneNumber(in.nextLine());
        contacts.add(newContact);
        System.out.println("The record added.");
    }
}
