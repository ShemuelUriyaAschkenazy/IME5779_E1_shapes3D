package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.List;

/**
 * triangle class contains 3 point3D the corners of triangle and addition produce vector who is the normal to the triangle
 */
public class Triangle extends Plane{

    Point3D p1;
    Point3D p2;
    Point3D p3;


    /* ********* Constructors ***********/

    /**
     * constructor that receive 3 point the corners of triangle, applies it to new triangle and produce the normal vector to triangle
     *
     * @param p1 point3D
     * @param p2 point3D
     * @param p3 point3D
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3){
        super(p1, p2 ,p3);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

    }

    /**
     * function return point A of triangle
     *
     * @return point A of triangle
     */
    /* ************* Getters/Setters *******/
    public Point3D getP1() {
        return p1;
    }

    /**
     * function return point B of triangle
     *
     * @return point B of triangle
     */
    public Point3D getP2() {
        return p2;
    }

    /**
     * function return point C of triangle
     *
     * @return point C of triangle
     */
    public Point3D getP3() {
        return p3;
    }

    

    /**
     * find the intersections points between a ray and the triangle
     * @param ray from the camera
     * @return list of Intersections points
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
