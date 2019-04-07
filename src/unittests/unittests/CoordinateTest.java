package unittests;

import org.junit.Test;
import primitives.Coordinate;
import primitives.Util;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void equals() {
        Coordinate x=new Coordinate(3.6666666666667);
        Coordinate y=new Coordinate(3.6666666666666);
        assert(x.equals(y));
    }

    @Test
    public void subtract() {
        Coordinate coord1=new Coordinate(3);
        Coordinate coord2=new Coordinate(5);
        Coordinate expected = new Coordinate(-2);
        Coordinate result= coord1.subtract(coord2);
        assertEquals(expected,result);
    }

    @Test
    public void add() {
        Coordinate coord1=new Coordinate(3);
        Coordinate coord2=new Coordinate(5);
        Coordinate expected = new Coordinate(8);
        Coordinate result= coord1.add(coord2);
        assertEquals(expected,result);
    }

    @Test
    public void scale() {
        Coordinate coord1=new Coordinate(3);
       double num=2;
        Coordinate expected = new Coordinate(6);
        Coordinate result= coord1.scale(num);
        assertEquals(expected,result);
    }

    @Test
    public void multiply() {
        Coordinate coord1=new Coordinate(3);
        Coordinate coord2=new Coordinate(5);
        double expected = 15;
        double result= coord1.multiply(coord2);
        assertTrue(Util.usubtract(expected,result)==0);

    }
}