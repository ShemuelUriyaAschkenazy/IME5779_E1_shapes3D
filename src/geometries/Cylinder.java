package geometries;

import primitives.*;


public class Cylinder extends Tube implements Geometry {
    double _height;



    /********** Constructors ***********/
    public  Cylinder(Ray ray, double radius, double height) throws Exception{
        super(ray,radius);
        if (height>=0 && Util.usubtract(height, 0.0)!=0)
            this._height = height;
        else
            throw new RadiusZeroException("height can't be zero (or almost zero).");
    }

    /************** Getters/Setters *******/

    public double get_height() {
        return _height;
    }


    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                ", _ray=" + _ray +
                ", _radius=" + _radius +
                '}';
    }

    @Override

    public Vector getNormal(Point3D point3D) throws Exception {
        Point3D O = new Point3D( this._ray.get_point().add(this._ray.get_vector().crossProduct(this._ray.get_vector().crossProduct(point3D.subtract(this._ray.get_point())))));
        Vector vectorNormal = new Vector(point3D.subtract(O));
        return vectorNormal.normalize();

    }
}

class HeightZeroException extends Exception {
    public HeightZeroException(String str)
    {
        super(str);
    }
}