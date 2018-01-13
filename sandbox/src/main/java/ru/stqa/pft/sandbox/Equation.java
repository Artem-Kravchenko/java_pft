package ru.stqa.pft.sandbox;

public class Equation { //Класс для расчёта корней квадратного уравнения

  private double a; // Создаём переменные для коэффициентов a, b, c
  private double b;
  private double c;

  private int n; //Переменная для определения количества возможных корней

  public Equation(double a, double b, double c) { //Функция расчёта и анализа корней уравнения
    this.a = a;
    this.b = b;
    this.c = c;

    double d = b * b - 4 * a * c; // Считаем дискриминант


    if (a != 0) { //Проверяет на ноль коэффициент a
      if (d > 0) { //Анализируем результат расчёта с определением количества корней у уравнения
        n = 2;
      } else if (d == 0) {
        n = 1;
      } else {
        n = 0;
      }

    } else if (b != 0) { //Проверяет на ноль коэффициент b
      n = 1;
    } else if (c != 0) { //Проверяет на ноль коэффициент с
      n = 0;
    } else {
      n = -1;
    }

  }

  public int rootNumber() { //Функция для возвращения количества решений у уравнения
    return n;
  }

}