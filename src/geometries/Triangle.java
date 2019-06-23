package geometries;

import primitives.*;

import java.util.List;

/**
 * triangle class contains 3 point3D the corners of triangle and addition produce vector who is the normal to the triangle
 */
public class Triangle extends Plane {

    private Point3D p1;
    private Point3D p2;
    private Point3D p3;


    /* ********* Constructors ***********/

    /**
     * constructor that receive 3 point the corners of triangle, applies it to new triangle and produce the normal vector to triangle
     *
     * @param p1 point3D
     * @param p2 point3D
     * @param p3 point3D
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3, Color emission, Material material) {
        super(p1, p2, p3, emission, material);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Triangle(Point3D p1, Point3D p2, Point3D p3, Color emission) {
        super(p1, p2, p3, emission, new Material(0.1, 0.1, 1));
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

    @Override
    public String toString() {
        return "Triangle{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                '}';
    }

    /**
     * find the intersections points between a ray and the triangle
     *
     * @param ray from the camera
     * @return list of Intersections points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionsWithPlane = super.findIntersections(ray);
        if (intersectionsWithPlane == null)
            return null;

        Point3D p0 = ray.getPoint();
        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector v3 = p3.subtract(p0);

        Vector n1 = v1.crossProduct(v2);
        Vector n2 = v2.crossProduct(v3);
        Vector n3 = v3.crossProduct(v1);

        Vector temp = intersectionsWithPlane.get(0).getPoint().subtract(ray.getPoint());
        double side1 = Util.alignZero(n1.dotProduct(temp));
        double side2 = Util.alignZero(n2.dotProduct(temp));
        double side3 = Util.alignZero(n3.dotProduct(temp));

        if (side1 > 0 && side2 > 0 && side3 > 0 || side1 < 0 && side2 < 0 && side3 < 0)
            return intersectionsWithPlane;

        return null;
    }
}
