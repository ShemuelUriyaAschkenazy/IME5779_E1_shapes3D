package geometries;

import primitives.*;

import java.util.List;


public class Rectangle extends Plane {

    Vector _width;
    Vector _height;

    // * **constructors************************
    public Rectangle(Point3D point, Vector normal, Vector _width, Vector _height, Color emission, Material material) {
        super(point, normal, emission, material);
        this._width = _width;
        this._height = _height;
    }

    public Rectangle(Rectangle other) {
        super(other.getPoint(), other.getNormal(), other.getEmission(), other.getMaterial());
        this._width = other.getWidth();
        this._height = other.getHeight();
    }

    public Vector getWidth() {
        return _width;
    }

    public Vector getHeight() {
        return _height;
    }


    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionsWithPlane = super.findIntersections(ray);
        GeoPoint intersection;
        if (intersectionsWithPlane != null)
            intersection = intersectionsWithPlane.get(0);
        else return null;
        double width = _width.length();
        double height = _height.length();
        Vector v;
        try {
            v = intersection.getPoint().subtract(this.getPoint());
        }
        //if the intersection point is itself the rectangle corner, return this point
        catch (IllegalArgumentException e) {
            return intersectionsWithPlane;
        }

        double proj1 = v.dotProduct(_width.normalize());
        double proj2 = v.dotProduct(_height.normalize());
        if ((proj1 < width && proj1 > 0) && (proj2 < height && proj2 > 0))
            return intersectionsWithPlane;
        else return null;
    }
}


//https://computergraphics.stackexchange.com/questions/8418/get-intersection-ray-with-square