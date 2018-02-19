package ru.stqa.pft.mantis.appmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {

  private  ApplicationManager app;

  public DbHelper(ApplicationManager app) {
    this.app = app;
  }

  public String getUserName(){
    Connection conn = null;
    List<String> result = new ArrayList<>();

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password="); // Подключение к БД MANTIS-1.2.19
      Statement st =conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT username FROM mantis_user_table WHERE username<>'administrator'");

      while (rs.next()){
        result.add(rs.getString("username"));
      }
      rs.close();
      st.close();
      conn.close();
      return result.get(1);

    } catch (SQLException ex) {
      // Перехват различных ошибок
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
      return null;
    }

  }

}
