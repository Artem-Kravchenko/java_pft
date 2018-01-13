package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTest {

  @Test
  public void test0() {
    Equation e = new Equation(1, 1, 1); //Создаём объект класса Equation, в котором задаются коэффициенты уравнения
    Assert.assertEquals(e.rootNumber(), 0); //Сравниваем количество возможных корней с ожидаемым результатом
  }

  @Test
  public void test1() {
    Equation e = new Equation(1, 2, 1);
    Assert.assertEquals(e.rootNumber(), 1);
  }

  @Test
  public void test2() {
    Equation e = new Equation(1, 5, 6);
    Assert.assertEquals(e.rootNumber(), 2);
  }

  @Test
  public void testLinear() {
    Equation e = new Equation(0, 1, 1); // Тесты особых случаев, когда один из коэффициентов равен 0
    Assert.assertEquals(e.rootNumber(), 1);
  }

  @Test
  public void testContant() {
    Equation e = new Equation(0, 0, 1); // Тесты особых случаев, когда один из коэффициентов равен 0
    Assert.assertEquals(e.rootNumber(), 0);
  }

  @Test
  public void testZero() {
    Equation e = new Equation(0, 0, 0); // Тесты особых случаев, когда один из коэффициентов равен 0
    Assert.assertEquals(e.rootNumber(), -1);
  }

}
