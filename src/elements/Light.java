package elements;

import primitives.Color;
import primitives.Point3D;

public abstract class Light {

    protected Color _color;

    //********consructors*************
    public Light(Color _color) {
        this._color = _color;
    }

    //*****getters/setters************8

    public void setColor(Color _color) {
        this._color = _color;
    }

    abstract Color getIntensity();
}
