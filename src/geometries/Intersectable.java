package geometries;

import primitives.Point3D;
import primitives.Ray;
import java.util.List;

/**
 * interface with one field it is list of intersection.
 */
public interface Intersectable {

    List<Point3D> findIntersections(Ray ray);
}
