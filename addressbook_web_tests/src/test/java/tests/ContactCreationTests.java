package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase{
    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>(List.of());
        for (var firstname : List.of("", "firstName")){
            for (var lastname : List.of("", "lastName")){
                for (var address: List.of("", "address")){
                    for (var mobileTelephone: List.of("", "mobileTelephone")){
                        for (var email: List.of("", "email")){
                            result.add(new ContactData(firstname, lastname, address, mobileTelephone, email));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++){
            result.add(new ContactData(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getContactsCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getContactsCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(new ContactData("lastname'", "firstname", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void cantCreateContacts(ContactData contact) {
        int contactCount = app.contacts().getContactsCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getContactsCount();
        Assertions.assertEquals(contactCount, newContactCount);
    }

    //@Test
    //public void canCreateContactWithOnlyName() {
    //    app.contacts().createContact(new ContactData().withName("Pavel"));
   // }

    //@Test
    //public void canCreateContact() {
    //    app.contacts().createContact(new ContactData("Ivan", "Ivanov","TestAddress", "11111", "Ivan@test.test"));
    //}
}
