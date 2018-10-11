package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pft.sandbox.Point.dist;

public class PointTests {

 @Test
  public void testRes(){
   Point p = new Point();

    //double res = dist(1, 3, 3, 3);
   Assert.assertEquals(p.dist(1,3,3,3), 2.0);
  }
}
