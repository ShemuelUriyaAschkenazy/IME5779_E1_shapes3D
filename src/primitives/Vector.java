package primitives;



public class Vector {
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    public Vector(Coordinate x, Coordinate y,Coordinate z) throws Exception{
        if (x._coord==Coordinate.ZERO._coord
                &&y._coord==Coordinate.ZERO._coord
                &&z._coord==Coordinate.ZERO._coord)
            throw new MyException("zero vector is not valid");

        _x=new Coordinate(x);
        _y=new Coordinate(y);
        _z=new Coordinate(z);

    }

    public Vector(Vector other) throws Exception{
        if (other._x._coord==Coordinate.ZERO._coord
        &&other._y._coord==Coordinate.ZERO._coord
        &&other._z._coord==Coordinate.ZERO._coord)
            throw new MyException("zero vector is not valid");

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

    /************** Operations ***************/

    public Vector scale(double num) throws Exception{
        return new Vector(_x.scale(num),_y.scale(num),_z.scale(num));
    }

    public double dotProduct(Vector other){
        return _x._coord*other._x._coord+ _y._coord*other._y._coord+ _z._coord*other._z._coord;
    }

    public Vector crossProduct(Vector other) throws Exception{
    Coordinate a= new Coordinate((_y._coord*other._z._coord)-(other._y._coord*_z._coord));
        Coordinate b= new Coordinate((other._x._coord*_z._coord)-(_x._coord*other._z._coord));
        Coordinate c= new Coordinate((_x._coord*other._y._coord)-(_y._coord*other._x._coord));
    return new Vector(a,b,c);
    }

    public double length (){
        return Math.sqrt(_x._coord*_x._coord  + _y._coord*_y._coord + _z._coord*_z._coord);
    }

    public Vector normalize () throws Exception{
    double length= this.length();
    Coordinate x=new Coordinate(_x._coord/length);
    Coordinate y=new Coordinate(_y._coord/length);
    Coordinate z=new Coordinate(_z._coord/length);
    return new Vector(x,y,z);
    }


    @Override
    public String toString() {
        return "Vector{" +
                "_x=" + _x +
                ", _y=" + _y +
                ", _z=" + _z +
                '}';
    }



}





class MyException extends Exception {
    public MyException(String str)
    {
        super(str);
    }
}