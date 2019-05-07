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
    public void getNormal() {
        //create of XY-plane
        Point3D receivedPoint=new Point3D(2,2,2);
        Point3D p1= new Point3D(5,4,0);
        Point3D p2= new Point3D(5,6,0);
        Point3D p3= new Point3D(7,8,0);
        Plane plane= new Plane(p1,p2,p3);
        Vector result= plane.getNormal(receivedPoint);
        Vector expected= new Vector(0,0,1);
        assertTrue(expected.equals(result));
    }

    @Test
    public void findIntersections(){
        Ray ray= new Ray(new Point3D(2,0,1),new Vector(-1,0,-1));
        //create of XY-plane
        Point3D p1= new Point3D(5,4,0);
        Point3D p2= new Point3D(5,6,0);
        Point3D p3= new Point3D(7,8,0);
        Plane plane= new Plane(p1,p2,p3);
        List<Point3D> result= plane.findIntersections(ray);
        List<Point3D> expectedResult= new ArrayList<Point3D>();
        expectedResult.add(new Point3D(1,0,0));
        System.out.println("ray: "+ray);
        System.out.println("plane points: "+p1+" "+p2+" "+p3);
        System.out.println("intersection point (expected): "+expectedResult);
        System.out.println("intersection point (result): "+result);
        assertEquals(expectedResult,result);
    }
}