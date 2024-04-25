package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(){
        var username = CommonFunctions.randomString(5);
        var password = "password";
        var email = String.format("%s@localhost", username);

        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
        //app.jamesCli().addUser(email, password);
        app.jamesApi().addUser(email, password);

        // заполняем форму создания и отправляем (браузер)
        app.session().signUp(username, email);
        // ждём почту (MailHelper)
        var messages = app.mail().receive(email, password, Duration.ofSeconds(15));
        // извлекаем ссылку из письма
        var url = app.session().getConfirmLink(messages);
        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
        app.session().finishRegistration(url, username, password);
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    DeveloperMailUser user;
    @Test
    void canCreateUser(){
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail", user.name());

//        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
//        //app.jamesCli().addUser(email, password);
//        //app.jamesApi().addUser(email, password);
//
//        // заполняем форму создания и отправляем (браузер)
//        app.session().signUp(username, email);
//        // ждём почту (MailHelper)
//        var messages = app.mail().receive(email, password, Duration.ofSeconds(15));
//        // извлекаем ссылку из письма
//        var url = app.session().getConfirmLink(messages);
//        // проходим по ссылке и завершаем регистрацию пользователя (браузер)
//        app.session().finishRegistration(url, username, password);
//        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
//        app.http().login(username, password);
//        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser(){
        app.developerMail().deleteUser(user);

    }
}
