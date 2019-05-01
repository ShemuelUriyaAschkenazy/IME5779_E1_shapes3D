package geometries;

import primitives.*;

import java.util.List;


public class Cylinder extends Tube implements Geometry {
    private double _height;

    /********** Constructors ***********/
    public Cylinder(Ray ray, double radius, double height) {
        super(ray, radius);
        if (height >= 0 && Util.usubtract(height, 0.0) != 0)
            this._height = height;
        else
            throw new IllegalArgumentException("height can't be zero (or almost zero).");
    }

    /************** Getters/Setters *******/

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

    @Override
    public Vector getNormal(Point3D point3D) {
        Point3D O = new Point3D(this.getRay().getPoint().add(this.getRay().getVector().crossProduct(this.getRay().getVector().crossProduct(point3D.subtract(this.getRay().getPoint())))));
        Vector vectorNormal = new Vector(point3D.subtract(O));
        return vectorNormal.normalize();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) throws Exception {
        return null;
    }
}
