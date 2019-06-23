package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

/**
 * DirectionalLight class
 * extends light class.
 * implements LightSource interface.
 * describes a far away light source (no attenuation with distance) like sun.
 * contains a vector for direction, and distance to calculate a far position.
 */
public class DirectionalLight extends LightSource {
    private Vector _direction;
    /**
     * constructor
     *
     * @param color     the color light
     * @param direction direction of light
 ]    */
    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this._direction = direction.normalize();
    }

    @Override
    public Vector getL(Point3D point) {
        return _direction;
    }

    @Override
    public Color getIntensity(Point3D intersection) {
        return getIntensity();
    }
}
