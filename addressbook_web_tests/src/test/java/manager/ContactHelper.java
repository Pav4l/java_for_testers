package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase{

    public ContactHelper(ApplicationManager manager){

        super(manager);
    }

    public void createContact(ContactData contactData) {
        manager.driver.findElement(By.linkText("add new")).click();
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contactData.firstName());
        manager.driver.findElement(By.name("middlename")).click();
        manager.driver.findElement(By.name("middlename")).sendKeys(contactData.middleName());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contactData.lastName());
        manager.driver.findElement(By.name("nickname")).click();
        manager.driver.findElement(By.name("nickname")).sendKeys(contactData.nickname());
        manager.driver.findElement(By.name("title")).click();
        manager.driver.findElement(By.name("title")).sendKeys(contactData.title());
        manager.driver.findElement(By.name("company")).click();
        manager.driver.findElement(By.name("company")).sendKeys(contactData.company());
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(contactData.address());
        manager.driver.findElement(By.name("home")).click();
        manager.driver.findElement(By.name("home")).sendKeys(contactData.homeTelephone());
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(contactData.mobileTelephone());
        manager.driver.findElement(By.name("work")).click();
        manager.driver.findElement(By.name("work")).sendKeys(contactData.workTelephone());
        manager.driver.findElement(By.name("fax")).click();
        manager.driver.findElement(By.name("fax")).sendKeys(contactData.faxTelephone());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys(contactData.email());
        manager.driver.findElement(By.name("email2")).click();
        manager.driver.findElement(By.name("email2")).sendKeys(contactData.email2());
        manager.driver.findElement(By.name("email3")).click();
        manager.driver.findElement(By.name("email3")).sendKeys(contactData.email3());
        manager.driver.findElement(By.name("homepage")).click();
        manager.driver.findElement(By.name("homepage")).sendKeys(contactData.homepage());
        manager.driver.findElement(By.name("submit")).click();
        manager.driver.findElement(By.linkText("home page")).click();
    }

    public boolean isContactPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        manager.driver.findElement(By.cssSelector("html")).click();
        //manager.driver.switchTo().alert().accept();
    }

}
