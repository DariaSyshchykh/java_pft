package ru.stqa.pft.sandbox;

public class Point {

  public static void main(String[] args) {
    double res = dist(1, 3, 3, 3);
    System.out.println(res);
  }
  static double dist(double x1, double y1, double x2, double y2) {
    return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
  }



}