package ru.stqa.addressbook.manager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void createContact(ContactData contact, GroupData group) {
        openAddNewPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnHomePage();
    }

    public void removeContactFromGroup(GroupData group, ContactData contact) {
        openHomePage();
        selectGroupHomePage(group);
        selectContact(contact);
        removeSelectedContactsFromGroup();
    }

    private void removeSelectedContactsFromGroup() {
        click(By.xpath("//input[@name=\"remove\"]"));
    }

    private void selectGroupHomePage(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByVisibleText(group.name());
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
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
        openHomePage();
        selectAllContacts();
        removeSelectedContact();
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void selectAllContacts() {
//        List<WebElement> checkboxes = manager.driver.findElements(By.xpath("//input[@name='selected[]']"));
//        for (WebElement checkbox : checkboxes) {
//            checkbox.click();
//        }
//        var checkboxes = manager.driver.findElements(By.name("selected[]"));
//        for (var checkbox : checkboxes) {
//            checkbox.click();
//        }
        manager.driver.findElement(By.id("MassCB")).click();
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
            var name = td.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var lastName = td.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var id = checkbox.getAttribute("value");
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
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
        //attach(By.name("photo"), contact.photo());
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

    public void addContactInGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectGroupToAddContact(group);
        addGroup();
        returnHomePage();
    }

    private void addGroup() {
        click(By.name("add"));
    }

    private void selectGroupToAddContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public String getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public String getAddresses(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }

    public String getEmails(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for(WebElement row : rows){
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getAddresses() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var addresses = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, addresses);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, emails);
        }
        return result;
    }

    public String getPhonesModifyPage(ContactData contact) {
        openHomePage();
        initContactModification(contact);
        var home = manager.driver.findElement(By.name("home")).getText();
        var mobile = manager.driver.findElement(By.name("mobile")).getText();
        var work = manager.driver.findElement(By.name("work")).getText();
        var secondary = manager.driver.findElement(By.name("phone2")).getText();
        openHomePage();
        return Stream.of(home, mobile, work, secondary).filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
    }

    public String getAddressModifyPage(ContactData contact) {
        openHomePage();
        initContactModification(contact);
        var address = manager.driver.findElement(By.name("address")).getText();
        openHomePage();
        return Stream.of(address).filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
    }

    public String getEmailsModifyPage(ContactData contact) {
        openHomePage();
        initContactModification(contact);
        var email = manager.driver.findElement(By.name("email")).getText();
        var email2 = manager.driver.findElement(By.name("email2")).getText();
        var email3 = manager.driver.findElement(By.name("email3")).getText();
        openHomePage();
        return Stream.of(email, email2, email3).filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
    }


}
