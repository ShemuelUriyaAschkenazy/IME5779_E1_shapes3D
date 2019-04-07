package unittests;


import org.junit.Test;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;
import primitives.Coordinate;

import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void scale() throws Exception{
        Vector v1= new Vector(new Point3D(new Coordinate (0),new Coordinate (3),new Coordinate (4)));
        double num= 5.6;
        Vector expected= new Vector(new Point3D(new Coordinate (0*num),new Coordinate (3*num),new Coordinate (4*num)));
        Vector result= v1.scale(num);
        assertEquals(expected,result);
    }

    @Test
    public void dotProduct() throws Exception {
        Vector v1= new Vector(new Point3D(new Coordinate (0),new Coordinate (3),new Coordinate (4)));
        Vector v2= new Vector(new Point3D(new Coordinate (1),new Coordinate (5),new Coordinate (2)));
        double expected= 23;
        double result= v1.dotProduct(v2);
        assertTrue(Util.usubtract(expected,result)==0);
    }

    @Test
    public void crossProduct() throws Exception{
        Vector v1= new Vector(new Point3D(new Coordinate (0),new Coordinate (3),new Coordinate (4)));
        Vector v2= new Vector(new Point3D(new Coordinate (1),new Coordinate (5),new Coordinate (2)));
        Vector expected= new Vector(new Point3D(new Coordinate (-14),new Coordinate (4),new Coordinate (-3)));
        Vector result= v1.crossProduct(v2);
        assertEquals(expected,result);
    }

    @Test
    public void subtract() throws Exception {
        Vector v1= new Vector(new Point3D(new Coordinate (0),new Coordinate (3),new Coordinate (4)));
        Vector v2= new Vector(new Point3D(new Coordinate (1),new Coordinate (5),new Coordinate (2)));
        Vector expected= new Vector(new Point3D(new Coordinate (-1),new Coordinate (-2),new Coordinate (2)));
        Vector result= v1.subtract(v2);
        assertEquals(expected,result);
    }

    @Test
    public void add() throws Exception{
        Vector v1= new Vector(new Point3D(new Coordinate (0),new Coordinate (3),new Coordinate (4)));
        Vector v2= new Vector(new Point3D(new Coordinate (0),new Coordinate (3),new Coordinate (4)));
        Vector expected= new Vector(new Point3D(new Coordinate (0),new Coordinate (6),new Coordinate (8)));
        Vector result= v1.add(v2);
        assertEquals(expected,result);
    }

    @Test
    public void length() throws Exception {
        Vector v= new Vector(new Point3D(new Coordinate (0),new Coordinate (3),new Coordinate (4)));
        double expected= 5;
        double result= v.length();
    }

    @Test
    public void normalize() throws  Exception{
        Vector v= new Vector(new Point3D(new Coordinate (0),new Coordinate (3),new Coordinate (4)));
        Vector result = v.scale(1/v.length());
        Vector expResult= new Vector(new Point3D(new Coordinate (0/5.0),new Coordinate (3/5.0),new Coordinate (4/5.0)));
        assertEquals(expResult,result);
    }
}