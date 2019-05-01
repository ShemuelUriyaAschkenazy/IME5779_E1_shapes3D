package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.List;

public class Triangle extends Plane{

    Point3D p1;
    Point3D p2;
    Point3D p3;


    /********** Constructors ***********/

    public Triangle(Point3D p1, Point3D p2, Point3D p3) throws Exception{
        super(p1, p2 ,p3);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

    }

    /************** Getters/Setters *******/
    public Point3D getP1() {
        return p1;
    }

    public Point3D getP2() {
        return p2;
    }

    public Point3D getP3() {
        return p3;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                '}';
    }

    /**
     * @param ray from the camera
     * @return list of Intersections points between the ray and the triangle
     * @throws Exception
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D intersectsPlane;
        List<Point3D> intersectionsWithPlane =  super.findIntersections(ray);
        if (intersectionsWithPlane==null||intersectionsWithPlane.isEmpty())
            return null;
        else
            intersectsPlane=intersectionsWithPlane.get(0);
        Vector v1= p1.subtract(ray.getPoint());
        Vector v2= p2.subtract(ray.getPoint());
        Vector v3= p3.subtract(ray.getPoint());

        Vector n1=v1.crossProduct(v2).normalize();
        Vector n2=v2.crossProduct(v3).normalize();
        Vector n3=v3.crossProduct(v1).normalize();

        Vector temp= intersectsPlane.subtract(ray.getPoint());
        if ((n1.dotProduct(temp)>0
                &&n2.dotProduct(temp)>0
                &&n3.dotProduct(temp)>0)
        ||(n1.dotProduct(temp)<0
                &&n2.dotProduct(temp)<0
                &&n3.dotProduct(temp)<0))
        return intersectionsWithPlane;
        else return null;
    }
}
