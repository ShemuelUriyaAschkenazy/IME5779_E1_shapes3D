package unittests;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Triangle;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static geometries.Intersectable.GeoPoint;

import java.util.List;

import static org.junit.Assert.*;

public class TriangleTest {
    

    @Test
    public void findIntersections() {
        System.out.println("test case 1 (on edge)- start at the plane:");
        Ray ray= new Ray(new Point3D(1, 0, 0), new Vector(0,0,-1));
        Triangle triangle = new Triangle(new Point3D(0,0,0), new Point3D(7,0,0), new Point3D(0,8,0),new Color(20,46,1));
        List<GeoPoint> intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);

        System.out.println("test case 1 (on edge)- start before the plane:");
        ray= new Ray(new Point3D(0, 0, 1), new Vector(1,0,-1));
        intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);

        System.out.println("test case 2 (inside triangle)- starts before plane:");
        ray= new Ray(new Point3D(1, 3, 1), new Vector(0,0,-1));
        intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);

        System.out.println("test case 3 (on vertex):");
        ray= new Ray(new Point3D(7, 0, 0), new Vector(0,0,-1));
        intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);

        System.out.println("test case 3 (on vertex):- starts before the plane");
        ray= new Ray(new Point3D(6, 0, 1), new Vector(1,0,-1));
        intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);

        System.out.println("test case 4 (on the line continues after edge):");
        ray= new Ray(new Point3D(10, 0, 0), new Vector(0,0,-1));
        intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);

        System.out.println("test case 4 :-starts before  the plane");
        ray= new Ray(new Point3D(8, 0, 2), new Vector(1,0,-1));
        intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);

        System.out.println("test case 5 (between the lines that continues edges):");
        ray= new Ray(new Point3D(-1, -1, 0), new Vector(0,0,-1));
        intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);

        System.out.println("test case 6: (in the other side of one edge)");
        ray= new Ray(new Point3D(-1, 1, 0), new Vector(0,0,-1));
        intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);
    }
}