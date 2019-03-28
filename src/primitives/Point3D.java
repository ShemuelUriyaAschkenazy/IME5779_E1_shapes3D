package primitives;


import com.sun.org.apache.bcel.internal.generic.NEW;

public final class Point3D {

    final public static Point3D ZERO= new Point3D(new Coordinate(0),new Coordinate(0), new Coordinate(0));

    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    /********** Constructors ***********/
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);

    }

    public Point3D(Point3D other) {
        _x = other.getX();
        _y = other.getY();
        _z = other.getZ();
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
        return _x.equals(((Point3D) obj)._x) && _y.equals(((Point3D) obj)._y) && _z.equals(((Point3D) obj)._z);
    }

    @Override
    public String toString() {
        return "point: (" + _x + " " + _y + " " + _z + ")";
    }

    /************** Operations ***************/
    public Vector subtract(Point3D other) throws Exception {
        return new Vector(new Point3D(_x.subtract(other._x), _y.subtract(other._y), _z.subtract(other._z)));
    }

    public Point3D add(Vector vector) {
        return new Point3D(_x.add(vector._head._x), _y.add(vector._head._y), _z.add(vector._head._z));
    }

    public double distance(Point3D p2) {
        return Math.sqrt((((_x).subtract(p2._x))._coord * ((_x).subtract(p2._x))._coord)
                + (((_y).subtract(p2._y))._coord * ((_y).subtract(p2._y))._coord)
                + (((_z).subtract(p2._z))._coord * ((_z).subtract(p2._z))._coord));
    }

    public double distance_inSquare(Point3D p1, Point3D p2) {
        return (((p1._x).subtract(p2._x))._coord * ((p1._x).subtract(p2._x))._coord)
                + (((p1._y).subtract(p2._y))._coord * ((p1._y).subtract(p2._y))._coord)
                + (((p1._z).subtract(p2._z))._coord * ((p1._z).subtract(p2._z))._coord);
    }
}

