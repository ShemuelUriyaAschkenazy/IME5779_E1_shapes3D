package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * LightSource interface
 * contains 2 methods:
 * getIntensity- for getting the light color
 * getL- for getting the vector from light source to point
 */
public abstract class LightSource extends Light {

    protected double _radius;
    Point3D _position;
    List<Point3D> _listPoints = new ArrayList<>();

    public LightSource(Color _color) {
        super(_color);
    }

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

    public List<Point3D> getListPoints() {
        return _listPoints;
    }
}
