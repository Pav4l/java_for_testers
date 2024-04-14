package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase{

    @Test
    void canRemovalContactTests() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("","name","lastname","", "","", "", "", "", "", "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemovalAllContactsTests() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("","name","lastname","", "","", "", "", "", "", "", ""));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.contacts().getContactsCount());
    }

    @Test
    public void canRemoveContactsFromGroup() {
        var rnd = new Random();
        var contact = new ContactData()
                .withName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));

        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(contact);
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "name", "header", "footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        app.contacts().createContact(contact, group);
        var oldRelated = app.hbm().getContactsInGroup(group);
        var index = rnd.nextInt(oldRelated.size());
        var randomContact = oldRelated.get(index);
        app.contacts().removeContactFromGroup(group, randomContact);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
    }
}

