package elements;

import geometries.Intersectable;
import primitives.*;
import primitives.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
     *                  ]
     */
    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this._direction = direction.normalize();
    }

    public DirectionalLight(Color color, Vector direction, double radius) {
        super(color);
        this._direction = direction.normalize();
        this._radius = radius;
    }

    @Override
    public Vector getL(Point3D point) {
        return _direction;
    }

    @Override
    public List<Ray> lightRays(Point3D p0) {
        Point3D imaginePositon = p0.add(_direction.scale(-999999));
        if (_radius == 0)
            return new ArrayList<>(Collections.singletonList(new Ray(imaginePositon, _direction)));
        Vector l = p0.subtract(imaginePositon);
        Point3D lHead = l.getHead();
        //creation of two vectors on light source surface (orthogonal to l)
        Vector a = new Vector(Coordinate.ZERO, lHead.getZ(), lHead.getY().scale(-1)).normalize(); //(a,b,c)*(0,c,-b)=0, therefore a orthogonal to l.
        Vector b = l.crossProduct(a).normalize();
        List<Ray> lightRays = new ArrayList<>();
        lightRays.add(new Ray(imaginePositon, _direction));
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            //creation of new point on the plane of vectors a and b.
            Point3D p = imaginePositon.add(a.scale(_radius * random.nextDouble()).add(b.scale(_radius * random.nextDouble())));
            lightRays.add(new Ray(p, p0.subtract(p)));
        }
        return lightRays;
    }

    @Override
    public Color getIntensity(Point3D intersection) {
        return getIntensity();
    }
}
