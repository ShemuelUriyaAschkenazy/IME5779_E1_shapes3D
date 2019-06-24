package elements;

import primitives.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class StandingLight extends LightSource {

    Point3D _position;

    StandingLight (double radius, Color color, Point3D position){
        super(color, radius);
        _position= new Point3D(position);
    }


    @Override
    public List<Ray> lightRays(Point3D p0) {
        Vector l= p0.subtract(_position);
        Point3D lHead= l.getHead();
        //creation of two vectors on light source surface (orthogonal to l)
        Vector a= new Vector(Coordinate.ZERO,lHead.getZ(),lHead.getY().scale(-1)).normalize(); //(a,b,c)*(0,c,-b)=0, therefore a orthogonal to l.
        Vector b= l.crossProduct(a).normalize();

        List<Ray> lightRays = new ArrayList<>();
        lightRays.add(new Ray(_position,p0.subtract(_position)));
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            //creation of new point on the plane of vectors a and b.
            Point3D p= _position.add(a.scale(_radius * random.nextDouble()).add(b.scale(_radius * random.nextDouble())));
            lightRays.add(new Ray(p, p0.subtract(p)));
        }
        return lightRays;
    }
}

