package primitives;

public class Ray {
    private Point3D _point;
    private Vector _vector;

    @Override
    public String toString() {
        return "Ray{" +
                "_point=" + _point +
                ", _vector=" + _vector +
                '}';
    }

    /********** Constructors ***********/
    public Ray(Point3D _point, Vector _vector) {
        this._point = _point;
        this._vector = _vector.normalize();
    }

    public Ray(Ray ray) {
        _point = new Point3D(ray.getPoint());
        _vector = new Vector(ray.getVector());
    }

    /************** Getters/Setters *******/
    public Point3D getPoint() {
        return _point;
    }

    public Vector getVector() {
        return _vector;
    }


}
