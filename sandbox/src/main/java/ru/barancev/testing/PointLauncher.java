package ru.barancev.testing;

public class PointLauncher {
    public static void main(String[] args){
        Point p1 = new Point(10, 2);
        Point p2 = new Point(2,14);

        System.out.println(distance(p1,p2));

        System.out.println(p1.distance4(p2));
    }
    public static double distance (Point p1, Point p2){
        return Math.sqrt((p1.x-p2.x)*(p1.x - p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
    }
}
