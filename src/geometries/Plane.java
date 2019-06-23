package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;

/**
 * plane class contains tow component point3D that exist on plain and vector who is the normal to the plane
 */
public class Plane extends Geometry {

    protected Point3D _point;
    protected Vector _normal;

    /* ********* Constructors ***********/

    /**
     * constructor that receive all components of the plane
     *
     * @param point  point3D exist on the plane
     * @param vector normal vector
     */
    public Plane(Point3D point, Vector vector, Color emission, Material material) {
        super(emission, material);
        this._point = point;
        this._normal = vector.normalize();
    }

    public Plane(Point3D point, Vector vector, Color emission) {
        super(emission, new Material(0.1, 0.1, 2));
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
    public Plane(Point3D p1, Point3D p2, Point3D p3, Color emission, Material material) {
        super(emission, material);
        Vector v1 = new Vector(p2.subtract(p1));
        Vector v2 = new Vector(p3.subtract(p1));
        Vector normal = new Vector(v1.crossProduct(v2));
        _normal = normal.normalize();
        _point = p1;
    }

    public Plane(Point3D p1, Point3D p2, Point3D p3, Color emission) {
        super(emission, new Material(0.1, 0.1, 3));
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
     * function return the normal of the plane
     *
     * @param p point3D
     * @return normal vector of the plane
     */
    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    /**
     * @param ray from the camera
     * @return list of Intersections points between the ray and the plane
     * @throws Exception
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint();
        if (_point.equals(p0))
            return null;

        Vector v = ray.getVector();
        double vn = Util.alignZero(v.dotProduct(this._normal));
        //ray is parallel to the plane:
        if (vn == 0)
            return null;

        //ray points are: P=P0+t*v, t>=0
        //plane points are: normal.dotProduct(planePoint-P)=0
        //when we compare between the two points P in the two equations, we get t (the scale num)
        double scaleNum = Util.alignZero(this._normal.dotProduct(this._point.subtract(p0)) / vn);
        //t>=0, and hence:
        if (scaleNum <= 0)
            return null;

        try {
            Point3D p = p0.add(v.scale(scaleNum));
            return new ArrayList<>(Collections.singletonList(new GeoPoint(this, p)));
        } catch (Exception e) {
            return null;
        }
    }
}
