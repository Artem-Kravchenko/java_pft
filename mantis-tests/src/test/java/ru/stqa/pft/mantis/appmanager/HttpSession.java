package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
  private CloseableHttpClient httpclient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app) { //Передача ссылки на самого себя
    this.app = app;
    httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build(); //Создание новой сессии (Объекта), который будет отправлять запросы на сервер
  }                                                                                          //setRedirectStrategy - стратегия перенаправления

  public boolean login(String username, String password) throws IOException { //Метод для логина пользователя
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php"); //Создаётся запрос типа POST
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username", username)); //Параметр запроса
    params.add(new BasicNameValuePair("password", password)); //Параметр запроса
    params.add(new BasicNameValuePair("secure_session", "on")); //Параметр запроса
    params.add(new BasicNameValuePair("return", "index.php")); //Параметр запроса
    post.setEntity(new UrlEncodedFormEntity(params)); //Параметры упаковываются и помещаются в запрос
    CloseableHttpResponse response = httpclient.execute(post); //Отправка запроса (Response - ответ от сервера)
    String body = geTextFrom(response); //Получение и анализ ответа от сервера (Текста ответа)
    return body.contains(String.format("<span class=\"italic\">%s</span>", username)); //Проверка того, действительно ли пользователь вошёл в систему
  }

  private String geTextFrom (CloseableHttpResponse response) throws IOException {
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }

  public boolean isLoggedInAs(String username) throws IOException { //Метод, который определяет, кто именно залогинен
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php"); //Создаётся запрос типа GET
    CloseableHttpResponse response = httpclient.execute(get); //Выполнение запроса
    String body = geTextFrom(response); //Получение и анализ ответа от сервера (Текста ответа)
    return body.contains(String.format("<span class=\"italic\">%s</span>", username)); //Проверка того, какой именно пользователь залогинен
  }
}
