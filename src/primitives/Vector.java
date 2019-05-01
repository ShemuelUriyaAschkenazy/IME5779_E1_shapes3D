package primitives;

/**
 * vector class.
 * one field: point 3D of head
 * 3 constructors:
 * a.gets a point
 * b.gets 3 double numbers
 * c.gets 3 coordinates
 */
public class Vector {

    private Point3D _head;

    /* ********* Constructors ***********/

    /**
     * constructor gets a point and applies it to the values vector
     *
     * @param head point3D
     */
    public Vector(Point3D head) {
        if (head.getX().subtract(Coordinate.ZERO).equals(Coordinate.ZERO)
                && head.getY().subtract(Coordinate.ZERO).equals(Coordinate.ZERO)
                && head.getZ().subtract(Coordinate.ZERO).equals(Coordinate.ZERO))
            throw new IllegalArgumentException("vector 0 is not valid");
        _head = head;
    }

    /**
     * constructor gets 3 double numbers and applies it to the values vector
     *
     * @param x double number
     * @param y double number
     * @param z double number
     */
    public Vector(double x, double y, double z) {
        if (Util.usubtract(x, 0) == 0 && Util.usubtract(y, 0) == 0 && Util.usubtract(z, 0) == 0)
            throw new IllegalArgumentException("vector 0 is not valid");
        _head = new Point3D(x, y, z);
    }

    /**
     * constructor gets 3 coordinate and applies it to the values vector
     *
     * @param x coordinate
     * @param y coordinate
     * @param z coordinate
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        if (x.subtract(Coordinate.ZERO) == Coordinate.ZERO
                && y.subtract(Coordinate.ZERO) == Coordinate.ZERO
                && z.subtract(Coordinate.ZERO) == Coordinate.ZERO)
            throw new IllegalArgumentException("vector 0 is not valid");
        _head = new Point3D(x, y, z);
    }

    /**
     * constructor gets vector and copy it to new vector
     *
     * @param other vector
     */
    public Vector(Vector other) {
        if (other._head.getX().subtract(Coordinate.ZERO) == Coordinate.ZERO
                && other._head.getY().subtract(Coordinate.ZERO) == Coordinate.ZERO
                && other._head.getZ().subtract(Coordinate.ZERO) == Coordinate.ZERO)
            throw new IllegalArgumentException("vector 0 is not valid");

        _head = other._head;
    }

    /**
     * function that return the values of the vector at point3D
     *
     * @return point3d that is the values of the vector
     */
    /* ************* Getters/Setters *******/
    public Point3D getHead() {
        return _head;
    }


    /* ************* Operations***************/

    /**
     * the function scale the vector by number and return new vector
     *
     * @param num double number
     * @return the result of scaling the vector by num
     * @throws Exception
     **/
    public Vector scale(double num) {
        return new Vector(_head.getX().scale(num), _head.getY().scale(num), _head.getZ().scale(num));
    }

    /**
     * function get vector and return the numeral result of dot product of the vector with another vector
     *
     * @param other - another vector
     * @return double number
     */
    public double dotProduct(Vector other) {
        return _head.getX().multiply(other._head.getX())
                + _head.getY().multiply(other._head.getY())
                + _head.getZ().multiply(other._head.getZ());
    }

    /**
     * function get vector and return new vector is result of cross product of the vector with another vector
     *
     * @param other vector
     * @return new vector
     */
    public Vector crossProduct(Vector other) {
        double a = _head.getY().multiply(other._head.getZ()) - (other._head.getY().multiply(_head.getZ()));
        double b = other._head.getX().multiply(_head.getZ()) - (_head.getX().multiply(other._head.getZ()));
        double c = _head.getX().multiply(other._head.getY()) - (other._head.getX().multiply(_head.getY()));
        return new Vector(a, b, c);
    }


    /**
     * function get vector and return new vector that is the result of subtracting the another vector from the main vector.
     *
     * @param other - vector
     * @return new vector
     */
    public Vector subtract(Vector other) {
        Coordinate a = _head.getX().subtract(other._head.getX());
        Coordinate b = _head.getY().subtract(other._head.getY());
        Coordinate c = _head.getZ().subtract(other._head.getZ());
        return new Vector(a, b, c);
    }

    /**
     * function get vector andd return new vector that is the result of adding the another vector to the main vector
     *
     * @param other- vector
     * @return new vector
     */
    public Vector add(Vector other) {
        Coordinate a = _head.getX().add(other._head.getX());
        Coordinate b = _head.getY().add(other._head.getY());
        Coordinate c = _head.getZ().add(other._head.getZ());
        return new Vector(a, b, c);
    }

    /**
     * the function produces the length of the vector
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(length2());
    }

    /**
     * function produced squared length of the vector
     *
     * @return squared length of the vector
     */
    public double length2() {
        return _head.getX().multiply(_head.getX())
                + _head.getY().multiply(_head.getY())
                + _head.getZ().multiply(_head.getZ());
    }

    /**
     * the function produces new vector that is a unit vector, and has the same direction as this vector
     * * @return a normalized vector
     */
    public Vector normalize() {
        return this.scale(1 / length());
    }

    @Override
    public String toString() {
        return "Vec:(" +
                _head.getX() +
                ",  " + _head.getY() +
                ",  " + _head.getZ() +
                ')';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Vector)) return false;
        Vector vector = (Vector) obj;
        return _head.equals(vector._head);
    }
}