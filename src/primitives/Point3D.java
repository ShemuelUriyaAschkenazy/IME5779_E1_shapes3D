package primitives;

import  primitives.Coordinate;
import static primitives.Util.*;

public final class Point3D {

    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    /********** Constructors ***********/
    public Point3D(Coordinate x, Coordinate y,Coordinate z) {
    _x=new Coordinate(x);
    _y=new Coordinate(y);
    _z=new Coordinate(z);

    }

    public Point3D(Point3D other) {
        _x=other.getX();
        _y=other.getY();
        _z=other.getZ();
    }

    /************** Getters/Setters *******/
    public Coordinate getX() {
        return new Coordinate(_x);
    }
    public Coordinate getY() {
        return new Coordinate(_y);
    }
    public Coordinate getZ() {
        return new Coordinate(_z);
    }

    /*************** Admin *****************/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;
        return _x.equals(((Point3D)obj)._x )&& _y.equals(((Point3D)obj)._y )&& _z.equals(((Point3D)obj)._z );
    }

    @Override
    public String toString() {
        return "("+ _x+" "+_y+" "+_z+")";
    }

    /************** Operations ***************/
    public Point3D subtract(Point3D other) {
        return new Point3D(_x.subtract(other._x), _y.subtract(other._y), _z.subtract(other._z));
    }

    public Point3D add(Point3D other) {
        return new Point3D(_x.add(other._x), _y.add(other._y), _z.add(other._z));
    }

    public Point3D scale(double num) {
        return new Point3D(_x.scale(num),_y.scale(num),_z.scale(num));
    }

    public Point3D multiply(Coordinate other) {
        return new Coordinate(uscale(_coord, other._coord));
    }

}

