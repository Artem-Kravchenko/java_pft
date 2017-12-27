package ru.stqa.pft.sandbox;

public class Point { // Создаём класс Point для представления точек на двумерной плоскости
  public double x;
  public double y;
  public Point(double x, double y) { // Создаём конструктор для передачи данных первой точки в функцию расчёта
    this.x = x;
    this.y = y;
    }

  public double distance(Point p2) { //Создаём метод, в котором вычисляются расстояние между двумя точками (От текущей до переданной)
    double dx = this.x - p2.x;
    double dy = this.y - p2.y;
    return Math.sqrt(dx * dx + dy * dy);
  }
}