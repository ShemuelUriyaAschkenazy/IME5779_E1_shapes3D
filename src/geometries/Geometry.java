package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface that contain one method, all class who inherit this interface will has to write this function
 */
abstract public class Geometry implements Intersectable {
    private Color _emission;

    abstract public Vector getNormal(Point3D point3D);

    public Color getEmission() {
        return _emission;
    }

    public void setEmission(Color _emission) {
        this._emission = _emission;
    }
}
