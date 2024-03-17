package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemovalContactTests() {
        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData("firstname","","","",""));
        }
        app.contacts().removeContact();
    }

    @Test
    public void canRemovalAllContactsTests() {
        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData("firstname","","","",""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.contacts().getContactsCount());
    }
}

