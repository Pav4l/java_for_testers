package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{
    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        /*for (int i = 0; i < 5; i++){
            result.add(new ContactData()
                    .withName(randomString(i * 10))
                    .withLastName(randomString(i * 10)));
        }*/
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact
                .withId(newContacts.get(newContacts.size() - 1).id())
                //.withName(contact.firstName())
                //.withLastName(contact.lastName())
                //.withPhoto(contact.photo())
                );
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    /*
    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(new ContactData("", "firstname", "lastName'","", "", "","")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void cantCreateContacts(ContactData contact) {
        int contactCount = app.contacts().getContactsCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getContactsCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }*/

    //@Test
    //public void canCreateContactWithOnlyName() {
    //    app.contacts().createContact(new ContactData().withName("Pavel"));
    // }

    @Test
    void canCreateContact() {
        var contact = new ContactData()
                .withName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContact(contact);
    }

    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }
}
