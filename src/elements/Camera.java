package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


public class Camera {
    Point3D _p0;
    Vector _vUp;
    Vector _vTo;
    Vector _vRight;

    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        _p0 = p0;
        _vUp = _vUp.normalize();
        _vTo = vTo.normalize();
        _vRight=_vUp.crossProduct(_vTo).normalize();
    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getVUp() {
        return _vUp;
    }

    public Vector getVTo() {
        return _vTo;
    }

    public Vector getVRight() {
        return _vRight;
    }

    Ray constructRayThroughPixel (int Nx, int Ny, int i, int j, double screenDistance, double screenWidth, double screenHeight){

    }
}
