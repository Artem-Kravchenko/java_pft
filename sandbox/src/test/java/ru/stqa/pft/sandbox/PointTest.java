package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test
  public void testDistance() {
    Point p1 = new Point(7, 2);
    Point p2 = new Point(4, 5);
    Assert.assertEquals(p1.distance(p2), 4.242640687119285);
  }
}
