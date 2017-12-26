package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    Point p1 = new Point(7, 2); //Задаём координаты первой точки
    Point p2 = new Point(4, 1); //Задаём координаты второй точки
    System.out.println("Расстояние между точками p1 и p2 = " + distance(p1, p2)); //Выводим результат на экран
  }

  public static double distance(Point p1, Point p2) { //Создаём функцию вычисления расстояния между двумя точками
    double dx = p1.x - p2.x;
    double dy = p1.y - p2.y;
    return Math.sqrt(dx * dx + dy * dy);
  }
}

