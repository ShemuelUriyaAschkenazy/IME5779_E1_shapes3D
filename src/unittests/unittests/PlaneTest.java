package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import primitives.Coordinate;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlaneTest {

    @Test
    public void getPoint() {
    }

    @Test
    public void getNormal() {
    }

    @Test
    public void findIntersections() throws Exception{
        Point3D p= new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0));
        Vector v= new Vector(new Point3D(new Coordinate(1), new Coordinate(0), new Coordinate(1)));
        Ray ray= new Ray(p,v);

        Point3D p1= new Point3D(new Coordinate(5), new Coordinate(4), new Coordinate(0));
        Point3D p2= new Point3D(new Coordinate(5), new Coordinate(6), new Coordinate(0));
        Point3D p3= new Point3D(new Coordinate(7), new Coordinate(8), new Coordinate(0));
        Plane plane= new Plane(p1,p2,p3);
        //System.out.println(ray);
        List<Point3D> result= plane.findIntersections(ray);
        List<Point3D> expectedResult= new ArrayList<Point3D>();
        expectedResult.add(new Point3D(new Coordinate(-1), new Coordinate(0), new Coordinate(1)));

        assertEquals(expectedResult,result);

    }
}