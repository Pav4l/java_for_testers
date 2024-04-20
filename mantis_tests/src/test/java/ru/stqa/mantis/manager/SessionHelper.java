package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.MailMessage;

import java.util.List;
import java.util.regex.Pattern;

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

    public String getConfirmLink(List<MailMessage> messages) {
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            return text.substring(matcher.start(), matcher.end());
        } else {
            throw new IllegalArgumentException("Url not found");
        }
    }

    public void finishRegistration(String url, String user, String password) {
        manager.driver().get(url);
        type(By.name("realname"), user);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
}
