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
    public void getNormal() throws Exception{

        Coordinate x= new Coordinate(4);
        Coordinate y=new Coordinate(2);
        Coordinate z=new Coordinate(4);
        Point3D receivedPoint=new Point3D(z,z,z);

        Point3D centerPoint= new Point3D(x,y,z);
        double radius=2;
        Sphere mySphere= new Sphere(radius,centerPoint);
        Vector result = new Vector(receivedPoint.subtract(mySphere.getCenter())).normalize();

        Coordinate a= new Coordinate(0);
        Coordinate b=new Coordinate(1);
        Coordinate c=new Coordinate(0);
        Point3D headPoint=new Point3D(a,b,c);
        Vector expectedResult= new Vector(headPoint);

        assertEquals(result,expectedResult);

    }


}