package elements;

import primitives.Color;

/**
 * AmbientLight class
 * extends Light
 * describes a general environmental light (from not-defined light source)
 * 3 fields:
 * _color (inherited)- the light color
 * _ka - k factor that reduced the color intensity
 * _getIntensity- saves the result of scaling the _color by _ka
 */
public class AmbientLight extends Light {
    private double _kA;
    private Color _getIntensity;

    /**
     *constructor
     *@param color light color
     * @param ka k factor that reduced the color intensity ('a' from 'ambient')
     */
    public AmbientLight(Color color, double ka) {
        super(color);
        _kA = ka;
        _getIntensity = _color.scale(_kA);
    }

    /**
     * getkA function to get the _kA factor field.
     * @return the k factor _kA
     */
    public double getkA() {
        return _kA;
    }

    @Override
    public Color getIntensity() {
        return _getIntensity;
    }
}
