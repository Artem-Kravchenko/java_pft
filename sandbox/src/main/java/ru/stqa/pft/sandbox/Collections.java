package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String args[]) {
    String[] langs = {"Java", "C#", "Python", "PHP"};  // Объявление массива

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP"); //Объявлется интерфейс типа List, который реализуется с помощью конкретного класса ArrayList

    for (String l : languages) {   //Вывод на экран всех элеметров коллекции/массива через указатель/ссылку на все элементы коллекции (l)
      System.out.println("Я хочу выучить " + l);
    }
  }

}
