package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * LightSource abstract class extends light class, light source class contain addition radius, position and list of points on the light source
 * contains 2 methods:
 * getIntensity- for getting the light color
 * getL- for getting the vector from light source to point
 * getListPoints - return list of random point on the light source area
 */
public abstract class LightSource extends Light {
    protected double _radius;

    /* ********* Constructors ***********/

    /**
     * Constructor get the light source color
     *
     * @param _color
     */
    public LightSource(Color _color) {
        super(_color);
        _radius = 0.0;
    }

    /**
     * Constructor get the light source color, position of the light source and the radius of the light source
     *
     * @param _color
     * @param _radius
     */
    public LightSource(Color _color, double _radius) {
        super(_color);
        this._radius = _radius;
    }

    /* *****getters/setters************/

    /**
     * getIntensity function
     * uses for gets the light color that the light source add to the point (from the viewer point view).
     *
     * @param intersection the point on the geometry
     * @return the light color
     */
    public abstract Color getIntensity(Point3D intersection);

    /**
     * getL function
     * uses to gets the vector from light source to the point
     *
     * @param intersection the point on geometry
     * @return the vector
     */
    public abstract Vector getL(Point3D intersection);

    public abstract List<Ray> lightRays(Point3D point3D);
}
