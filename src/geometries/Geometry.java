package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface that contain one method, all class who inherit this interface will has to write this function
 */
abstract public class Geometry implements Intersectable {
    protected Color _emission;
    protected Material _material;

    public Geometry(Color _emission, Material material) {
        //shallow copy indeed, but not bad because there is no set function.
        this._emission = _emission;
        _material = material;
    }

    abstract public Vector getNormal(Point3D point3D);

    public Color getEmission() {
        return _emission;
    }
    public Material getMaterial() { return _material; }
}
