package primitives;

public class Ray {
    Point3D _point;
    Vector _vector;

    @Override
    public String toString() {
        return "Ray{" +
                "_point=" + _point +
                ", _vector=" + _vector +
                '}';
    }

    /********** Constructors ***********/
    public  Ray(Point3D _point, Vector _vector){

        this._point = _point;
        this._vector = _vector;
    }
    /************** Getters/Setters *******/
    public Point3D get_point() {
        return _point;
    }

    public Vector get_vector() {
        return _vector;
    }


}
