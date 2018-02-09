package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

@Test
  public void testDbConnection(){
    Connection conn = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password="); // Инициализация драйвера для подключения к БД
      Statement st = conn.createStatement(); //Открытие подключения к БЛ
      ResultSet rs = st.executeQuery("SELECT group_id,group_name,group_header,group_footer FROM group_list "); //Запрос к БД
      Groups groups = new Groups(); //Создаём новый объект типа группа
      while (rs.next()) { //Помещаем в него результат запроса к БД
       groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
               .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      rs.close(); //Закрываем переменную, в которую прочитан был запрос
      st.close(); //Закрываем Statement (Мы больше не будем выполнять запросы к БД)
      conn.close(); //Закрываем соединение к БД

      System.out.println(groups);




    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

}
