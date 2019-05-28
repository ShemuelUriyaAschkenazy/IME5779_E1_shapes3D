package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    Point3D _position;
    double _kC, _kL, _kQ;

    public PointLight(Color _color, Point3D _position, double _kC, double _kL, double _kQ) {
        super(_color);
        this._position = _position;
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }

    public Color getIntensity(Point3D point3D) {
        //distance in square
        double dist2= point3D.distanceInSquare(_position);
        double dist= Math.sqrt(dist2);
        return _color.scale(1/(_kC+_kL*dist+_kQ*dist2));
    }

    @Override
    public Vector getL(Point3D point3D) {
        return new Vector(point3D.subtract(_position).normalize());
    }

    @Override
    public Vector getD(Point3D point3D) {
        return null;
    }

    @Override
    Color getIntensity() {
        return null;
    }
}
