package elements;

import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
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
        camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList.add(ray);
            }
        sphere = new Sphere(2.5, new Point3D(0, 0, -2.5));
        integrationFunction(sphere, rayList);

        System.out.println("test case 3:");
        camera = new Camera(new Point3D(0, 0, 0.5), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList.add(ray);
            }
        sphere = new Sphere(2, new Point3D(0, 0, -2));
        integrationFunction(sphere, rayList);

        System.out.println("test case 4:");
        camera = new Camera(new Point3D(0, 0, 1), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList.add(ray);
            }
        sphere = new Sphere(3.1, new Point3D(0, 0, 0));
        integrationFunction(sphere, rayList);

        System.out.println("test case 5:");
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList.add(ray);
            }
        sphere = new Sphere(0.5, new Point3D(0, 0, 1));
        integrationFunction(sphere, rayList);

        System.out.println("plane- test case 1:");
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList.add(ray);
            }
        Plane plane = new Plane(new Point3D(0, 0, -2), new Vector(0, 0, 1));
        integrationFunction(plane, rayList);

        System.out.println("plane- test case 1- with 4X4:");
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                Ray ray = camera.constructRayThroughPixel(4, 4, i, j, 1, 4, 4);
                rayList.add(ray);
            }
        plane = new Plane(new Point3D(0, 0, -2), new Vector(0, 0, 1));
        integrationFunction(plane, rayList);

        System.out.println("plane- test case 2:");
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList.add(ray);
            }
        plane = new Plane(new Point3D(0, 0, -3), new Point3D(0, -1, -2), new Point3D(1, -1, -2));
        integrationFunction(plane, rayList);

        System.out.println("plane- test case 2:- with 4X4");
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                Ray ray = camera.constructRayThroughPixel(4, 4, i, j, 5, 8, 8);
                rayList.add(ray);
            }
        plane = new Plane(new Point3D(0, 3, -7), new Point3D(0, 0, -2), new Point3D(6, -3, 3));
        integrationFunction(plane, rayList);

        System.out.println("plane- test case 3:");
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList.add(ray);
            }
        plane = new Plane(new Point3D(0, 1, -2.5), new Point3D(0, 0, -2), new Point3D(1, -1, -1.5));
        integrationFunction(plane, rayList);

        System.out.println("triangle- test case 1:");
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList.add(ray);
            }
        Triangle triangle = new Triangle(new Point3D(0, -1, -2), new Point3D(1, 1, -2), new Point3D(-1, 1, -2));
        integrationFunction(triangle, rayList);

        System.out.println("triangle- test case 2:");
        camera = new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, -1));
        rayList.clear();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                rayList.add(ray);
            }
        triangle = new Triangle(new Point3D(0, -20, -2), new Point3D(1, 1, -2), new Point3D(-1, 1, -2));
        integrationFunction(triangle, rayList);
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
        System.out.println(geometryIntersections.size());
        System.out.print("\n");
    }
}

