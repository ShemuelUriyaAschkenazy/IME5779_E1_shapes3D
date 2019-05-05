package unittests;

import geometries.Plane;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

public class TriangleTest {



    @Test
    public void findIntersections() {
        Ray ray= new Ray(new Point3D(1, 0, 0), new Vector(0,0,-1));
        Triangle triangle = new Triangle(new Point3D(0,0,0), new Point3D(7,0,0), new Point3D(0,8,0));
        List<Point3D> intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);

        ray= new Ray(new Point3D(1, 3, 0), new Vector(0,0,-1));
        intersectionsList= triangle.findIntersections(ray);
        System.out.println(intersectionsList);
    }
}