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

    /**
     * the function return string with the parameters of the ray
     *
     * @return string of the ray parameters vector and point
     */
    @Override
    public String toString() {
        return "Ray{" +
                "_point=" + _point +
                ", _vector=" + _vector +
                '}';
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

    /**
     * constructor get ray and copy to new ray
     *
     * @param ray ray
     */
    public Ray(Ray ray) {
        _point = new Point3D(ray.getPoint());
        _vector = new Vector(ray.getVector());
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
