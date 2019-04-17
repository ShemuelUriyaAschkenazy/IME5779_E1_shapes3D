package primitives;

public class Vector {

    private Point3D _head;

    public Vector(Point3D head) {
        if (head.getX().subtract(Coordinate.ZERO).equals(Coordinate.ZERO)
            &&head.getY().subtract(Coordinate.ZERO).equals(Coordinate.ZERO)
            &&head.getZ().subtract(Coordinate.ZERO).equals(Coordinate.ZERO))
            throw new IllegalArgumentException("vector 0 is not valid");
        _head=head;
    }

    public Vector(Vector other) {
        if (other._head.getX().subtract(Coordinate.ZERO)==Coordinate.ZERO
                &&other._head.getY().subtract(Coordinate.ZERO)==Coordinate.ZERO
              &&other._head.getZ().subtract(Coordinate.ZERO)==Coordinate.ZERO)
            throw new IllegalArgumentException("vector 0 is not valid");

        _head=other._head;
    }

    public Vector(double x, double y, double z) {
        if (Util.usubtract(x,0) == 0 && Util.usubtract(y,0) == 0 && Util.usubtract(z,0) == 0)
            throw new IllegalArgumentException("vector 0 is not valid");
        _head = new Point3D(x,y,z);
    }

    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        if (x.subtract(Coordinate.ZERO) == Coordinate.ZERO
                && y.subtract(Coordinate.ZERO) == Coordinate.ZERO
                && z.subtract(Coordinate.ZERO) == Coordinate.ZERO)
            throw new IllegalArgumentException("vector 0 is not valid");

        _head = new Point3D(x, y, z);
    }
    /* ************* Getters/Setters *******/
    public Point3D getHead() {
        return _head;
    }


    /* ************* Operations***************/

    /**
     * @param num
     * @return the result of scaling the vector by num
     * @throws Exception
    **/
    public Vector scale(double num) {
        return new Vector(new Point3D(_head._x.scale(num),_head._y.scale(num),_head._z.scale(num)));
    }

    /**
     * @param other - another vector
     * @return the numeral result of dot product of the two vectors
     */
    public double dotProduct(Vector other){
        return _head.getX().multiply(other._head.getX())
              + _head.getY().multiply(other._head.getY())
                +_head.getZ().multiply(other._head.getZ());
    }

    /**
     * @param other - another vector
     * @return the vector that is the result of cross product of the two vectors
     * @throws Exception
     */
    public Vector crossProduct(Vector other) {
        double a= _head.getY().multiply(other._head.getZ())-(other._head.getY().multiply(_head.getZ()));
        double b= other._head.getY().multiply(_head.getZ())-(_head.getX().multiply(other._head.getZ()));
        double c= _head.getX().multiply(other._head.getY())-(_head.getY().multiply(other._head.getX()));
        return new Vector(a,b,c);
    }


    /**
     * @param other -onother vector
     * @return a vector that is the result of subtracting the second vector from the first.
     * @throws Exception
     */
    public Vector subtract(Vector other) {
        Coordinate a= _head.getX().subtract(other._head.getX());
        Coordinate b= _head.getY().subtract(other._head.getY());
        Coordinate c= _head.getZ().subtract(other._head.getZ());
        return new Vector(a,b,c);
    }

    /**
     * @param other- onother vector
     * @return a vector that is the result of adding the two vectors
     * @throws Exception
     */
    public Vector add(Vector other) {
        Coordinate a= _head.getX().add(other._head.getX());
        Coordinate b= _head.getY().add(other._head.getY());
        Coordinate c= _head.getZ().add(other._head.getZ());
        return new Vector(a,b,c);
    }

    /**
     * @return the length of the vector
     */
    public double length (){
        return Math.sqrt(length2());
    }

    public double length2 (){
        return _head.getX().multiply(_head.getX())
                +_head.getY().multiply(_head.getY())
                +_head.getZ().multiply(_head.getZ());
    }

    /**
     * return new vector that is a unit vector, and has the same direction as thisw vector
     * * @return a normalized vector
     * @throws Exception
     */
    public Vector normalize() {
    return this.scale(1/length());
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
        if (obj == null || obj instanceof Vector) return false;
        Vector vector = (Vector) obj;
        return _head.equals(vector._head);
    }
}