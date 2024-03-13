package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper {
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager){

        this.manager = manager;
    }

    public void openGroupPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    public boolean isGroupPresent() {
        openGroupPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData groupData) {
        openGroupPage();
        manager.driver.findElement(By.name("new")).click();
        manager.driver.findElement(By.name("group_name")).click();
        manager.driver.findElement(By.name("group_name")).sendKeys(groupData.name());
        manager.driver.findElement(By.name("group_header")).click();
        manager.driver.findElement(By.name("group_header")).sendKeys(groupData.header());
        manager.driver.findElement(By.name("group_footer")).click();
        manager.driver.findElement(By.name("group_footer")).sendKeys(groupData.footer());
        manager.driver.findElement(By.name("submit")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }

    public void removeGroup() {
        openGroupPage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }
}
