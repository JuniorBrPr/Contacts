package contacts.app.classes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * This class represents a contact.
 * It contains the fields that are common to all contacts.
 */
public abstract class Contact implements Serializable {
    private final long serialVersionUID = 1L;
    String phoneNumber;
    final LocalDateTime CREATED;
    LocalDateTime lastEdited;
    final Pattern NUMBER_PATTERN;

    Contact() {
        this.phoneNumber = "";
        this.CREATED = LocalDateTime.now();
        this.lastEdited = LocalDateTime.now();
        this.NUMBER_PATTERN = Pattern.compile(
                "^\\+?(\\([A-Za-z0-9]{2,}+\\)|[A-Za-z0-9]{2,}+)([ -]?([A-Za-z0-9]{2,}))*$|" +
                        "^\\+?([A-Za-z0-9]+[ -]?\\([A-Za-z0-9]{2,3}\\))([ -]?([A-Za-z0-9]{2,}))*$|" +
                        "^\\([A-Za-z0-9]{2,}\\)$|" +
                        "^\\d$");
    }

    /**
     * @return the phone number of the contact.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * @param phoneNumber the phone number to set.
     */
    void setPhoneNumber(String phoneNumber) {
        if (isValidNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
            System.out.println("Wrong number format!");
        }
    }

    /**
     * Checks if the phone number is valid.
     *
     * @param phoneNumber the phone number to check.
     * @return true if the phone number is valid, false otherwise.
     */
    boolean isValidNumber(String phoneNumber) {
        return this.NUMBER_PATTERN.matcher(phoneNumber).matches();
    }

    /**
     * Set the last edited time to the current time.
     */
    void setLastEdited() {
        this.lastEdited = LocalDateTime.now();
    }

    /**
     * @return the name of the contact.
     */
    public abstract String getName();
}

