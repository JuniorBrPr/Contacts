package contacts.app;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * This class represents a contact.
 * It contains the name, surname, phone number and email of a contact.
 *
 * @author Junior Javier Brito Perez
 */
public class Contact {
    private String name;
    private String surname;
    private String phoneNumber;
    private final Pattern NUMBER_PATTERN = Pattern.compile(
            "^\\+?(\\([A-Za-z0-9]+\\)|[A-Za-z0-9]+)([ -]?([A-Za-z0-9]{2,}))*$|" +
                    "^\\+?([A-Za-z0-9]{2,}[ -]?\\([A-Za-z0-9]{2,3}\\))([ -]?([A-Za-z0-9]{2,}))*$|" +
                    "^\\([A-Za-z0-9]{2,}\\)$|" +
                    "^\\d$");

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(isValidNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
            System.out.println("Wrong number format!");
        }
    }

    private boolean isValidNumber(String phoneNumber) {
        return NUMBER_PATTERN.matcher(phoneNumber).matches();
    }


    @Override
    public String toString() {
        String number = Objects.equals(phoneNumber, "") ? "[no number]" : phoneNumber;
        return "Contact{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + number + '\'' +
                '}';
    }
}
