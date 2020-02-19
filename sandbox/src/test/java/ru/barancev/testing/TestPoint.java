package ru.barancev.testing;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPoint {


    @Test
    public void testDistance(){
        Point p1 = new Point(10, 2);
        Point p2 = new Point(1,1);
        p1.distance4(p2);
        //assert p1.distance4(p2)==9.055385138137417;
        Assert.assertEquals(p1.distance4(p2),9.055385138137417);
    }
}
