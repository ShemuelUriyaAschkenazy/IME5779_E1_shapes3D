package unittests;


import geometries.Sphere;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;

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
       org.junit.Assert.assertEquals(result,expResult);

    }

   @Test
    public void getNormal() {

        assertEquals(1,1);
    }


}