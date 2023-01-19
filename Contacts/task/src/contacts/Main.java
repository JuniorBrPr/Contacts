package contacts;

import contacts.app.ContactsApp;

public class Main {
    public static void main(String[] args) {
        String filepath;
        if (args.length == 0) {
            filepath = System.getProperty("user.dir") + "\\contacts.db";
        } else {
            filepath = args[0];
        }
        ContactsApp contactsApp = new ContactsApp(filepath);
    }
}
