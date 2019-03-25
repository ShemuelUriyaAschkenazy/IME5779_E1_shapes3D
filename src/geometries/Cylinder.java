package geometries;

import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;


public class Cylinder extends Tube {
    double _height;

    /********** Constructors ***********/
    public  Cylinder(Ray ray, double radius, double height){
        super(ray,radius);
        this._height = height;

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
