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


    /**
     * constructor
     *
     * @param _emission emission light
     * @param material  material object (values of material type)
     */
    public Geometry(Color _emission, Material material) {
        //shallow copy indeed, but not bad because there is no set function.
        this._emission = _emission;
        _material = material;
    }

    /**
     * getNormal function to get the normal vector from a point on a geometry
     *
     * @param point3D a point on geometry
     * @return the normal vector
     */
    abstract public Vector getNormal(Point3D point3D);

    /**
     * getEmission function to get the emission light on the geometry
     *
     * @return emission light
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * getMaterial function to get the material values of the geometry
     *
     * @return the material (object with material values)
     */
    public Material getMaterial() {
        return _material;
    }

    public abstract double getMaxX();
    public abstract double getMinX();
    public abstract double getMaxY();
    public abstract double getMinY();
    public abstract double getMaxZ();
    public abstract double getMinZ();
}

