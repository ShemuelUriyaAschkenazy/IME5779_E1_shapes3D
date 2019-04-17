package geometries;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;

public class Tube extends RadialGeometry {
    private Ray _ray;

    /********** Constructors ***********/
    public Tube(Ray ray, double radius) {
        super(radius);
        this._ray = ray;
    }

    /************** Getters/Setters *******/
    public Ray getRay() {
        return _ray;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_ray=" + _ray +
                ", _radius=" + getRadius() +
                '}';
    }

}
