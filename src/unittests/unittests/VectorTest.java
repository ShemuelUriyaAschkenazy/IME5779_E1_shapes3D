package unittests;


import javafx.scene.effect.Effect;
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


    /*
        /**
     * @param v2 -other vector
     * @return true if the vectors have the same direction, or false if they doesn't
     * @throws Exception

    boolean isSameDirection(Vector v2) {
        double scaleNum;
        if(v2._head._x.subtract(Coordinate.ZERO)!=Coordinate.ZERO) {
            scaleNum = (_head._x._coord / v2._head._x._coord);
            if (_head._y.subtract(v2._head._y.scale(scaleNum)) == Coordinate.ZERO
                    &&_head._z.subtract(v2._head._z.scale(scaleNum)) == Coordinate.ZERO)
                return true;
            else
                return false;
        }
        else if (v2._head._y.subtract(Coordinate.ZERO)!=Coordinate.ZERO) {
            scaleNum = (_head._y._coord / v2._head._y._coord);
            if (_head._x.subtract(v2._head._x.scale(scaleNum)) == Coordinate.ZERO
                    && _head._z.subtract(v2._head._z.scale(scaleNum)) == Coordinate.ZERO)
                return true;
            else
                return false;
        }
        else if (v2._head._z.subtract(Coordinate.ZERO)!=Coordinate.ZERO) {
            scaleNum = (_head._z._coord / v2._head._z._coord);
            if (_head._x.subtract(v2._head._x.scale(scaleNum)) == Coordinate.ZERO
                    && _head._y.subtract(v2._head._y.scale(scaleNum)) == Coordinate.ZERO)
                return true;
            else
                return false;
        }
        else
            throw new IllegalArgumentException("zero vector is not valid");
    }
     */
    
}