package primitives;

public class Vector {
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    public Vector(Coordinate x, Coordinate y,Coordinate z) {
        _x=new Coordinate(x);
        _y=new Coordinate(y);
        _z=new Coordinate(z);

    }

    public Vector(Vector other) {
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
    public Coordinate scale(double num) {
        return new Vector(new Coordinate(uscale(_x._coord, num)),new Coordinate(uscale(_y, num)),new Coordinate(uscale(_z, num)));
    }
}
