package unittests;


import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class Point3DTest {

    @Test
    public void equals() {
        Point3D point3DA = new Point3D(new Coordinate(1), new Coordinate(2), new Coordinate(3));
        Point3D point3DB = new Point3D(new Coordinate(1), new Coordinate(2), new Coordinate(3));
        assert (point3DA.equals(point3DB));
    }

    @Test
    public void subtract () throws Exception{
       // Point3D point3DA = new Point3D(new Coordinate(1), new Coordinate(2), new Coordinate(3));
       // Point3D point3DB = new Point3D(new Coordinate(1), new Coordinate(2), new Coordinate(3));
       // Vector vector =  point3DA.subtract(point3DB);
       // Point3D p = new Point3D(point3DA.getX().subtract(point3DB.getX()), point3DA.getY().subtract(point3DB.getY()),point3DA.getZ().subtract(point3DB.getZ()));
       // Vector vector1
       // assertEquals();
    }

    @Test
    public void add() {
    }

    @Test
    public void distance() {
    }

    @Test
    public void distance_inSquare() {
    }
}