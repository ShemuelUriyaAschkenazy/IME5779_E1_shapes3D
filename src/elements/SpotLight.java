package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * SpotLight class
 * extends PointLight
 * contains 6 fields:
 * * _color (inherited)- the color of light
 * * _position- point describes the light source position.
 * *  _kC, _kL, _kQ -3 factors to describe the exponential attenuation depending on the distance.
 * direction- the light direction (vector)
 */
public class SpotLight extends PointLight {

    Vector _direction;

    /* ************* constructor *******/

    /**
     * constructor that doesn't contain the radius of the light source
     *
     * @param _color    the light color
     * @param _position point describes the position of light
     * @param _kC       constant attenuation factor (for describing the light attenuation)
     * @param _kL       linear attenuation factor (for describing the light attenuation)
     * @param _kQ       quadratic attenuation factor (for describing the light attenuation)
     * @param direction light direction vector
     */
    public SpotLight(Color _color, Point3D _position, double _kC, double _kL, double _kQ, Vector direction) {
        super(_color, _position, _kC, _kL, _kQ);
        _direction = direction.normalize();
    }

    /**
     * constructor that contain the radius of the light source
     *
     * @param _color    the light color
     * @param _position point describes the position of light
     * @param _radius    radius of the spot light
     * @param _kC       constant attenuation factor (for describing the light attenuation)
     * @param _kL       linear attenuation factor (for describing the light attenuation)
     * @param _kQ       quadratic attenuation factor (for describing the light attenuation)
     * @param direction light direction vector
     */
    public SpotLight(Color _color, Point3D _position,double _radius, double _kC, double _kL, double _kQ, Vector direction) {
        super(_color, _position,_radius, _kC, _kL, _kQ);
        _direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point3D intersection) {
        //getL returns the vector from light source to the point
        //dot product is indicator for the angle between spot light direction and the vector from the previous line.
        double angle = getL(intersection).dotProduct(_direction);
        return angle <= 0 ? Color.BLACK : super.getIntensity(intersection).scale(angle);
    }
}
