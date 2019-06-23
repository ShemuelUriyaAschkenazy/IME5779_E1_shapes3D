package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sphere extends from Radial Geometry then it has radius and additional component point3D it is the _center of the sphere
 */
public class Sphere extends RadialGeometry {
    private Point3D _center;

    /**
     * constructor that receive all components of the sphere
     *
     * @param radius radius
     * @param p      point3D
     */
    /* ********* Constructors ***********/
    public Sphere(double radius, Point3D p, Color emission) {
        super(radius, emission, new Material(0, 0, 1));
        _center = new Point3D(p);
    }

    public Sphere(double radius, Point3D p, Color emission, Material material) {
        super(radius, emission, material);
        _center = new Point3D(p);
    }

    /* ************* Getters/Setters *******/

    /**
     * function that return point3D it is the _center of the sphere
     *
     * @return point3D it is the _center of the sphere
     */
    public Point3D getCenter() {
        return _center;
    }

    /**
     * function return the normal of the sphere
     *
     * @param point3D
     * @return normal of the sphere
     */
    @Override
    public Vector getNormal(Point3D point3D) {
        return point3D.subtract(this._center).normalize();
    }

    /**
     * function that produce list of  Intersections points between the ray and the sphere
     *
     * @param ray ray from the camera
     * @return list of Intersections points between the ray and the sphere
     * @throws Exception
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getVector();
        Vector u;
        try {
            u = _center.subtract(p0);
        } catch (IllegalArgumentException e) {
            //if subtract gives vector zero, it means that ray point is itself the sphere _center.
            //it this case, the intersection will received by _center point+(direction vector*radius):
            return new ArrayList<>(Collections.singletonList(new GeoPoint(this, p0.add(v.scale(_radius)))));
        }

        double uLength = u.length();
        double destTillHalf = Util.alignZero(v.dotProduct(u));
        double d = Math.sqrt(uLength * uLength - destTillHalf * destTillHalf);
        double delta = Util.alignZero(d - _radius);
        if (delta > 0)
            return null;
        if (delta == 0) { // the ray's line is tangent to the sphere
            if (destTillHalf > 0)
                return new ArrayList<>(Collections.singletonList(new GeoPoint(this, p0.add(v.scale(destTillHalf)))));
            return null;
        }

        double halfChord = Math.sqrt(_radius * _radius - d * d);
        double t1 = Util.alignZero(destTillHalf + halfChord);
        double t2 = Util.alignZero(destTillHalf - halfChord);
        List<GeoPoint> intersectionsPoints = new ArrayList<>();
        if (t1 > 0)
            try {
                intersectionsPoints.add(new GeoPoint(this, p0.add(v.scale(t1))));
            } catch (Exception e) {
            }
        if (t2 > 0)
            try {
                intersectionsPoints.add(new GeoPoint(this, p0.add(v.scale(t2))));
            } catch (Exception e) {
            }
        return intersectionsPoints.isEmpty() ? null : intersectionsPoints;
    }
}
