package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Util;

/**
 * abstract class of radial geometries
 * all class are inheritor from this class must include, getRadius method
 * RadialGeometry component field radius
 */
public abstract class RadialGeometry extends Geometry {
    private double _radius;

    /**
     * constructor that received double number and applies it at radius
     *
     * @param radius radius
     */
    /* ********* Constructors ***********/
    public RadialGeometry(double radius, Color emission, Material material) {
        super(emission, material);
        if (radius >= 0 && Util.usubtract(radius, 0.0) != 0)
            _radius = radius;
        else
            throw new IllegalArgumentException("radius can't be zero (or almost zero).");
    }

    /**
     * constructor that received radial geometry and copy it to new radial geometry
     *
     * @param other radial geometry
     */

    public RadialGeometry(RadialGeometry other) {
        this(other._radius,other._emission, other._material);
    }

    /* ************* Getters/Setters *******/


    /**
     * function that return the radius of the current radial geometry
     *
     * @return radius of the shape
     */
    public double getRadius() {
        return _radius;
    }
}

