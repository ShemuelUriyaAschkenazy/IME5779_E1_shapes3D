package geometries;

import primitives.*;

import java.util.List;


public class Rectangle extends Plane {
    private Vector _widthV, _heightV;
    private double _width, _height;

    // * **constructors************************
    public Rectangle(Point3D point, Vector _width, Vector _height, Color emission, Material material) {
        super(point, _width.crossProduct(_height), emission, material);
        if (!Util.isZero(_width.dotProduct(_height)))
            throw new IllegalArgumentException("Rectangle edges must be orthogonal!");
        this._widthV = _width.normalize();
        this._heightV = _height.normalize();
        this._width = _width.length();
        this._height = _height.length();
    }

    public Vector getWidthV() {
        return _widthV;
    }
    public Vector getHeightV() {
        return _heightV;
    }
    public double getWidth() {
        return _width;
    }
    public double getHeight() {
        return _height;
    }

    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionsWithPlane = super.findIntersections(ray);
        if (intersectionsWithPlane == null)
            return null;
        Point3D p = intersectionsWithPlane.get(0).getPoint();
        Vector v;
        try {
            v = p.subtract(this._point);
        } catch (IllegalArgumentException e) {
            //if the intersection point is itself the rectangle corner, return this point
            return null;
        }

        double proj1 = Util.alignZero(v.dotProduct(_widthV));
        if (proj1 < 0 || Util.alignZero(_width - proj1) < 0)
            return null;
        double proj2 = Util.alignZero(v.dotProduct(_heightV));
        if (proj2 < 0 || Util.alignZero(_height - proj1) < 0)
            return null;

        return intersectionsWithPlane;
    }
}

//https://computergraphics.stackexchange.com/questions/8418/get-intersection-ray-with-square