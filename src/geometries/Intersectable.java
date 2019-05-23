package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * interface with one field it is list of intersections
 * and inner class geopoint that include point and geometry.
 */
public interface Intersectable {
    /**
     * class include intersection point and its geomtry
     */
    class GeoPoint {
        public Geometry _geometry;
        public Point3D _point;
        public GeoPoint(Geometry _geometry, Point3D _point) {
            this._geometry = _geometry;
            this._point = _point;
        }
    }

    List<GeoPoint> findIntersections(Ray ray);
}
