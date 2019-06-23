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
    /**
     *constructor
     *@param color light color
     * @param ka k factor that reduced the color intensity ('a' from 'ambient')
     */
    public AmbientLight(Color color, double ka) {
        super(color.scale(ka));
    }
}
