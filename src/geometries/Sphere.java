package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Sphere extends from Radial Geometry then it has radius and additional component point3D it is the center of the sphere
 */
public class Sphere extends RadialGeometry {
    private Point3D center;

    /**
     * constructor that receive all components of the sphere
     *
     * @param radius radius
     * @param p      point3D
     */
    /* ********* Constructors ***********/
    public Sphere(double radius, Point3D p) {
        super(radius);
        center = new Point3D(p);
    }

    /* ************* Getters/Setters *******/

    /**
     * function that return point3D it is the center of the sphere
     *
     * @return point3D it is the center of the sphere
     */
    public Point3D getCenter() {
        Point3D centerPoint = new Point3D(center);
        return centerPoint;
    }

    /**
     * function return the normal of the sphere
     *
     * @param point3D
     * @return normal of the sphere
     */
    @Override
    public Vector getNormal(Point3D point3D) {
        return point3D.subtract(this.center).normalize();
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
        Vector u;
        try {
            u = center.subtract(ray.getPoint());
        } catch (IllegalArgumentException e) {
            //if subtract gives vector zero, it means that ray point is itself the sphere center.
            //it this case, the intersection will received by center point+(direction vector*radius):
            List<GeoPoint> list = new ArrayList<>();
            list.add(new GeoPoint(this,ray.getPoint().add(ray.getVector().scale(this.getRadius()))));
            return list;
        }

        double uLength = u.length();
        double DestTilHalf = ray.getVector().dotProduct(u);
        double d = Math.sqrt(Util.usubtract(uLength * uLength, (DestTilHalf * DestTilHalf)));
        if (Util.usubtract(d, getRadius()) > 0)
            return null;

        double halfChord = Math.sqrt(Util.usubtract(getRadius() * getRadius(), (d * d)));

        List<GeoPoint> intersectionsPoints = new ArrayList<>();
        //if the ray is on the tangent line, it will be at most one intersection point, we calculate and return it:
        if (Util.usubtract(halfChord, 0) == 0) {
            //if the ray is as was written above, and in addition the ray starts on surface, DestTilHalf will be 0:
            if (DestTilHalf == 0)
                intersectionsPoints.add(new GeoPoint(this,ray.getPoint()));
            else if (DestTilHalf > 0)
                intersectionsPoints.add(new GeoPoint(this,ray.getPoint().add(ray.getVector().scale(DestTilHalf))));
            else
                return null;
            return intersectionsPoints;
        }

        //calculates the t numbers to find the two intersections
        double t1 = DestTilHalf + halfChord;
        double t2 = DestTilHalf - halfChord;

        if (t1 >= 0) {
            if (t1 == 0)
                intersectionsPoints.add(new GeoPoint(this,ray.getPoint()));
            else
                intersectionsPoints.add(new GeoPoint(this,ray.getPoint().add(ray.getVector().scale(t1))));
        }
        if (t2 >= 0) {
            if (t2 == 0)
                intersectionsPoints.add(new GeoPoint(this,ray.getPoint()));
            else
                intersectionsPoints.add(new GeoPoint(this,ray.getPoint().add(ray.getVector().scale(t2))));
        }
        if (intersectionsPoints.isEmpty())
            return null;
        return intersectionsPoints;
    }
}
