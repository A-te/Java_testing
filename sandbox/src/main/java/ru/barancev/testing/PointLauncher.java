package ru.barancev.testing;

public class PointLauncher {
    public static void main(String[] args){
        Point p1 = new Point(10, 2);
        Point p2 = new Point(2,14);
        System.out.println(Point.distance(p1,p2));
    }
}
