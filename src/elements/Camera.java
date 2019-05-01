package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * camera class.
 * include 4 fields:
 * _p0 - point of location
 * _vUp - vector from location to up
 * _vTo - vector from location to the view plane
 * _vRight - vector from location to the right
 */
public class Camera {
    Point3D _p0;
    Vector _vUp;
    Vector _vTo;
    Vector _vRight;

    /* **constructor***/

    /***
     * constructor
     * @param p0 camera location point
     * @param vUp vector from location to up
     * @param vTo vector from location to the view plane
     */
    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        _p0 = p0;
        _vUp = vUp.normalize();
        _vTo = vTo.normalize();
        _vRight = _vTo.crossProduct(_vUp).normalize();
    }

    /**
     * to get the camera location point
     *
     * @return the camera location point
     */
    public Point3D getP0() {
        return _p0;
    }

    /**
     * to get the vector from camera location to up
     *
     * @return the vector to up
     */
    public Vector getVUp() {
        return _vUp;
    }

    /**
     * to get the vector from camera location to the view plane
     *
     * @return the vector to view plane
     */
    public Vector getVTo() {
        return _vTo;
    }

    /**
     * to get the vector from camera location to right
     *
     * @return the vector to right
     */
    public Vector getVRight() {
        return _vRight;
    }

    /**
     * calculate the ray from camera that passes through a particular pixel
     *
     * @param Nx             number of pixels in the x axis
     * @param Ny             number of pixels in the y axis
     * @param i              horizontal index of pixel (from left to right)
     * @param j              vertical index of pixel (from up to down)
     * @param screenDistance distance from camera to the view plane
     * @param screenWidth    width of the view plane
     * @param screenHeight   height of the view plane
     * @return the ray from camera to the pixel
     */
    Ray constructRayThroughPixel(int Nx, int Ny, int i, int j, double screenDistance, double screenWidth, double screenHeight) {
        //image center
        Point3D screenCenter = _p0.add(_vTo.scale(screenDistance));
        //ratio (pixel height&width)
        double yRatio = screenHeight / Ny;
        double xRatio = screenWidth / Nx;
        //pixel[i,j] center
        //multiplying of x value of pixel with the pixel width. and adding half of the width to get the distance till the center.
        double XOfPixel = (i - Nx / 2.0) * xRatio + xRatio / 2.0;
        double YOfPixel = (j - Ny / 2.0) * yRatio + yRatio / 2.0;
        Point3D PixelIJ = screenCenter;
        if (XOfPixel != 0) PixelIJ = PixelIJ.add(_vRight.scale(XOfPixel));
        if (YOfPixel != 0) PixelIJ = PixelIJ.add(_vUp.scale(-YOfPixel));
        //direction vector to pixel center
        Vector direction = PixelIJ.subtract(_p0);
        return new Ray(_p0, direction);
    }

    @Override
    public String toString() {
        return "Camera{" +
                "_p0=" + _p0 +
                ", _vUp=" + _vUp +
                ", _vTo=" + _vTo +
                ", _vRight=" + _vRight +
                '}';
    }
}

