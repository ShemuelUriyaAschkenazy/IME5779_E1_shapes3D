package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;

/**
 * plane class contains tow component point3D that exist on plain and vector who is the normal to the plane
 */
public class Plane extends Geometry {

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
     * @param p point3D
     * @return normal to the plain
     */
    @Override
    public Vector getNormal(Point3D p) {
        Vector v = new Vector(p);
        double result = v.dotProduct(_normal);
        if (result > 0)
            return _normal;
        else if (result < 0)
            return _normal.scale(-1);
        else
            throw new IllegalArgumentException("the point is on the plane!");
    }

    /**
     * @param ray from the camera
     * @return list of Intersections points between the ray and the plane
     * @throws Exception
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionsList = new ArrayList<>();

        //ray is parallel to the plane:
        if (Util.usubtract(ray.getVector().dotProduct(this._normal), 0) == 0)
            return null;


        //ray points are: P=P0+t*v, t>=0
        //plane points are: normal.dotProduct(planePoint-P)=0
        //when we compare between the two points P in the two equations, we get t (the scale num)
        double scaleNum = this._normal.dotProduct(this._point.subtract(ray.getPoint())) / (_normal.dotProduct(ray.getVector()));
        //t>=0, and hence:
        if (scaleNum < 0)
            return null;
        Point3D point;
        double accuracy = 999999999;
        //if scale num !=0, using this and return the point
        if (Util.usubtract(scaleNum, 0) != 0) {
            point = ray.getPoint().add(ray.getVector().scale(scaleNum));
            //checking that point isn't a result of an inaccuracy
            if (point.getX().getCoordinate() > accuracy || point.getY().getCoordinate() > accuracy || point.getZ().getCoordinate() > accuracy)
                return null;
            else
                intersectionsList.add(new GeoPoint(this, point));
        } else
            //t=0 means that the ray point itself is on the plane
            intersectionsList.add(new GeoPoint(this, ray.getPoint()));
        return intersectionsList;
    }
}

