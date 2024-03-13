package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {
    protected static WebDriver driver;

    public void init() {
        if (driver == null) {
            driver = new ChromeDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1536, 412));
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.id("LoginForm")).click();
            driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        }
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void createGroup(GroupData groupData) {
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys(groupData.name());
        driver.findElement(By.name("group_header")).click();
        driver.findElement(By.name("group_header")).sendKeys(groupData.header());
        driver.findElement(By.name("group_footer")).click();
        driver.findElement(By.name("group_footer")).sendKeys(groupData.footer());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    public void openGroupPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    public void createContact(ContactData contactData) {
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).sendKeys(contactData.firstName());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).sendKeys(contactData.middleName());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).sendKeys(contactData.lastName());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).sendKeys(contactData.nickname());
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).sendKeys(contactData.title());
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).sendKeys(contactData.company());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).sendKeys(contactData.address());
        driver.findElement(By.name("home")).click();
        driver.findElement(By.name("home")).sendKeys(contactData.homeTelephone());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).sendKeys(contactData.mobileTelephone());
        driver.findElement(By.name("work")).click();
        driver.findElement(By.name("work")).sendKeys(contactData.workTelephone());
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).sendKeys(contactData.faxTelephone());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys(contactData.email());
        driver.findElement(By.name("email2")).click();
        driver.findElement(By.name("email2")).sendKeys(contactData.email2());
        driver.findElement(By.name("email3")).click();
        driver.findElement(By.name("email3")).sendKeys(contactData.email3());
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).sendKeys(contactData.homepage());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("home page")).click();
    }

    public boolean isGroupPresent() {
        return !isElementPresent(By.name("selected[]"));
    }

    public boolean isContactPresent() {
        return !isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        driver.findElement(By.cssSelector("html")).click();
        //driver.switchTo().alert().accept();
    }

    public void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }
}
