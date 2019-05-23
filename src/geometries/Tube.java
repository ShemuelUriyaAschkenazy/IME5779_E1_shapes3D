package geometries;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 * class tube is infinite tube with tow components - ray and radius
 */
public class Tube extends RadialGeometry {
    private Ray _ray;

    /**
     * constructor that received ray and radius, and applies it to new tube
     *
     * @param ray ray
     * @param radius radius - - double number
     */
    /* ********* Constructors ***********/
    public Tube(Ray ray, double radius) {
        super(radius);
        this._ray = ray;
    }

    /**
     * function return the ray of the tube
     *
     * @return ray of the tube
     */
    /* ************* Getters/Setters *******/
    public Ray getRay() {
        return _ray;
    }


    /**
     * function return normal vector normalize of the tube at point is received
     *
     * @param point point3D
     * @return normal vector normalize of the tube at point is received
     */
    @Override
    public Vector getNormal(Point3D point) {
        double scaleNumber= _ray.getVector().dotProduct(point.subtract(_ray.getPoint()));
        Point3D O = _ray.getPoint().add(_ray.getVector().scale(scaleNumber));
        Vector normalVector = point.subtract(O);
        return normalVector.normalize();
    }

    /**
     * function that produce list of  Intersections points between the ray and the tube
     *
     * @param ray- a ray
     * @return return a list of Intersections points between the ray and the tube
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return null;
    }
}
