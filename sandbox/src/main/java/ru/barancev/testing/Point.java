package ru.barancev.testing;

public class Point {
    double x;
    double y;


    public Point (double x,double y){
        this.x = x;
        this.y = y;
    }



    public double distance4(Point p){

        return Math.sqrt((this.x-p.x)*(this.x - p.x) + (this.y-p.y)*(this.y-p.y));
    }


}
