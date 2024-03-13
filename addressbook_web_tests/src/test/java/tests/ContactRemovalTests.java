package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemovalContactTests() {
        if (app.isContactPresent()) {
            app.createContact(new ContactData());
        }
        app.removeContact();
    }
}

