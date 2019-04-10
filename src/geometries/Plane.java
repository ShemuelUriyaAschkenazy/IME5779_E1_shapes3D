package geometries;

import primitives.*;
import primitives.Vector;

import java.util.*;

public class Plane implements Geometry {

    Point3D _point;
    Vector _normal;

    /********** Constructors ***********/

    public Plane(Point3D point, Vector vector) throws Exception {
        this._point = point;
        this._normal = vector.normalize();
    }
    public Plane(Point3D p1, Point3D p2, Point3D p3) throws Exception {
        Vector v1 = new Vector(p2.subtract(p1));
        Vector v2 = new Vector(p3.subtract(p1));
        Vector normal = new Vector(v1.crossProduct(v2));

        _normal = normal.normalize();
        _point = p1;
    }
    /************** Getters/Setters *******/
    public Point3D getPoint() {
        return _point;
    }


    @Override
    public Vector getNormal(Point3D point3D) {
        return _normal;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) throws Exception{
        List<Point3D> intersectionsList= new ArrayList<Point3D>();

        //ray is parallel to the plane:
        if (Util.usubtract(ray.getVector().dotProduct(this._normal),0)==0)
            return null;

        //ray is orthogonal to the plane
        if (ray.getVector().equals(this._normal))
            return null;

        //System.out.println(_normal);
        double scaleNum= this._normal.dotProduct(this._point.subtract(ray.getPoint()))/(_normal.dotProduct(ray.getVector()));
        //System.out.println(scaleNum);
        intersectionsList.add(ray.getPoint().add(ray.getVector().scale(scaleNum)));
        return intersectionsList;
    }
}

