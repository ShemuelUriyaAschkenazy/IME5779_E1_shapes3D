package renderer;

import elements.LightSource;
import geometries.Intersectable;
import primitives.Color;
import static geometries.Intersectable.GeoPoint;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.awt.*;
import java.util.List;

/**
 * render class
 * using for render an image
 * contain 2 fields:
 * 1.imageWriter
 * 2.scene
 */
public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;

    /**
     * constructor
     *
     * @param imageWriter an imageWriter object that responsible for the pixels and colors
     * @param scene       a scene of camera and geometries
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    /**
     * function for render the image, by painting the pixels in a imagine view plane, according to scene
     *
     * @param i amount pixels im x axis in the imagine view plane
     * @param j amount pixels im y axis in the imagine view plane
     */
    public void renderImage(int i, int j) {
        Ray ray;
        for (int m = 0; m < i; m++)
            for (int n = 0; n < j; n++) {
                ray = _scene.getCamera().constructRayThroughPixel
                        (_imageWriter.getNx(), _imageWriter.getNy(), m, n, _scene.getDistCameraScreen(), _imageWriter.getWidth(), _imageWriter.getHeight());
                List<GeoPoint> intersectionPoint = _scene.getGeometries().findIntersections(ray);
                if (intersectionPoint.isEmpty())
                    _imageWriter.writePixel(m, n, _scene.getBackground().getColor());
                GeoPoint closestPoint = getClosestPoint(intersectionPoint);
                if (closestPoint != null)
                    _imageWriter.writePixel(m, n, calcColor(closestPoint).getColor());
                else
                    _imageWriter.writePixel(m, n, _scene.getBackground().getColor());
            }
    }

    /**
     * function that calculates the color of a point in the scene
     *
     * @param intersection
     * @return the color of point
     */
    private Color calcColor(GeoPoint intersection) {
        Color color = _scene.getAmbientLight().getIntensity();
        color = color.add(intersection.getGeometry().getEmission());
        //vector v is vector of our view (i.e from the camera to a point)
        Vector v = intersection.getPoint().subtract(_scene.getCamera().getP0()).normalize();
        //vector n is the normal vector from the intersection point
        Vector n = intersection.getGeometry().getNormal(intersection.getPoint());
        int nShininess = intersection.getGeometry().getMaterial().getNShininess();
        //kd is factor ('k') for diffusion light
        double kd = intersection.getGeometry().getMaterial().getKD();
        //ks is factor ('k') for specular light
        double ks = intersection.getGeometry().getMaterial().getKS();
        for (LightSource lightSource : _scene.getLights()) {
            Vector l = lightSource.getL(intersection.getPoint());
            if (n.dotProduct(l)*n.dotProduct(v) > 0) { // both are with the same sign
                Color lightIntensity = lightSource.getIntensity(intersection.getPoint());
                color= color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcDiffusive(double Kd, Vector l, Vector n, Color lightIntensity){
        return lightIntensity.scale(Kd*Math.abs(l.dotProduct(n)));
    }
    private Color calcSpecular(double Ks, Vector l, Vector normal ,Vector view,int nShininess ,Color lightIntensity){
        //Vector reflection= l.subtract(normal.scale(2*l.dotProduct(normal))).normalize();
        Vector reflection= l.add(normal.scale(l.scale(-1).dotProduct(normal)*2)).normalize();
        return lightIntensity.scale(Ks* Math.pow(Math.max(0,view.scale(-1).dotProduct(reflection)),nShininess));
    }

    /**
     * function to calculate the closest point to camera, from list of intersection points
     *
     * @param intersectionPoint list of intersection points
     * @return the closest point
     */
    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoint) {
        if (!intersectionPoint.isEmpty()) {
            GeoPoint closestPoint = intersectionPoint.get(0);
            for (int i = 1; i < intersectionPoint.size(); i++) {
                if (intersectionPoint.get(i).getPoint().distanceInSquare(_scene.getCamera().getP0()) <
                        closestPoint.getPoint().distanceInSquare(_scene.getCamera().getP0()))
                    closestPoint = intersectionPoint.get(i);
            }
            return closestPoint;
        } else return null;
    }

    /**
     * function to draw a grid on our image, by painting the interval pixels
     *
     * @param interval number that the pixels ,that their index is a multiple of this number, are part of the grid.
     */
    public void printGrid(int interval) {
        Color white = new Color(255, 255, 255);
        for (int i = 0; i < _imageWriter.getNx(); i++) {
            for (int j = 0; j < _imageWriter.getNy(); j++) {
                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(i, j, white.getColor());
            }
        }
    }


}



