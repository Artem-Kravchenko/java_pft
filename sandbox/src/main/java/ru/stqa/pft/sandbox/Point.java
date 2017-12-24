package ru.stqa.pft.sandbox;

public class Point {  // Создаём класс Point (Точка с параметрами x и y)
  double x;  //Объявляем переменную для координаты x
  double y; //Объявляем переменную для координаты y
  public Point (double x, double y){ // Создаём конструктор для класса Point для обработки конкретных точек
    this.x=x;
    this.y=y;
        }

  public static void main(String[] args) { //Создаём функцию для вывода результа вычисления на экран
    Point p1 = new Point(3,3); //Задаём координаты первой точки
    Point p2 = new Point(2,2); //Задаём координаты второй точки
    System.out.println("Расстояние между точками p1 и p2 = " + distance(p1, p2)); //Выводим результат на экран
  }

  public static double distance(Point p1, Point p2){ //Создаём функцию вычисления расстояния между двумя точками
    double dx = p1.x - p2.x;
    double dy = p1.y - p2.y;
    return Math.sqrt(dx*dx + dy*dy);
    }
  }
