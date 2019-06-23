package primitives;

/**
 * the class contain point3d and vector
 * The class has 2 constructors:
 * a. get point3D and vector
 * b. get ray and copy to new ray
 */
public class Ray {
    private Point3D _point;
    private Vector _vector;
    private static final double EPS = 0.1;

    /**
     * the function return string with the parameters of the ray
     *
     * @return string of the ray parameters vector and point
     */
    @Override
    public String toString() {
        return _point+
                " "+ _vector
                ;
    }

    /**
     * constructor get point3D and vector
     *
     * @param _point  point3D
     * @param _vector vector
     */
    /* ********* Constructors ***********/
    public Ray(Point3D _point, Vector _vector) {
        this._point = _point;
        this._vector = _vector.normalize();
    }

    public Ray(Point3D _point, Vector v, Vector normal) {
        this._vector = v.normalize();
        Vector epsVector = normal.scale(normal.dotProduct(v) > 0 ? EPS : -EPS);
        this._point = _point.add(epsVector);
    }

    /**
     * constructor get ray and copy to new ray
     *
     * @param ray ray
     */
    public Ray(Ray ray) {
        _point = new Point3D(ray._point);
        _vector = new Vector(ray._vector);
    }

    /**
     * function return the point of the ray
     *
     * @return the point of the ray
     */
    /* ************* Getters/Setters *******/
    public Point3D getPoint() {
        return _point;
    }

    /**
     * function return the vector of the ray
     *
     * @return the vector of the ray
     */
    public Vector getVector() {
        return _vector;
    }


}
