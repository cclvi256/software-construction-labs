/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.*;

public class TurtleSoup {
  
  /**
   * Draw a square.
   *
   * @param turtle     the turtle context
   * @param sideLength length of each side
   */
  public static void drawSquare(Turtle turtle, int sideLength) {
    for (int i = 0; i < 4; i++) {
      turtle.forward(sideLength);
      turtle.turn(90.0);
    }
  }
  
  /**
   * Determine inside angles of a regular polygon.
   * <p>
   * There is a simple formula for calculating the inside angles of a polygon;
   * you should derive it and use it here.
   *
   * @param sides number of sides, where sides must be > 2
   * @return angle in degrees, where 0 <= angle < 360
   */
  public static double calculateRegularPolygonAngle(int sides) {
    if (sides <= 2) {
      throw new RuntimeException("sides must be > 2");
    }
    return 180.0 - (360.0 / sides);
  }
  
  /**
   * Determine number of sides given the size of interior angles of a regular polygon.
   * <p>
   * There is a simple formula for this; you should derive it and use it here.
   * Make sure you *properly round* the answer before you return it (see java.lang.Math).
   * HINT: it is easier if you think about the exterior angles.
   *
   * @param angle size of interior angles in degrees, where 0 < angle < 180
   * @return the integer number of sides
   */
  public static int calculatePolygonSidesFromAngle(double angle) {
    if (angle <= 0 || angle >= 180) {
      throw new RuntimeException("angle must be > 0 and < 180");
    }
    int sides = (int) Math.round(360.0 / (180.0 - angle));
    
    if (sides < 3) {
      throw new RuntimeException("invalid polygon");
    }
    
    if (Math.abs(sides * (180.0 - angle) - 360.0) > 1) {
      throw new RuntimeException("angle must be a divisor of 360");
    }
    
    return sides;
  }
  
  /**
   * Given the number of sides, draw a regular polygon.
   * <p>
   * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
   *
   * @param turtle     the turtle context
   * @param sides      number of sides of the polygon to draw
   * @param sideLength length of each side
   */
  public static void drawRegularPolygon(Turtle turtle, int sides,
                                        int sideLength) {
    double angle = calculateRegularPolygonAngle(sides);
    for (int i = 0; i < sides; i++) {
      turtle.forward(sideLength);
      turtle.turn(180.0 - angle);
    }
  }
  
  /**
   * Given the current direction, current location, and a target location, calculate the Bearing
   * towards the target point.
   * <p>
   * The return value is the angle input to turn() that would point the turtle in the direction of
   * the target point (targetX,targetY), given that the turtle is already at the point
   * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
   * degrees, where 0 <= angle < 360.
   * <p>
   * HINT: look at <a href="http://en.wikipedia.org/wiki/Atan2">http://en.wikipedia.org/wiki/Atan2</a>
   * and Java's math libraries
   *
   * @param currentBearing current direction as clockwise from north
   * @param currentX       current location x-coordinate
   * @param currentY       current location y-coordinate
   * @param targetX        target point x-coordinate
   * @param targetY        target point y-coordinate
   * @return adjustment to Bearing (right turn amount) to get to target point,
   * must be 0 <= angle < 360
   */
  public static double calculateBearingToPoint(double currentBearing,
                                               int currentX, int currentY,
                                               int targetX, int targetY) {
    int deltaX = targetX - currentX;
    int deltaY = targetY - currentY;
    double angle = Math.toDegrees(Math.atan2(deltaX, deltaY));
    
    double deltaAngle = angle - currentBearing;
    while (deltaAngle < 0) {
      deltaAngle += 360;
    }
    return deltaAngle;
  }
  
  /**
   * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
   * to the next.
   * <p>
   * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
   * For each subsequent point, assumes that the turtle is still facing in the direction it was
   * facing when it moved to the previous point.
   * You should use calculateBearingToPoint() to implement this function.
   *
   * @param xCoords list of x-coordinates (must be same length as yCoords)
   * @param yCoords list of y-coordinates (must be same length as xCoords)
   * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
   * otherwise of size (# of points) - 1
   */
  public static List<Double> calculateBearings(List<Integer> xCoords,
                                               List<Integer> yCoords) {
    int len = xCoords.size();
    if (len != yCoords.size()) {
      throw new RuntimeException("xCoords and yCoords must be the same length");
    }
    
    List<Double> bearings = new ArrayList<>();
    List<Double> result = new ArrayList<>();
    
    if (len == 0) {
      return result;
    }
    
    bearings.add(0.0);
    for (int i = 0; i < len - 1; i++) {
      bearings.add(
          Math.toDegrees(Math.atan2(xCoords.get(i + 1) - xCoords.get(i),
              yCoords.get(i + 1) - yCoords.get(i))));
    }
    
    for (int i = 0; i < len - 1; i++) {
      result.add(bearings.get(i + 1) - bearings.get(i));
      while (result.get(i) < 0) {
        result.set(i, result.get(i) + 360);
      }
    }
    
    return result;
  }
  
  /**
   * Given a set of points, compute the convex hull, the smallest convex set that contains all the points
   * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and
   * there are other algorithms too.
   *
   * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
   * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
   */
  public static Set<Point> convexHull(Set<Point> points) {
    
    if (points.size() <= 2) {
      return points;
    }
    HashSet<Point> result = new HashSet<Point>();
    Point tmp = points.iterator().next();
    Point start = tmp;
    Point target = tmp;
    double angle = 0, a1 = 0, at = 0;
    for (Point p : points) {
      if (p.x() < start.x() || p.x() == start.x() && p.y() > start.y()) {
        start = p;
      }
    }
    result.add(start);
    Point ptr = start;
    while (true) {
      at = TurtleSoup.newCalculateBearingToPoint(angle, ptr.x(), ptr.y(),
          target.x(), target.y());
      for (Point q : points) {
        if (target == q) {
          continue;
        }
        a1 = TurtleSoup.newCalculateBearingToPoint(angle, ptr.x(), ptr.y(),
            q.x(), q.y());
        if (a1 < at) {
          target = q;
          at = a1;
        } else if (a1 == at) {
          double dist =
              TurtleSoup.calculateDistance(ptr.x(), ptr.y(), target.x(),
                  target.y());
          double dis1 =
              TurtleSoup.calculateDistance(ptr.x(), ptr.y(), q.x(), q.y());
          if (dis1 > dist) {
            target = q;
            at = a1;
          }
        }
      }
      if (target == start) {
        break;
      } else {
        angle = at;
        result.add(target);
        ptr = target;
      }
    }
    return result;
  }
  
  /**
   * Draw your personal, custom art.
   * <p>
   * Many interesting images can be drawn using the simple implementation of a turtle.  For this
   * function, draw something interesting; the complexity can be as little or as much as you want.
   *
   * @param turtle the turtle context
   */
  public static void drawPersonalArt(Turtle turtle) {
    
    throw new RuntimeException("implement me!");
  }
  
  private static double newCalculateBearingToPoint(double currentBearing,
                                                   double currentX,
                                                   double currentY,
                                                   double targetX,
                                                   double targetY) {
    double hei = Math.abs(currentY - targetY);
    double wid = Math.abs(currentX - targetX);
    double slop = Math.sqrt(hei * hei + wid * wid);
    double CAngle = Math.toDegrees(Math.asin(wid / slop));
    double TAngle;
    if (currentX >= targetX && currentY > targetY) {
      TAngle = 180 + CAngle;
    } else if (currentX > targetX && currentY <= targetY) {
      TAngle = 360 - CAngle;
    } else if (currentX < targetX && currentY >= targetY) {
      TAngle = 180 - CAngle;
    } else if (currentX <= targetX && currentY < targetY) {
      TAngle = CAngle;
    } else {
      return 359;
    }
    return (TAngle >= currentBearing) ? (TAngle - currentBearing) :
        (360 - (currentBearing - TAngle));
  }
  
  private static double calculateDistance(double currentX, double currentY,
                                          double targetX,
                                          double targetY) {
    double wid = Math.abs(currentX - targetX);
    double hei = Math.abs(currentY - targetY);
    return Math.sqrt(wid * wid + hei * hei);
  }
  
  /**
   * Main method.
   * <p>
   * This is the method that runs when you run "java TurtleSoup".
   *
   * @param args unused
   */
  public static void main(String[] args) {
    DrawableTurtle turtle = new DrawableTurtle();

//    drawSquare(turtle, 40);
    
    Scanner scanner = new Scanner(System.in);
    System.out.print("Please input the number of sides of the polygon: ");
    int sides = scanner.nextInt();
    System.out.print("Please input the length of each side: ");
    int sideLength = scanner.nextInt();
    drawRegularPolygon(turtle, sides, sideLength);
    
    // draw the window
    turtle.draw();
  }
}
