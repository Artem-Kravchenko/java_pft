package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ResetPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer (){ //Запуск почтового сервера перед тестом
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {
    app.goTo().login("administrator","root");
    app.goTo().manage();
    app.goTo().usersTab();
    String username=app.db().getUserName();
    app.resetPassword().select(username);
    app.resetPassword().init();
    List<MailMessage> mailMessages= app.mail().waitForMail(1,10000);
    String email = username + "@localhost.localdomain";
    String confirmationLink = findConfirmationLink(mailMessages, email);
    String newPassword = String.valueOf(System.currentTimeMillis());
    app.resetPassword().finish(confirmationLink, newPassword);
    Assert.assertTrue(app.newSession().login(username,newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) { //Метод поиска ссылки для потдверждения регистрации
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get(); //Ищем нужное письмо (Т.е. первое в общем списке письмо, адресованное пользователю с email = "user1@localhost.localdomain")
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build(); //Извлечение ссылки из текста письма (Через регулярное выражение)
    return regex.getText(mailMessage.text);
  }



  @AfterMethod
  public void stopMailServer() { //Остановка почтового сервера после теста
    app.mail().stop();
  }

}
