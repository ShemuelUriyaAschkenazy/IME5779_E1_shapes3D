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
    public void subtract() throws Exception {
        Point3D point3DA = new Point3D(new Coordinate(2), new Coordinate(4), new Coordinate(6));
        Point3D point3DB = new Point3D(new Coordinate(1), new Coordinate(2), new Coordinate(3));
        Vector vector = point3DA.subtract(point3DB);
        Coordinate coordinate1 = new Coordinate(point3DA.getX().subtract(point3DB.getX()));
        Coordinate coordinate2 = new Coordinate(point3DA.getY().subtract(point3DB.getY()));
        Coordinate coordinate3 = new Coordinate(point3DA.getZ().subtract(point3DB.getZ()));

        Point3D p = new Point3D(coordinate1, coordinate2, coordinate3);
        Vector vector1 = new Vector(p);
        assert (vector.equals(vector1));
    }
}