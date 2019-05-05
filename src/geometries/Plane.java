package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;

/**
 * plane class contains tow component point3D that exist on plain and vector who is the normal to the plane
 */
public class Plane implements Geometry {

    private Point3D _point;
    private Vector _normal;

    /* ********* Constructors ***********/

    /**
     * constructor that receive all components of the plane
     *
     * @param point  point3D exist on the plane
     * @param vector normal vector
     */
    public Plane(Point3D point, Vector vector) {
        this._point = point;
        this._normal = vector.normalize();
    }

    /**
     * constructor that receive 3 point are exist on the plane and produce the normal vector
     *
     * @param p1 point3D, exist on the plane
     * @param p2 point3D, exist on the plane
     * @param p3 point3D, exist on the plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        Vector v1 = new Vector(p2.subtract(p1));
        Vector v2 = new Vector(p3.subtract(p1));
        Vector normal = new Vector(v1.crossProduct(v2));

        _normal = normal.normalize();
        _point = p1;
    }

    /**
     * function that return point3D it is exist on the plane
     *
     * @return point3D, exist on the plane
     */
    /* ************* Getters/Setters *******/
    public Point3D getPoint() {
        return _point;
    }

    /**
     * function return the normal of the plain
     *
     * @param point3D point3D
     * @return normal to the plain
     */
    @Override
    public Vector getNormal(Point3D point3D) {
        return _normal;
    }

    /**
     * @param ray from the camera
     * @return list of Intersections points between the ray and the plane
     * @throws Exception
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersectionsList = new ArrayList<Point3D>();

        //ray is parallel to the plane:
        if (Util.usubtract(ray.getVector().dotProduct(this._normal), 0) == 0)
            return null;

        double scaleNum = this._normal.dotProduct(this._point.subtract(ray.getPoint())) / (_normal.dotProduct(ray.getVector()));
        if (Util.usubtract(scaleNum, 0) != 0)
            intersectionsList.add(ray.getPoint().add(ray.getVector().scale(scaleNum)));
        else
            intersectionsList.add(ray.getPoint());
        return intersectionsList;
    }
}

