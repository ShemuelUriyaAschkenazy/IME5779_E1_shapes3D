package unittests;


import geometries.Geometries;
import geometries.Intersectable;
import geometries.Sphere;
import org.junit.Test;
import primitives.*;

import static geometries.Intersectable.GeoPoint;

import java.util.List;

import static org.junit.Assert.*;

public class SphereTest {
    @Test
    public void getCenter() throws Exception{
        Coordinate x= new Coordinate(2);
        Coordinate y=new Coordinate(3);
        Coordinate z=new Coordinate(4);
        Point3D centerPoint=new Point3D(x,y,z);
        double radius=2;
        Sphere mySphere= new Sphere(radius,centerPoint,new Color(20,46,1));
        Point3D result= mySphere.getCenter();
        Point3D expResult= new Point3D(centerPoint);
        assertEquals(result,expResult);
    }

    @Test
    public void getNormal(){
        Point3D receivedPoint=new Point3D(4,4,4);
        Point3D centerPoint= new Point3D(4,2,4);
        double radius=2;
        Sphere mySphere= new Sphere(radius,centerPoint,new Color(20,46,1));
        Vector result = new Vector(receivedPoint.subtract(mySphere.getCenter())).normalize();
        Vector expectedResult= new Vector(0,1,0);
        assertEquals(result,expectedResult);
    }

    @Test
    public void findIntersections() {
        System.out.println("test case 1 (no intersections):");
        Ray ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        Sphere sphere = new Sphere(1, new Point3D(0,2,-4),new Color(20,46,1));
        List<GeoPoint> result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 2 (2 intersections):");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(1, new Point3D(0,0.5,-4),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 3 (ray starts inside sphere, one intersection):");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,-1,-1),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 4 (ray starts outside sphere, due to direction- no intersection):");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(2, new Point3D(0,1,4),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 5 (ray is on a line that include center- ray starts in center):");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,0,0),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 5 , but ray starts on sphere surface, to the outside:");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,0,5),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 5 ,but ray starts on sphere surface, to inside):");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,0,-5),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 5 , but ray starts outside, to the outside:");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,0,6),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 5 , but ray starts outside, to the inside:");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(5, new Point3D(0,0,-6),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 6 (ray starts on sphere surface anywhere, to inside):");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,-1,-1));
        sphere = new Sphere(5, new Point3D(0,0,-5),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 6 (ray starts on sphere surface anywhere, to outside):");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,1,1));
        sphere = new Sphere(5, new Point3D(0,0,-5),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 7 (ray is on the tangent line- ray starts before intersection):");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(1, new Point3D(0,1,-1),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 7 (ray is on the tangent line- ray starts in intersection point):");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(1, new Point3D(0,1,0),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 7 (ray is on the tangent line- ray starts after intersection):");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(1, new Point3D(0,1,1),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");

        System.out.println("test case 8 (ray is on a line that vertical to radius- ray starts on the radius line:");
        ray= new Ray(new Point3D(0,0,0), new Vector(0,0,-1));
        sphere = new Sphere(1, new Point3D(0,2,0),new Color(20,46,1));
        result= sphere.findIntersections(ray);
        System.out.println(result+"\n");
    }
}