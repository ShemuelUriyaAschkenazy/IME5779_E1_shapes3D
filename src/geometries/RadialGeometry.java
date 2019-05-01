package geometries;

import primitives.Util;

/**
 *
 */
public abstract class RadialGeometry implements Geometry{
    private double _radius;

    /* ********* Constructors ***********/
    public RadialGeometry(double num) {
        if (num >= 0 && Util.usubtract(num, 0.0) != 0)
            _radius = num;
        else
            throw new IllegalArgumentException("radius can't be zero (or almost zero).");
    }

    public RadialGeometry(RadialGeometry other) {
        if (Util.usubtract(other._radius, 0.0) != 0)
            _radius = other._radius;
        else
            throw new IllegalArgumentException("radius can't be zero (or almost zero).");
    }

    /* ************* Getters/Setters *******/


    /**
     * return the radius of the current radial geometry
     *
     * @return radius
     */
    public double getRadius() {
        return _radius;
    }
}

