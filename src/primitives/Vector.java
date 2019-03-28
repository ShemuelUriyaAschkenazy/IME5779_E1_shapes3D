package primitives;
import primitives.Coordinate;

public class Vector {

    Point3D _head;

    public Vector(Point3D head) throws Exception {
        if (head._x.subtract(Coordinate.ZERO)==Coordinate.ZERO
            &&head._y.subtract(Coordinate.ZERO)==Coordinate.ZERO
            &&head._z.subtract(Coordinate.ZERO)==Coordinate.ZERO)
            throw new MyException("vector 0 is not valid");
        _head=head;
    }

    public Vector(Vector other) throws  Exception{
        if (other._head._x.subtract(Coordinate.ZERO)==Coordinate.ZERO
                &&other._head._y.subtract(Coordinate.ZERO)==Coordinate.ZERO
              &&other._head._z.subtract(Coordinate.ZERO)==Coordinate.ZERO)
            throw new MyException("vector 0 is not valid");

        _head=other._head;
    }

    /************** Getters/Setters *******/
    public Point3D get_head() {
        return new Point3D(_head._x,_head._y,_head._z);
    }


    /************** Operations ***************/

    public Vector scale(double num) throws Exception {
        return new Vector(new Point3D(_head._x.scale(num),_head._y.scale(num),_head._z.scale(num)));
    }

    public double dotProduct(Vector other){
        return _head._x.multiply(other._head._x)
              + _head._y.multiply(other._head._y)
                +_head._z.multiply(other._head._z);
    }

    public Vector crossProduct(Vector other) throws Exception{
    Coordinate a= new Coordinate((_head._y.multiply(other._head._z))-(other._head._y.multiply(_head._z)));
        Coordinate b= new Coordinate((other._head._x.multiply(_head._z))-(_head._x.multiply(other._head._z)));
        Coordinate c= new Coordinate((_head._x.multiply(other._head._y))-(_head._y.multiply(other._head._x)));

    return new Vector(new Point3D(a,b,c));
    }

    public Vector substract(Vector other) throws Exception{
        Coordinate a= new Coordinate((_head._x.subtract(other._head._x)));
        Coordinate b= new Coordinate((_head._y.subtract(other._head._y)));
        Coordinate c= new Coordinate((_head._z.subtract(other._head._z)));
        return new Vector(new Point3D(a,b,c));
    }

    public Vector add(Vector other) throws Exception{
        Coordinate a= new Coordinate((_head._x.add(other._head._x)));
        Coordinate b= new Coordinate((_head._y.add(other._head._y)));
        Coordinate c= new Coordinate((_head._z.add(other._head._z)));
        return new Vector(new Point3D(a,b,c));
    }


    public double length (){
        return Math.sqrt(_head._x.multiply(_head._x)  + _head._y.multiply(_head._y) + _head._z.multiply(_head._z));
    }

    public Vector normalize() throws Exception{
    return this.scale(1/length());
    }


    @Override
    public String toString() {
        return String.format("Vector: (" +
                _head._x +
                ",  " + _head._y +
                ",  " + _head._z +
                ')');
    }
}

class MyException extends Exception {
    public MyException(String str)
    {
        super(str);
    }
}