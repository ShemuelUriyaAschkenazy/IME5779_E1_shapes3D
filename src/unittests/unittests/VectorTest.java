package unittests;

import org.junit.Test;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;
import primitives.Coordinate;
import static org.junit.Assert.*;


/**
 * tests for primitives.Vector
 */
public class VectorTest {

    @Test
    public void scale() {
        Vector v1 = new Vector(new Point3D(new Coordinate(0), new Coordinate(3), new Coordinate(4)));
        double num = 5.6;
        Vector expected = new Vector(new Point3D(new Coordinate(0 * num), new Coordinate(3 * num), new Coordinate(4 * num)));
        Vector result = v1.scale(num);
        assertEquals(expected, result);
    }

    @Test
    public void dotProduct() {
        Vector v1 = new Vector(0, 3, 4);
        Vector v2 = new Vector(1, 5, 2);
        double expected = 23;
        double result = v1.dotProduct(v2);
        assertTrue(Util.usubtract(expected, result) == 0);

        //test for vectors with a sharp angle between:
        v1 = new Vector(0, 3, 4);
        v2 = new Vector(0, 3, 5);
        expected = 29;
        result = v1.dotProduct(v2);
        assertTrue(Util.usubtract(expected, result) == 0);

        //test for vectors with a blunt angle between:
        v1 = new Vector(1, 1, 8);
        v2 = new Vector(1, 1, -8);
        expected = -62;
        result = v1.dotProduct(v2);
        assertTrue(Util.usubtract(expected, result) == 0);

        //test for orthogonal vectors:
        v1 = new Vector(1, 2, -5);
        v2 = new Vector(1, 2, 1);
        expected = 0;
        result = v1.dotProduct(v2);
        assertTrue(Util.usubtract(expected, result) == 0);

        //test for vectors with opposite directions (on the same line):
        v1 = new Vector(1, 2, -5);
        v2 = new Vector(-1, -2, 5);
        expected = -30;
        result = v1.dotProduct(v2);
        assertTrue(Util.usubtract(expected, result) == 0);

        //test for vectors with the same direction:
        v1 = new Vector(1, 2, 5);
        v2 = new Vector(2, 4, 10);
        expected = 60;
        result = v1.dotProduct(v2);
        assertTrue(Util.usubtract(expected, result) == 0);
    }

    @Test
    public void crossProduct() {
        Vector v1 = new Vector(0, 3, 4);
        Vector v2 = new Vector(1, 5, 2);
        Vector expected = new Vector(-14, 4, -3);
        Vector result = v1.crossProduct(v2);
        assertEquals(expected, result);

        //test for vectors with a sharp angle between:
        v1 = new Vector(0, 3, 4);
        v2 = new Vector(0, 3, 5);
        expected = new Vector(3, 0, 0);
        result = v1.crossProduct(v2);
        assertEquals(expected, result);

        //test for vectors with a blunt angle between:
        v1 = new Vector(1, 1, 8);
        v2 = new Vector(1, 1, -8);
        expected = new Vector(-16, 16, 0);
        result = v1.crossProduct(v2);
        assertEquals(expected, result);

        //test for orthogonal vectors:
        v1 = new Vector(1, 2, -5);
        v2 = new Vector(1, 2, 1);
        expected = new Vector(12, -6, 0);
        result = v1.crossProduct(v2);
        assertEquals(expected, result);

        //test for vectors with opposite directions (on the same line):
        try {
            System.out.println("test for vectors with opposite directions (on the same line):");
            v1 = new Vector(1, 2, -5);
            v2 = new Vector(-1, -2, 5);
            result = v1.crossProduct(v2);
            //if no exception caught, fail the test
            fail();
        } catch (IllegalArgumentException error) {
            System.out.println("\t" + error.getMessage());
        }

        //test for vectors with the same direction:
        try {
            System.out.println("test for vectors with the same direction:");
            v1 = new Vector(1, 2, 5);
            v2 = new Vector(2, 4, 10);
            result = v1.crossProduct(v2);
            //if no exception caught, fail the test
            fail();
        } catch (IllegalArgumentException error) {
            System.out.println("\t" + error.getMessage());
        }
    }

    @Test
    public void subtract() {
        Vector v1 = new Vector(0, 3, 4);
        Vector v2 = new Vector(1, 5, 2);
        Vector expected = new Vector(-1, -2, 2);
        Vector result = v1.subtract(v2);
        assertEquals(expected, result);
    }

    @Test
    public void add() {
        Vector v1 = new Vector(0, 3, 4);
        Vector v2 = new Vector(0, 3, 4);
        Vector expected = new Vector(0, 6, 8);
        Vector result = v1.add(v2);
        assertEquals(expected, result);
    }

    @Test
    public void length() {
        Vector v = new Vector(0, 3, 4);
        double expected = 5;
        double result = v.length();
    }

    @Test
    public void normalize() {
        Vector v = new Vector(0, 3, 4);
        Vector result = v.scale(1 / v.length());
        Vector expResult = new Vector((0 / 5.0), (3 / 5.0), (4 / 5.0));
        assertEquals(expResult, result);
    }

    /**
     * @param v2 -other vector
     * @return true if the vectors have the same direction, or false if they doesn't
     * @throws Exception
     **/

    boolean isSameDirection(Vector v1, Vector v2) {
        double scaleNum;
        if (v2.getHead().getX().subtract(Coordinate.ZERO) != Coordinate.ZERO) {
            scaleNum = (v1.getHead().getX().getCoordinate() / v2.getHead().getX().getCoordinate());
            if (v1.getHead().getY().subtract(v2.getHead().getY().scale(scaleNum)) == Coordinate.ZERO
                    && v1.getHead().getZ().subtract(v2.getHead().getZ().scale(scaleNum)) == Coordinate.ZERO)
                return true;
            else
                return false;
        } else if (v2.getHead().getY().subtract(Coordinate.ZERO) != Coordinate.ZERO) {
            scaleNum = (v1.getHead().getY().getCoordinate() / v2.getHead().getY().getCoordinate());
            if (v1.getHead().getX().subtract(v2.getHead().getX().scale(scaleNum)) == Coordinate.ZERO
                    && v1.getHead().getZ().subtract(v2.getHead().getZ().scale(scaleNum)) == Coordinate.ZERO)
                return true;
            else
                return false;
        } else if (v2.getHead().getZ().subtract(Coordinate.ZERO) != Coordinate.ZERO) {
            scaleNum = (v1.getHead().getZ().getCoordinate() / v2.getHead().getZ().getCoordinate());
            if (v1.getHead().getX().subtract(v2.getHead().getX().scale(scaleNum)) == Coordinate.ZERO
                    && v1.getHead().getY().subtract(v2.getHead().getY().scale(scaleNum)) == Coordinate.ZERO)
                return true;
            else
                return false;
        } else
            throw new IllegalArgumentException("zero vector is not valid");
    }

}