package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public interface Intersectable {
    /**
     * @param ray- a ray
     * @return a list of intersections between the ray and the body
     * @throws Exception
     */
    List<Point3D> findIntersections(Ray ray) throws Exception;
}
