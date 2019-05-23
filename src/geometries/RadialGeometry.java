package geometries;

import primitives.Color;
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
     * @param num radius
     */
    /* ********* Constructors ***********/
    public RadialGeometry(double num,Color color) {

        if (num >= 0 && Util.usubtract(num, 0.0) != 0)
            _radius = num;
        else
            throw new IllegalArgumentException("radius can't be zero (or almost zero).");
        this.setEmission(color);
    }

    /**
     * constructor that received radial geometry and copy it to new radial geometry
     *
     * @param other radial geometry
     */
    public RadialGeometry(RadialGeometry other) {
        if (Util.usubtract(other._radius, 0.0) != 0)
            _radius = other._radius;
        else
            throw new IllegalArgumentException("radius can't be zero (or almost zero).");
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

