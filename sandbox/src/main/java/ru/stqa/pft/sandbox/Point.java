package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;
  public Point (double x, double y){
        }

  public static void main(String[] args) {
    Point pu1 = new Point(3,3);
    Point pu2 = new Point(2,2);
    System.out.println("Расстояние между точками p1 и p2 = " + distance(pu1, pu2));
  }

  public static double distance(Point p1, Point p2){
    double dx = p1.x - p2.x;
    double dy = p1.y - p2.y;
    return Math.sqrt(dx*dx + dy*dy);
    }
  }
