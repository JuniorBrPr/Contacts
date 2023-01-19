package contacts.app.classes;

import java.util.Scanner;

/**
 * This class handles the creation and editing of contacts.
 */
public class ContactManager {
    private final Scanner in;

    public ContactManager(Scanner in) {
        this.in = in;
    }

    /**
     * @param contact Contact to be edited.
     */
    public void editContact(Contact contact){
        if (contact instanceof Person) {
            editPerson((Person) contact);
        } else if (contact instanceof Organization) {
            editOrganization((Organization) contact);
        }
    }

    /**
     * Create a new contact.
     *
     * @param type Type of contact to be created.
     * @return Contact created.
     */
    public Contact createContact(String type) {
        return switch (type) {
            case "person" -> createPerson();
            case "organization" -> createOrganization();
            default -> {
                System.out.println("Wrong type!");
                yield null;
            }
        };
    }

    /**
     * Edit an organization's contact details.
     *
     * @param contact Organization contact to be edited.
     */
    private void editOrganization(Organization contact) {
        System.out.println("Select a field (name, address, number):");
        String field = in.nextLine();
        switch (field) {
            case "name" -> {
                System.out.println("Enter name:");
                contact.setName(in.nextLine());
            }
            case "address" -> {
                System.out.println("Enter address:");
                contact.setAddress(in.nextLine());
            }
            case "number" -> {
                System.out.println("Enter number:");
                contact.setPhoneNumber(in.nextLine());
            }
            default -> {
                System.out.println("Wrong field!");
                return;
            }
        }
        System.out.println("The record updated!");
    }

    /**
     * Edit a person's contact details.
     *
     * @param contact Person contact to be edited.
     */
    private void editPerson(Person contact) {
        System.out.println("Select a field (name, surname, birth, gender, number):");
        String field = in.nextLine();
        switch (field) {
            case "name" -> {
                System.out.println("Enter name:");
                contact.setFirstname(in.nextLine());
            }
            case "surname" -> {
                System.out.println("Enter surname:");
                contact.setSurname(in.nextLine());
            }
            case "birth" -> {
                System.out.println("Enter birthdate (YYYY-MM-DD):");
                contact.setBirthDate(in.nextLine());
            }
            case "gender" -> {
                System.out.println("Enter gender (M, F):");
                contact.setGender(in.nextLine());
            }
            case "number" -> {
                System.out.println("Enter number:");
                contact.setPhoneNumber(in.nextLine());
            }
            default -> {
                System.out.println("Wrong field!");
                return;
            }
        }
        System.out.println("The record updated!");
    }

    /**
     * Create a new person contact.
     *
     * @return Person contact created.
     */
    private Contact createPerson() {
        Person person = new Person();

        System.out.println("Enter the name:");
        person.setFirstname(in.nextLine());
        System.out.println("Enter the surname:");
        person.setSurname(in.nextLine());
        System.out.println("Enter birthdate (YYYY-MM-DD):");
        person.setBirthDate(in.nextLine());
        System.out.println("Enter the gender (M, F):");
        person.setGender(in.nextLine());
        System.out.println("Enter the number:");
        person.setPhoneNumber(in.nextLine());

        return person;
    }

    /**
     * Create a new organization contact.
     *
     * @return Organization contact created.
     */
    private Contact createOrganization() {
        Organization organization = new Organization();

        System.out.println("Enter the organization name:");
        organization.setName(in.nextLine());
        System.out.println("Enter the address:");
        organization.setAddress(in.nextLine());
        System.out.println("Enter the number:");
        organization.setPhoneNumber(in.nextLine());

        return organization;
    }
}
