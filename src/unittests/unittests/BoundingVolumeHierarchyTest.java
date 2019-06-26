package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import renderer.BoundingVolumeHierarchy;

import static org.junit.Assert.*;

public class BoundingVolumeHierarchyTest {

    @Test
    public void isIntersect() {
        Sphere s= new Sphere(10,new Point3D(0,0,0),new Color(0,0,0));
        BoundingVolumeHierarchy b= new BoundingVolumeHierarchy(s);
        Ray r= new Ray(new Point3D(-9,0,-15),new Vector(0,0,1));
        assertTrue(b.isIntersect(r,-1000,1000));

        r= new Ray(new Point3D(-10,0,-15),new Vector(0,0,1));
        assertFalse(b.isIntersect(r,-1000,1000));
    }
}