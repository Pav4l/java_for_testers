package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase{

    @Test
    void canRemovalContactTests() {
        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData().withName("name").withLastName("lastName"));
        }
        var oldContacts = app.contacts().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemovalAllContactsTests() {
        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData("id","firstname","","","","",""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.contacts().getContactsCount());
    }
}

