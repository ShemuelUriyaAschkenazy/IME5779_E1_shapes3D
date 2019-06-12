package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * PointLight class
 * extends Light
 * implements LightSource interface
 * describes a light from point with attenuation by distance, and without direction (like a bulb)
 * contains 5 fields:
 * _color (inherited)- the color of light
 * _position- point describes the light source position.
 * _kC, _kL, _kQ -3 factors to describe the exponential attenuation depending on the distance.
 */
public class PointLight extends LightSource {

    Point3D _position;
    double _kC, _kL, _kQ;

    /**
     * constructor
     *
     * @param _color    the light color
     * @param _position point describes the position of light
     * @param _kC       constant attenuation factor (for describing the light attenuation)
     * @param _kL       linear attenuation factor (for describing the light attenuation)
     * @param _kQ       quadratic attenuation factor (for describing the light attenuation)
     */
    public PointLight(Color _color, Point3D _position, double _kC, double _kL, double _kQ) {
        super(_color);
        this._position = _position;
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }

    public PointLight(Color _color, Point3D _position,double _radius, double _kC, double _kL, double _kQ) {
        super(_color,_position,_radius);
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }



    public Point3D get_position() {
        return _position;
    }

    @Override
    public Color getIntensity(Point3D intersection,Point3D precisePosition) {
        //distance in square
        double dist2 = intersection.distanceInSquare(precisePosition);
        double dist = Math.sqrt(dist2);
        return _color.scale(1 / (_kC + _kL * dist + _kQ * dist2));
    }



    @Override
    Color getIntensity() {
        return null;
    }
}
