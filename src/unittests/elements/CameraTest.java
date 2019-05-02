package elements;

import geometries.Geometry;
import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class CameraTest {

    @Test
    public void constructorTest() {
        Point3D location = new Point3D(3, 3, 3);
        Vector up = new Vector(0, 0, 3);
        Vector to = new Vector(4, 0, 0);
        Camera result = new Camera(location, up, to);
        assertEquals(new Vector(1, 0, 0), result.getVTo());
        assertEquals(new Vector(0, 0, 1), result.getVUp());
        assertEquals(new Vector(0, -1, 0), result.getVRight());
    }

    @Test
    public void constructRayThroughPixel() {
        System.out.println("test case 1:");
        Sphere sphere = new Sphere(1, new Point3D(0, 0, -3));
        Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));
        List<Ray> rayList = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList.add(ray);
            }
        integrationFunction(sphere, rayList);

        System.out.println("test case 2:");
        Camera camera2 = new Camera(new Point3D(0, 0, 0.5), new Vector(0, -1, 0), new Vector(0, 0, -1));
        List<Ray> rayList2 = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera2.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList2.add(ray);
            }
        Sphere sphere2 = new Sphere(2.5, new Point3D(0, 0, -2.5));
        integrationFunction(sphere2, rayList2);

        System.out.println("test case 3:");
        Camera camera3 = new Camera(new Point3D(0, 0, 0.5), new Vector(0, -1, 0), new Vector(0, 0, -1));
        List<Ray> rayList3 = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera3.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList3.add(ray);
            }
        Sphere sphere3 = new Sphere(2, new Point3D(0, 0, -2));
        integrationFunction(sphere3, rayList3);

        System.out.println("test case 4:");
        Camera camera4 = new Camera(new Point3D(0, 0, 1), new Vector(0, -1, 0), new Vector(0, 0, -1));
        List<Ray> rayList4 = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera4.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList4.add(ray);
            }
        Sphere sphere4 = new Sphere(3.1, new Point3D(0, 0, 0));
        integrationFunction(sphere4, rayList4);

        System.out.println("test case 5:");
        Camera camera5 = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));
        List<Ray> rayList5 = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera5.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList5.add(ray);
            }
        Sphere sphere5 = new Sphere(0.5, new Point3D(0, 0, 1));
        integrationFunction(sphere5, rayList5);

    }

    // helps to check the integration between the create of rays and find intersections
    void integrationFunction(Geometry geometry, List<Ray> rayList) {
        List<Point3D> geometryIntersections = new ArrayList<>();
        for (Ray item : rayList) {
            List<Point3D> list = geometry.findIntersections(item);
            if (list != null)
                geometryIntersections.addAll(list);
        }

        System.out.println(geometryIntersections.stream().map(Object::toString).collect(Collectors.joining("\n")).toString());
        System.out.print("\n");
    }
}

        /*//checking if the points we find are the expected:
                boolean flag = true;
        if (sphereIntersections.size() == expected.size())
            for (Point3D point : sphereIntersections) {
                if (!expected.contains(point))
                    flag = false;
            }
        else flag = false;
        return flag;*/

