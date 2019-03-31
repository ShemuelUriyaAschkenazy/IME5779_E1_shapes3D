package geometries;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Util;


public class Cylinder extends Tube {
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
}

class HeightZeroException extends Exception {
    public HeightZeroException(String str)
    {
        super(str);
    }
}