package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    Point p1 = new Point(7, 2); //Создаём первый объект типа точка
    Point p2 = new Point(4, 5); //Создаём второй объект типа точка
    System.out.println("Расстояние между точками p1 и p2 = " + p1.distance(p2)); /* Используем метод distance из класса Point для
                                                                                 расчёта расстояние между точками и выводим результат
                                                                                 на экран */
  }
}