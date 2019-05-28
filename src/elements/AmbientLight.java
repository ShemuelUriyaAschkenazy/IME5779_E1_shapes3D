package elements;

import primitives.Color;

public class AmbientLight extends Light {
    private double _kA;
    private Color _getIntensity;

    public AmbientLight(Color color, double ka) {
        super(color);
        _kA = ka;
        _getIntensity = _color.scale(_kA);
    }

    public double getkA() {
        return _kA;
    }

    public Color getIntensity() {
        return _getIntensity;
    }
}
