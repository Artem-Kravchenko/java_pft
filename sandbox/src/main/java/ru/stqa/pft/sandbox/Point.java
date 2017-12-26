package ru.stqa.pft.sandbox;

public class Point { // Создаём класс Point для представления точек на двумерной плоскости
  public double x;
  public double y;
  public Point(double x, double y) { // Создаём конструктор для передачи конкретных данных в функцию расчёта
    this.x = x;
    this.y = y;
  }
}