package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

  // @BeforeMethod
  public void startMailServer() { //Запуск почтового сервера перед тестом
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException { //Тест для проверки регистрации нового пользователя
    long now = System.currentTimeMillis(); //Возвращение времени в мс от 01.01.1970
    String user = String.format("user%s", now); //Создание нового пользователя с именем + время в мс от 01.01.1970
    String password = "password";
    String email = String.format("user%s@localhost.localdomain", now);
    app.james().createUser(user, password); //Создание пользователя на почтовом сервере

    app.registration().start(user, email);
    // List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) { //Метод поиска ссылки для потдверждения регистрации
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get(); //Ищем нужное письмо (Т.е. первое в общем списке письмо, адресованное пользователю с email = "user1@localhost.localdomain")
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build(); //Извлечение ссылки из текста письма (Через регулярное выражение)
    return regex.getText(mailMessage.text);
  }

  //  @AfterMethod (alwaysRun = true)
  public void stopMailServer() {  //Остановка почтового сервера после теста
    app.mail().stop();
  }

}
