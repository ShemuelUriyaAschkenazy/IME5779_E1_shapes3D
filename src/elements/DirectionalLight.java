package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * DirectionalLight class
 * extends light class.
 * implements LightSource interface.
 * describes a far away light source (no attenuation with distance) like sun.
 * contains a vector for direction, and distance to calculate a far position.
 */
public class DirectionalLight extends LightSource {
    private Vector _direction;
    public static double distance = 999999;

    /**
     * constructor
     *
     * @param color     the color light
     * @param direction direction of light
     * @param from      point to calculate a far position from it
     */
    public DirectionalLight(Color color, Vector direction, Point3D from) {
        super(color, from.add(direction.scale(-1 * distance)), 10000);
        this._direction = direction.normalize();
        _position = from.add(direction.scale(-1 * distance));
    }

    @Override
    public Color getIntensity() {
        return _color;
    }

    @Override
    public Color getIntensity(Point3D intersection, Point3D precisePosition) {
        //getL returns the vector from light source to the point
        //dot product is indicator for the angle between directional light direction and the vector from the previous line.
        double angle = getL(intersection, precisePosition).dotProduct(_direction);
        return _color.scale(Math.max(0, angle));
    }
}
