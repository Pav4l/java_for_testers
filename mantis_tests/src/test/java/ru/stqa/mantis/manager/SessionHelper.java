package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase{

    public SessionHelper(ApplicationManager manager){
        super(manager);
    }

    public void signUp(String user, String email) {
        click(By.linkText("Signup for a new account"));
        type(By.name("username"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void finishRegistration(String url, String user, String password) {
        manager.driver().get(url);
        type(By.name("realname"), user);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
