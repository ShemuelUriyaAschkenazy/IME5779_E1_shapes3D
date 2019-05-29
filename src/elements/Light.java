package elements;

import primitives.Color;
import primitives.Point3D;


/**
 * abstract light class
 * 1 field:color
 * methods:
 * getIntensity: getting the color
 */
public abstract class Light {

    protected Color _color;

    //********consructors*************
    public Light(Color _color) {
        this._color = _color;
    }

    //*****getters/setters************8

    /**
     * set color function
     * gets color ang sets the color values
     * @param _color the light color
     */
    public void setColor(Color _color) {
        this._color = _color;
    }

    /**
     * get intensity function
     * @return the color (light)
     */
    abstract Color getIntensity();
}
