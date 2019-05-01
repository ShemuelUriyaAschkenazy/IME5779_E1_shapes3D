package primitives;


import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * contain 3 coordinate.
 * The class has 3 constructors:
 * a. get 3 coordinates
 * b. get 3 double numbers.
 * c. copy constructor.
 */
public final class Point3D {


    /**
     * const point3 that all coordinats is 0
     */
    final public static Point3D ZERO = new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0));

    private Coordinate _x;
    private Coordinate _y;
    private Coordinate _z;

    /**
     * constructor get 3 coordinates
     *
     * @param x is coordinate
     * @param y is coordinate
     * @param z is coordinate
     */
    /* ********* Constructors ********** */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     * constructor get 3 double numbers.
     *
     * @param x is double number
     * @param y is double number
     * @param z is double number
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     * constructor get point and copy it to new point
     *
     * @param other is point3D
     */
    public Point3D(Point3D other) {
        _x = new Coordinate(other._x);
        _y = new Coordinate(other._y);
        _z = new Coordinate(other._z);
    }


    /* ************* Getters/Setters ****** */

    /**
     * function get the x coordinate
     *
     * @return x coordinate
     */
    public Coordinate getX() {
        return _x;
    }

    /**
     * function get the x coordinate
     *
     * @return y coordinate
     */
    public Coordinate getY() {
        return _y;
    }

    /**
     * function get the x coordinate
     *
     * @return z coordinate
     */
    public Coordinate getZ() {
        return _z;
    }

    /*************** Admin *****************/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D other = (Point3D) obj;
        return _x.equals(other._x) && _y.equals(other._y) && _z.equals(other._z);
    }

    /**
     * the function return string of the point
     *
     * @return string with 3 coordinate of the point
     */
    @Override
    public String toString() {
        return "pt: (" + _x + " " + _y + " " + _z + ")";
    }

    /************** Operations***************/

    /**
     * the vector received from subtract a point from our point
     *
     * @param other- point
     * @return vector
     **/

    public Vector subtract(Point3D other) {
        return new Vector(_x.subtract(other._x), _y.subtract(other._y), _z.subtract(other._z));
    }

    /**
     * the point received when adding the vector to the point
     *
     * @param vector -  vector
     * @return new point3D
     */
    public Point3D add(Vector vector) {
        return new Point3D(_x.add(vector.getHead()._x), _y.add(vector.getHead()._y), _z.add(vector.getHead()._z));
    }

    /**
     * the distane between the points
     *
     * @param p2- other point
     * @return the distane between the points
     */
    public double distance(Point3D p2) {
        return Math.sqrt(distanceInSquare(p2));
    }

    /**
     * The function calculates squared distance to other point
     *
     * @param p2- other point
     * @return the squared distance
     */
    public double distanceInSquare(Point3D p2) {
        double x = _x.subtract(p2._x).getCoordinate();
        double y = _y.subtract(p2._y).getCoordinate();
        double z = _z.subtract(p2._z).getCoordinate();
        return (x * x) + (y * y) + (z * z);
    }
}

