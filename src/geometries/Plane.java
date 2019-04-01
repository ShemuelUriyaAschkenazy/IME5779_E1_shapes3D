package geometries;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {

    Point3D point;
    Vector vector;

    /********** Constructors ***********/

    public Plane(Point3D point, Vector vector) throws Exception {
        this.point = point;
        this.vector = vector.normalize();
    }
    public Plane(Point3D p1, Point3D p2, Point3D p3) throws Exception {
        Vector v1 = new Vector(p2.subtract(p1));
        Vector v2 = new Vector(p3.subtract(p1));
        Vector n = new Vector(v1.crossProduct(v2));
        vector = n.normalize();
        point = p1;
    }
    /************** Getters/Setters *******/
    public Point3D getPoint() {
        return point;
    }

    public Vector getVector() {
        return vector;
    }

    @Override
    public Vector getNormal(Point3D point3D) {
        return vector;
    }
}

