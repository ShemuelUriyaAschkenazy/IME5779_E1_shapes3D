package elements;

import geometries.Intersectable;
import org.omg.CORBA.PRIVATE_MEMBER;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * abstract light class
 * 1 field:color
 * methods:
 * getIntensity: getting the color
 */
public abstract class Light {

    private Color _color;

    /* ********consructors*************/

    /** consructor get color of the light
     * @param _color
     */
    public Light(Color _color) {
        this._color = _color;
    }


    /**
     * get intensity function
     * @return the color (light)
     */
    public Color getIntensity() { return _color; }
}
