package ru.stqa.pft.sandbox;

public class Primes {

  public static boolean isPrime(int n) { // Функция проверки числа на то, простое оно или нет
    for (int i = 2; i < n; i++) {
      if (n % i == 0)
        return false;
    }
    return true;
  }

  public static boolean isPrimeFast(int n) { // Отрефакторенная функция проверки числа на то, простое оно или нет
    int m = (int) Math.sqrt(n);
    for (int i = 2; i < m; i++) {
      if (n % i == 0)
        return false;
    }
    return true;
  }


  public static boolean isPrimeWhile(int n) { // Функция проверки числа на то, простое оно или нет через while
    int i = 2;
    while (i < n && n % i != 0) { // Пока число n меньше счётчика и остаток от деления числа на счётчик НЕ равен 0, то цикл не прерывается и число простое
      i++;
    }
    return i == n;
  }


  public static boolean isPrime(long n) { // Функция проверки числа на то, простое оно или нет
    for (int i = 2; i < n; i++) {
      if (n % i == 0)
        return false;
    }
    return true;
  }



}
