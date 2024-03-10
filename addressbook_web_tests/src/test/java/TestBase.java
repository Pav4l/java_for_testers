import model.GroupData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected static WebDriver driver;

    protected static void createGroup(GroupData groupData) {
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

    protected static void removeGroup() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("group page")).click();
    }

    @BeforeEach
    public void setUp() {
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

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    protected void openGroupPage() {
        if (!isElementPresent(By.name("new"))) {
            driver.findElement(By.linkText("groups")).click();
        }
    }

    protected boolean isGroupPresent() {
        return !isElementPresent(By.name("selected[]"));
    }


    protected void openContactPage() {
        if (!isElementPresent(By.name("new add"))) {
            driver.findElement(By.linkText("contacts")).click();
        }
    }

    protected boolean isContactPresent() {
        return !isElementPresent(By.name("selected[]"));
    }
    protected void createContact() {
        driver.findElement(By.name("contact_firstname")).click();
        driver.findElement(By.name("contact_firstname")).sendKeys("Name");
        driver.findElement(By.name("contact_middlename")).click();
        driver.findElement(By.name("contact_middlename")).sendKeys("MiddleMame");
        driver.findElement(By.name("contact_lastname")).click();
        driver.findElement(By.name("contact_lastname")).sendKeys("Lastname");
        driver.findElement(By.name("contact_address")).click();
        driver.findElement(By.name("contact_address")).sendKeys("New Street");
        driver.findElement(By.name("contact_mobile")).click();
        driver.findElement(By.name("contact_mobile")).sendKeys("+71111111111");
        driver.findElement(By.name("contact_email")).click();
        driver.findElement(By.name("contact_email")).sendKeys("test@test.test");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("home page")).click();
        driver.findElement(By.linkText("Logout")).click();
    }
}
