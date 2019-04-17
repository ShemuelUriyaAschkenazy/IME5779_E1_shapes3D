package primitives;


import com.sun.org.apache.bcel.internal.generic.NEW;

public final class Point3D {

    final public static Point3D ZERO= new Point3D(new Coordinate(0),new Coordinate(0), new Coordinate(0));

    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    /* ********* Constructors ********** */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);

    }

    public Point3D(double x, double y, double z){
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    public Point3D(Point3D other) {
        _x = new Coordinate(other._x);
        _y = other.getY();
        _z = other.getZ();
    }


    /* ************* Getters/Setters ****** */
    /**
     * asd asdf sdf dsf
     * @return fdd vf
     */
    public Coordinate getX() {
        return _x;
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
        if (!(obj instanceof Point3D)) return false;
        Point3D other = (Point3D)obj;
        return _x.equals(other._x) && _y.equals(other._y) && _z.equals(other._z);
    }

    @Override
    public String toString() {
        return "pt: (" + _x + " " + _y + " " + _z + ")";
    }

    /************** Operations***************/

    /**
     * @param other- another point
     * @return the vector received from subtract a point from our point
     * @throws Exception
    **/

    public Vector subtract(Point3D other) {
        return new Vector(new Point3D(_x.subtract(other._x), _y.subtract(other._y), _z.subtract(other._z)));
    }

    /**
     * @param vector - a vector
     * @return the point received when adding the vector to the point
     */
    public Point3D add(Vector vector) {
        return new Point3D(_x.add(vector._head._x), _y.add(vector._head._y), _z.add(vector._head._z));
    }

    /**
     * @param p2- other point
     * @return the distane between the points
     */
    public double distance(Point3D p2) {
        double x=_x.subtract(p2._x)._coord;
        double y= _y.subtract(p2._y)._coord;
        double z= _z.subtract(p2._z)._coord;
        return Math.sqrt((x*x)+(y*y)+(z*z));
    }

    /**
     * The function calculates squared distance to other point
     * @param p2- other point
     * @return the squared distance
     */
    public double distanceInSquare(Point3D p2) {
        double x=_x.subtract(p2._x)._coord;
        double y= _y.subtract(p2._y)._coord;
        double z= _z.subtract(p2._z)._coord;
        return (x*x)+(y*y)+(z*z);
    }
}

