package contacts.app.classes;

import java.time.LocalDate;
import java.util.Objects;

import static java.lang.Integer.parseInt;

/**
 * This class represents a contact of a person.
 */
public class Person extends Contact {
    private String firstname;
    private String surname;
    private LocalDate birthDate;
    private String gender;

    Person() {
        super();
        this.birthDate = null;
    }

    /**
     * @param firstname First name of the person.
     */
    protected void setFirstname(String firstname) {
        this.firstname = firstname;
        setLastEdited();
    }

    /**
     * @param surname Surname of the person.
     */
    protected void setSurname(String surname) {
        this.surname = surname;
        setLastEdited();
    }

    /**
     * @param birthDate Birthdate of the person.
     */
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

    /**
     * @param gender The gender of the person.
     */
    protected void setGender(String gender) {
        if (Objects.equals(gender, "M")) {
            this.gender = gender;
        } else if (Objects.equals(gender, "F")) {
            this.gender = gender;
        } else {
            System.out.println("Invalid gender!");
            this.gender = null;
        }
        setLastEdited();
    }

    /**
     * @return The full name of the person.
     */
    @Override
    public String getName() {
        return this.firstname + " " + this.surname;
    }

    /**
     * @return The contact information of the person.
     */
    @Override
    public String toString() {
        String number = Objects.equals(this.getPhoneNumber(), "") ? "[no number]" : getPhoneNumber();
        String birthDate = this.birthDate == null ? "[no data]" : this.birthDate.toString();
        String gender = this.gender == null ? "[no data]" : this.gender;
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
}
