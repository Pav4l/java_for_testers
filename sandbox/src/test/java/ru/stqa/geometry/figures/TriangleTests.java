package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateTriangleArea(){
        var s = new Triangle(3.0, 4.0, 5.0);
        double result = s.area();
        Assertions.assertEquals(6.0, result);
    }

    @Test
    void canCalculateTrianglePerimeter(){
        Assertions.assertEquals(12.0, new Triangle(3.0, 4.0, 5.0).perimeter());
    }
    @Test
    void cannotCreateTriangleWithNegativeSide1(){
        try{
            new Triangle(-5.0, 3.0, 4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            //OK
        }
    }
    @Test
    void cannotCreateTriangleWithNegativeSide2(){
        try{
            new Triangle(-5.0, -3.0, 4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            //OK
        }
    }
    @Test
    void cannotCreateTriangleWithNegativeSide3(){
        try{
            new Triangle(-5.0, -3.0, -4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            //OK
        }
    }
    @Test
    void cannotCreateTriangle(){
        try{
            new Triangle(5.0, 10.0, 4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception){
            //OK
        }
    }
    @Test
    void testEquality(){
        var r1 = new Triangle(3.0,4.0,5.0);
        var r2 = new Triangle(3.0,4.0,5.0);
        Assertions.assertEquals(r1,r2);
    }

    @Test
    void testEquality2(){
        var r1 = new Triangle(3.0,4.0,5.0);
        var r2 = new Triangle(4.0,5.0,3.0);
        Assertions.assertEquals(r1,r2);
    }
}
