package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void canCreateContactWithEmptyName() {
        //openNewContactPage();
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithOnlyName() {
        //openNewContactPage();
        app.contacts().createContact(new ContactData().withName("Pavel"));
    }

    @Test
    public void canCreateContact() {
        //openNewContactPage();
        app.contacts().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "Vanyusha",
                "Qa Engineer", "TestCompany", "TestAddress", "11111", "22222", "333333",
                "44444", "Ivan@test.test", "Ivan2@test.test", "Ivan3@test.test", "HomePage"));
    }
}
