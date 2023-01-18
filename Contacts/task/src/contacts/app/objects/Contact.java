package contacts.app.objects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

/**
 * This class represents a contact.
 * It contains the fields that are common to all contacts.
 *
 * @author Junior Javier Brito Perez
 */
public abstract class Contact {
    String phoneNumber;
    final LocalDateTime CREATED;
    LocalDateTime lastEdited;
    final Pattern NUMBER_PATTERN;

    Contact() {
        this.phoneNumber = "";
        this.CREATED = LocalDateTime.now();
        this.lastEdited = LocalDateTime.now();
        this.NUMBER_PATTERN = Pattern.compile(
                "^\\+?(\\([A-Za-z0-9]+\\)|[A-Za-z0-9]+)([ -]?([A-Za-z0-9]{2,}))*$|" +
                        "^\\+?([A-Za-z0-9]{2,}[ -]?\\([A-Za-z0-9]{2,3}\\))([ -]?([A-Za-z0-9]{2,}))*$|" +
                        "^\\([A-Za-z0-9]{2,}\\)$|" +
                        "^\\d$");
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        if (isValidNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
            System.out.println("Wrong number format!");
        }
    }

    boolean isValidNumber(String phoneNumber) {
        return this.NUMBER_PATTERN.matcher(phoneNumber).matches();
    }

    LocalDateTime getCreated() {
        return this.CREATED;
    }

    LocalDateTime getLastEdited() {
        return this.lastEdited;
    }

    void setLastEdited() {
        this.lastEdited = LocalDateTime.now();
    }
}

class Person extends Contact {
    private String firstname;
    private String surname;
    private LocalDate birthDate;
    private Gender gender;

    Person() {
        super();
        this.birthDate = null;
        this.gender = null;
    }

    protected void setFirstname(String firstname) {
        this.firstname = firstname;
        setLastEdited();
    }

    protected void setSurname(String surname) {
        this.surname = surname;
        setLastEdited();
    }

    protected void setBirthDate(String birthDate) {
        if (birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            this.birthDate = LocalDate.of(
                    parseInt(birthDate.split("-")[0]),
                    parseInt(birthDate.split("-")[1]),
                    parseInt(birthDate.split("-")[2]));
        } else {
            System.out.println("Bad birth date!");
            this.birthDate = null;
        }
        setLastEdited();
    }

    protected void setGender(String gender) {
        if (Objects.equals(gender, "M")) {
            this.gender = Gender.M;
        } else if (Objects.equals(gender, "F")) {
            this.gender = Gender.F;
        } else {
            System.out.println("Bad gender!");
            this.gender = null;
        }
        setLastEdited();
    }

    @Override
    public String toString() {
        String number = Objects.equals(this.getPhoneNumber(), "") ? "[no number]" : getPhoneNumber();
        String birthDate = this.birthDate == null ? "[no data]" : this.birthDate.toString();
        String gender = this.gender == null ? "[no data]" : this.gender.toString();
        return """
                Name: %s
                Surname: %s
                Birth date: %s
                Gender: %s
                Number: %s
                Time created: %s
                Time last edit: %s
                """.formatted(
                this.firstname,
                this.surname,
                birthDate,
                gender,
                number,
                this.CREATED,
                this.lastEdited
        );
    }

    enum Gender {
        M, F
    }
}

class Organization extends Contact {
    private String name;
    private String address;

    Organization() {
        super();
        this.address = "";
    }

    protected void setName(String name) {
        this.name = name;
        setLastEdited();
    }

    protected void setAddress(String address) {
        this.address = address;
        setLastEdited();
    }

    @Override
    public String toString() {
        String number = Objects.equals(this.getPhoneNumber(), "") ? "[no number]" : getPhoneNumber();
        String address = Objects.equals(this.address, "") ? "[no data]" : this.address;
        return """
                Organization name: %s
                Address: %s
                Number: %s
                Time created: %s
                Time last edit: %s
                """.formatted(
                this.name,
                address,
                number,
                this.CREATED,
                this.lastEdited
        );
    }
}
