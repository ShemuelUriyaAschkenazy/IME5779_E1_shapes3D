package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * interface that contain one method, all class who inherit this interface will has to write this function
 */
public interface Geometry extends Intersectable {
    public Vector getNormal(Point3D point3D);
}
