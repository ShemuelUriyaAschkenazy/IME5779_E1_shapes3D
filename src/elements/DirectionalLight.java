package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * DirectionalLight class
 * extends light class.
 * implements LightSource interface.
 * describes a far away light source (no attenuation with distance) like sun.
 * contains a vector for direction
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector _direction;

    /**
     * constructor
     * @param color the color light
     * @param direction direction of light
     */
    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this._direction = direction.normalize();
    }

    @Override
    public Color getIntensity() {
        return _color;
    }

    @Override
    public Color getIntensity(Point3D point3D) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point3D point3D) {
        return _direction;
    }
}
