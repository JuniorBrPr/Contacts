package contacts.app.objects;

import java.util.Scanner;

public class ContactManager {
    private final Scanner in;

    public ContactManager(Scanner in) {
        this.in = in;
    }

    public void editContact(Contact contact){
        if (contact instanceof Person) {
            editPerson((Person) contact);
        } else if (contact instanceof Organization) {
            editOrganization((Organization) contact);
        }
    }

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

    private void editOrganization(Organization contact) {
        System.out.println("Select a field (address, number):");
        String field = in.nextLine();
        switch (field) {
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
                System.out.println("Enter birth date (YYYY-MM-DD):");
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

    private Contact createPerson() {
        Person person = new Person();

        System.out.println("Enter the name:");
        person.setFirstname(in.nextLine());
        System.out.println("Enter the surname:");
        person.setSurname(in.nextLine());
        System.out.println("Enter the birth date:");
        person.setBirthDate(in.nextLine());
        System.out.println("Enter the gender (M, F):");
        person.setGender(in.nextLine());
        System.out.println("Enter the number:");
        person.setPhoneNumber(in.nextLine());

        return person;
    }

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
