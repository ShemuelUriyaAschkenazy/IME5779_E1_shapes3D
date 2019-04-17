package primitives;

public class Vector {

    Point3D _head;

    public Vector(Point3D head) {
        if (head._x.subtract(Coordinate.ZERO).equals(Coordinate.ZERO)
            &&head._y.subtract(Coordinate.ZERO).equals(Coordinate.ZERO)
            &&head._z.subtract(Coordinate.ZERO).equals(Coordinate.ZERO))
            throw new IllegalArgumentException("vector 0 is not valid");
        _head=head;
    }

    public Vector(Vector other) {
        if (other._head._x.subtract(Coordinate.ZERO)==Coordinate.ZERO
                &&other._head._y.subtract(Coordinate.ZERO)==Coordinate.ZERO
              &&other._head._z.subtract(Coordinate.ZERO)==Coordinate.ZERO)
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
        return _head._x.multiply(other._head._x)
              + _head._y.multiply(other._head._y)
                +_head._z.multiply(other._head._z);
    }

    /**
     * @param other - another vector
     * @return the vector that is the result of cross product of the two vectors
     * @throws Exception
     */
    public Vector crossProduct(Vector other) {
        
        if (this.isSameDirection(other))
            throw new IllegalArgumentException("the two vectors have the same direction");

        Coordinate a= new Coordinate((_head._y.multiply(other._head._z))-(other._head._y.multiply(_head._z)));
        Coordinate b= new Coordinate((other._head._x.multiply(_head._z))-(_head._x.multiply(other._head._z)));
        Coordinate c= new Coordinate((_head._x.multiply(other._head._y))-(_head._y.multiply(other._head._x)));

    return new Vector(new Point3D(a,b,c));
    }

    /**
     * @param v2 -other vector
     * @return true if the vectors have the same direction, or false if they doesn't
     * @throws Exception
     */
    boolean isSameDirection(Vector v2) {
        double scaleNum;
        if(v2._head._x.subtract(Coordinate.ZERO)!=Coordinate.ZERO) {
            scaleNum = (_head._x._coord / v2._head._x._coord);
            if (_head._y.subtract(v2._head._y.scale(scaleNum)) == Coordinate.ZERO
                    &&_head._z.subtract(v2._head._z.scale(scaleNum)) == Coordinate.ZERO)
                return true;
            else
                return false;
        }
        else if (v2._head._y.subtract(Coordinate.ZERO)!=Coordinate.ZERO) {
            scaleNum = (_head._y._coord / v2._head._y._coord);
            if (_head._x.subtract(v2._head._x.scale(scaleNum)) == Coordinate.ZERO
                    && _head._z.subtract(v2._head._z.scale(scaleNum)) == Coordinate.ZERO)
                return true;
            else
                return false;
        }
        else if (v2._head._z.subtract(Coordinate.ZERO)!=Coordinate.ZERO) {
            scaleNum = (_head._z._coord / v2._head._z._coord);
            if (_head._x.subtract(v2._head._x.scale(scaleNum)) == Coordinate.ZERO
                    && _head._y.subtract(v2._head._y.scale(scaleNum)) == Coordinate.ZERO)
                return true;
            else
                return false;
        }
        else
            throw new IllegalArgumentException("zero vector is not valid");
    }

    /**
     * @param other -onother vector
     * @return a vector that is the result of subtracting the second vector from the first.
     * @throws Exception
     */
    public Vector subtract(Vector other) {
        Coordinate a= new Coordinate((_head._x.subtract(other._head._x)));
        Coordinate b= new Coordinate((_head._y.subtract(other._head._y)));
        Coordinate c= new Coordinate((_head._z.subtract(other._head._z)));
        return new Vector(new Point3D(a,b,c));
    }

    /**
     * @param other- onother vector
     * @return a vector that is the result of adding the two vectors
     * @throws Exception
     */
    public Vector add(Vector other) {
        Coordinate a= new Coordinate((_head._x.add(other._head._x)));
        Coordinate b= new Coordinate((_head._y.add(other._head._y)));
        Coordinate c= new Coordinate((_head._z.add(other._head._z)));
        return new Vector(new Point3D(a,b,c));
    }

    /**
     * @return the length of the vector
     */
    public double length (){
        return Math.sqrt(_head._x.multiply(_head._x)  + _head._y.multiply(_head._y) + _head._z.multiply(_head._z));
    }

    /**
     * @return a normalized vector
     * @throws Exception
     */
    public Vector normalize() {
    return this.scale(1/length());
    }

    @Override
    public String toString() {
        return "Vec:(" +
                _head._x +
                ",  " + _head._y +
                ",  " + _head._z +
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