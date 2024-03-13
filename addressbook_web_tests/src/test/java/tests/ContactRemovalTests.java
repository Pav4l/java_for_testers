package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemovalContactTests() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData());
        }
        app.contacts().removeContact();
    }
}

