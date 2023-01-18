package contacts.app.objects;

import java.util.Scanner;

public class CreateContact {
    private Scanner in = new Scanner(System.in);

    public Contact createContact(String type) {
        return switch (type) {
            case "person" -> createPerson();
            case "organization" -> createOrganization();
            default -> null;
        };
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
