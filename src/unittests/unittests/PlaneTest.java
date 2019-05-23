package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
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
        Plane plane= new Plane(p1,p2,p3,new Color(20,46,1));
        Vector result= plane.getNormal(receivedPoint);
        Vector expected= new Vector(0,0,1);
        assertTrue(expected.equals(result));
    }

    @Test
    public void findIntersections(){
        System.out.println("case 1: (Ray neither orthogonal nor parallel to the plane) -intersect the plane");
        Ray ray= new Ray(new Point3D(3,3,3),new Vector(-1,0,-1));
        Plane plane= new Plane(new Point3D(5,4,0),new Point3D(7,8,0),new Point3D(5,6,0),new Color(20,46,1));
        System.out.println(plane.findIntersections(ray));

        System.out.println("case 1 but without intersections:");
        ray= new Ray(new Point3D(3,3,3),new Vector(1,0,1));
        plane= new Plane(new Point3D(5,4,0),new Point3D(7,8,0),new Point3D(5,6,0),new Color(20,46,1));
        System.out.println(plane.findIntersections(ray));

        System.out.println("case 2: parallel to plane, included in plane:");
        ray= new Ray(new Point3D(2,6,0),new Vector(3,3,0));
        plane= new Plane(new Point3D(5,4,0),new Point3D(7,8,0),new Point3D(5,6,0),new Color(20,46,1));
        System.out.println(plane.findIntersections(ray));

        System.out.println("case 3: parallel, not included:");
        ray= new Ray(new Point3D(2,6,1),new Vector(3,3,0));
        plane= new Plane(new Point3D(5,4,0),new Point3D(7,8,0),new Point3D(5,6,0),new Color(20,46,1));
        System.out.println(plane.findIntersections(ray));

        System.out.println("case 3: ray orthogonal to plane, p0 before plane:");
        ray= new Ray(new Point3D(2,6,1),new Vector(0,0,-1));
        plane= new Plane(new Point3D(5,4,0),new Point3D(7,8,0),new Point3D(5,6,0),new Color(20,46,1));
        System.out.println(plane.findIntersections(ray));

        System.out.println("case 3: orthogonal, p0 after plane:");
        ray= new Ray(new Point3D(2,6,1),new Vector(0,0,1));
        plane= new Plane(new Point3D(5,4,0),new Point3D(7,8,0),new Point3D(5,6,0),new Color(20,46,1));
        System.out.println(plane.findIntersections(ray));

        System.out.println("case 3: orthogonal, p0 in plane:");
        ray= new Ray(new Point3D(2,6,0),new Vector(0,0,1));
        plane= new Plane(new Point3D(5,4,0),new Point3D(7,8,0),new Point3D(5,6,0),new Color(20,46,1));
        System.out.println(plane.findIntersections(ray));
    }
}