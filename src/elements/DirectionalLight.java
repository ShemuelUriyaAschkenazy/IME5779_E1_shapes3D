package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource {

    private Vector _direction;

    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this._direction = direction.normalize();
    }

    public Vector getDirection() {
        return _direction;
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

    @Override
    public Vector getD(Point3D point3D) {
        return null;
    }
}
