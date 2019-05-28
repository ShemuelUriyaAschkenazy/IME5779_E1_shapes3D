package geometries;

import primitives.*;

import java.util.List;


/**
 * cylinder inheritor from tube but it is final geometry and then it has height addition the tube components
 */
public class Cylinder extends Tube {
    private double _height;

    /* ********* Constructors ***********/

    /**
     * constructor that receive all components of the cylinder and throw exception if the height is zero
     *
     * @param ray    ray
     * @param radius radius
     * @param height height
     */
    public Cylinder(Ray ray, double radius, double height, Color emission,Material material) {
        super(ray, radius, emission,material);
        if (height >= 0 && Util.usubtract(height, 0.0) != 0)
            this._height = height;
        else
            throw new IllegalArgumentException("height can't be zero (or almost zero).");
    }

    /* ************* Getters/Setters *******/

    /**
     * function return the height of cylinder
     *
     * @return double number
     */
    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                ", _ray=" + getRay() +
                ", _radius=" + getRadius() +
                '}';
    }

    /**
     * function received point3D and build new vector
     *
     * @param point3D point3D
     * @return normalize vector
     */
    @Override
    public Vector getNormal(Point3D point3D) {
        Point3D O = new Point3D(this.getRay().getPoint().add(this.getRay().getVector().crossProduct(this.getRay().getVector().crossProduct(point3D.subtract(this.getRay().getPoint())))));
        Vector vectorNormal = new Vector(point3D.subtract(O));
        return vectorNormal.normalize();
    }

    /**
     * function find all intersections points on cylinder by the ray that received
     *
     * @param ray- a ray
     * @return list of intersection between the ray and cylinder
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return null;
    }
}
