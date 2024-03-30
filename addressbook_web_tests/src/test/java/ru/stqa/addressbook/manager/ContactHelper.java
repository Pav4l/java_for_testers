package ru.stqa.addressbook.manager;

import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        openAddNewPage();
        fillContactForm(contact);
        submitContactCreation();
        returnHomePage();
    }

    public int getContactsCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void openHomePage() {
        click(By.linkText("home"));
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContact();
        openHomePage();
    }

    public void removeAllContacts() {
        selectAllContacts();
        removeSelectedContact();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public List<ContactData> getContactList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var tds = manager.driver.findElements(By.name("entry"));
        for (var td : tds) {
            var checkbox = td.findElement(By.className("center")).findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            var name = td.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var lastName = td.findElement(By.cssSelector("td:nth-child(2)")).getText();
            contacts.add(new ContactData().withId(id).withName(name).withLastName(lastName));
        }
        return contacts;
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        openHomePage();
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href*='edit.php?id=%s']", contact.id())));
        //click(By.name("edit"));

    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobileTelephone());
        type(By.name("email"), contact.email());
        attach(By.name("photo"), contact.photo());
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    public void openAddNewPage() {
        click(By.linkText("add new"));
    }

    public void returnHomePage(){
        click(By.linkText("home"));
    }


}
