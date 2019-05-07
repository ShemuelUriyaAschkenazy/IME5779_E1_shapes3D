package unittests;


import geometries.Sphere;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class SphereTest {
    @Test
    public void getCenter() throws Exception{
        Coordinate x= new Coordinate(2);
        Coordinate y=new Coordinate(3);
        Coordinate z=new Coordinate(4);
        Point3D centerPoint=new Point3D(x,y,z);
        double radius=2;
        Sphere mySphere= new Sphere(radius,centerPoint);
        Point3D result= mySphere.getCenter();
        Point3D expResult= new Point3D(centerPoint);
        assertEquals(result,expResult);
    }

    @Test
    public void getNormal(){
        Point3D receivedPoint=new Point3D(4,4,4);
        Point3D centerPoint= new Point3D(4,2,4);
        double radius=2;
        Sphere mySphere= new Sphere(radius,centerPoint);
        Vector result = new Vector(receivedPoint.subtract(mySphere.getCenter())).normalize();
        Vector expectedResult= new Vector(0,1,0);
        assertEquals(result,expectedResult);
    }

}