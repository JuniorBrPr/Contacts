package contacts.app.classes;

import java.util.Objects;

/**
 * This class represents an organization contact.
 */
public class Organization extends Contact {
    private String name;
    private String address;

    Organization() {
        super();
        this.address = "";
    }

    /**
     * @param name the name to set.
     */
    protected void setName(String name) {
        this.name = name;
        setLastEdited();
    }

    /**
     * @param address the address to set.
     */
    protected void setAddress(String address) {
        this.address = address;
        setLastEdited();
    }

    /**
     * @return the name of the organization.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return the contact information of the organization.
     */
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
