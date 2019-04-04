package geometries;


import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry implements Geometry{



    Point3D center;

    /********** Constructors ***********/
    public Sphere (double radius, Point3D p) throws Exception{
        super(radius);
        center=new Point3D(p);
    }

    /************** Getters/Setters *******/

    public Point3D getCenter() {
        Point3D centerPoint=new Point3D(center);
        return centerPoint;
    }


    @Override
    public Vector getNormal(Point3D point3D) throws Exception {
        Vector vectorNormal = new Vector(point3D.subtract(this.center));
        return vectorNormal.normalize();
    }
}
