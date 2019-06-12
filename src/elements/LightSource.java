package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * LightSource abstract class extends light class, light source class contain addition radius, position and list of points on the light source
 * contains 2 methods:
 * getIntensity- for getting the light color
 * getL- for getting the vector from light source to point
 * getListPoints - return list of random point on the light source area
 */
public abstract class LightSource extends Light {

    protected double _radius;
    Point3D _position;
    List<Point3D> _listPoints = new ArrayList<>();

    /* ********* Constructors ***********/

    /**
     * Constructor get the light source color
     *
     * @param _color
     */
    public LightSource(Color _color) {
        super(_color);
    }

    /**
     * Constructor get the light source color, position of the light source and the radius of the light source
     *
     * @param _color
     * @param _position
     * @param _radius
     */
    public LightSource(Color _color, Point3D _position, double _radius) {
        super(_color);
        this._position = _position;
        this._radius = _radius;
        Random random = new Random();
        for (int i = 0; i <= 15; i++) {
            double randomRadius = _radius * random.nextDouble();
            Vector vector = new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble()).normalize().scale(randomRadius);
            Point3D p = _position.add(vector);
            _listPoints.add(p);
        }
    }

    /* *****getters/setters************/

    /**
     * getIntensity function
     * uses for gets the light color that the light source add to the point (from the viewer point view).
     *
     * @param intersection the point on the geometry
     * @param precisePosition a point on the light source surface
     * @return the light color
     */
    public abstract Color getIntensity(Point3D intersection, Point3D precisePosition);

    /**
     * getL function
     * uses to gets the vector from light source to the point
     *
     * @param intersection the point on geometry
     * @return the vector
     */
    public Vector getL(Point3D intersection, Point3D precisePosition) {
        return intersection.subtract(precisePosition).normalize();
    }

    /**
     * function that return the list of points on the light source
     *
     * @return list of points on the light source
     */
    public List<Point3D> getListPoints() {
        return _listPoints;
    }
}
