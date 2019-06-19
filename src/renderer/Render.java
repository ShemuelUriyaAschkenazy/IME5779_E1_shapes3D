package renderer;

import elements.LightSource;
import elements.PointLight;
import geometries.Intersectable;
import primitives.*;

import static geometries.Intersectable.GeoPoint;

import primitives.Color;
import scene.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
                    _imageWriter.writePixel(m, n, calcColor(closestPoint, ray).getColor());
                else
                    _imageWriter.writePixel(m, n, _scene.getBackground().getColor());
            }
    }

    private final int MAX_CALC_COLOR_LEVEL = 10;
    private final double MIN_CALC_COLOR_K = 0.005;

    private Color calcColor(GeoPoint intersection, Ray inRay) {
        return calcColor(intersection, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.getAmbientLight().getIntensity());
    }

    /**
     * function that calculates the color of a point in the scene
     *
     * @param intersection
     * @return the color of point
     */
    private Color calcColor(GeoPoint intersection, Ray inRay, int level, double k) {
        if (level == 0 || k < MIN_CALC_COLOR_K) return Color.BLACK;
        Color color = intersection.getGeometry().getEmission();
        //vector view is vector of our view (i.e from the camera to a point)
        Vector view = intersection.getPoint().subtract(_scene.getCamera().getP0()).normalize();
        //vector n is the normal vector from the intersection point
        Vector normal = intersection.getGeometry().getNormal(intersection.getPoint());
        int nShininess = intersection.getGeometry().getMaterial().getNShininess();
        //kd is factor ('k') for diffusion light
        double kd = intersection.getGeometry().getMaterial().getKD();
        //ks is factor ('k') for specular light
        double ks = intersection.getGeometry().getMaterial().getKS();
        double ktr; //ktr is k factor for transparency
        Color colorFromLights = new Color(Color.BLACK);
        for (LightSource lightSource : _scene.getLights()) {
            //calculates light from different points on the light source, and average the results:
            for (Point3D p : lightSource.getListPoints()) {
                Vector l = lightSource.getL(intersection.getPoint(), p);
                if (normal.dotProduct(l) * normal.dotProduct(view) > 0) {// both are with the same sign
                    ktr = transparency(l, normal, intersection);
                    if (!Util.isZero(ktr * k)) {
                        Color lightIntensity = lightSource.getIntensity(intersection.getPoint(), p).scale(ktr);
                        colorFromLights = colorFromLights.add(calcDiffusive(kd, l, normal, lightIntensity),
                                calcSpecular(ks, l, normal, view, nShininess, lightIntensity));
                    }
                }
            }
            colorFromLights = colorFromLights.scale(1.0 / lightSource.getListPoints().size());
            color = color.add(colorFromLights);
        }
        double kr = intersection.getGeometry().getMaterial().getKR();
        double kt = intersection.getGeometry().getMaterial().getKT();
        Ray reflectedRay = constructReflectedRay(normal, intersection.getPoint(), inRay);
        if (reflectedRay != null) {

//            GeoPoint reflectedPoint = getClosestPoint(_scene.getGeometries().findIntersections(reflectedRay));
//            if (reflectedPoint != null) {
//                Color reflectedLight = calcColor(reflectedPoint, reflectedRay, level - 1, k * kr).scale(kr);
//                color = color.add(reflectedLight);
//            }

            //double radius = intersection.getGeometry().getMaterial().getKDG();
            double radius = 3;
            double count=0;
            List<Ray> reflectedRayList = new ArrayList<>();
            reflectedRayList.add(reflectedRay);
            Random random = new Random();
            Point3D intersectionPoint = intersection.getPoint();
            while (reflectedRayList.size() < 1) {
                count++;
            //for (int i = 0; i < 5; i++) {
                double randomRadius = radius * random.nextDouble();
                Vector vector = new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble()).normalize().scale(randomRadius);
                Point3D p = intersectionPoint.add(reflectedRay.getVector().scale(radius *15)).add(vector); //scale by radius*2 in order to reduce the cases that vector that will create by point will be under the tangent line
                Vector newVector = p.subtract(intersectionPoint).normalize();
                Ray ray;
                if (newVector.dotProduct(normal) * reflectedRay.getVector().dotProduct(normal)> 0){
                    //the new vector is the same sign like the original reflected ray (i.e it isn't under the tangent line)
                    ray = new Ray(intersectionPoint, newVector);
                    reflectedRayList.add(ray);
                }
            }

            Color reflectedLight = Color.BLACK;
            GeoPoint reflectedPoint1 = getClosestPoint(_scene.getGeometries().findIntersections(reflectedRay));
            if (reflectedPoint1 != null) {

                for (Ray ray : reflectedRayList) {
                    GeoPoint reflectedPoint = getClosestPoint(_scene.getGeometries().findIntersections(ray));
                    if (reflectedPoint != null) {
                        //Color c=calcColor(reflectedPoint, ray, level - 1, k * kr).scale(kr);
                        //Color c = calcColor(reflectedPoint, ray, level - 1, k * kr).scale(kr);
                        reflectedLight = reflectedLight.add(calcColor(reflectedPoint, ray, level - 1, k * kr).scale(kr));
                    }
                }
                reflectedLight = reflectedLight.scale(1.0 / reflectedRayList.size());
                color = color.add(reflectedLight);
            }
        }

        Ray refractedRay = constructRefractedRay(intersection.getPoint(), inRay);
        if (refractedRay!=null) {

            //double radius = intersection.getGeometry().getMaterial().getKDG();
            double radius= 3;
            List<Ray> refractedRayList = new ArrayList<>();
            refractedRayList.add(reflectedRay);
            Random random = new Random();
            Point3D intersectionPoint = intersection.getPoint();
            while (refractedRayList.size() < 1) {
                double randomRadius = radius * random.nextDouble();
                Vector vector = new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble()).normalize().scale(randomRadius);
                Point3D p = intersectionPoint.add(refractedRay.getVector().scale(radius *15)).add(vector); //scale by radius*2 in order to reduce the cases that vector that will create by point will be under the tangent line
                Vector newVector = p.subtract(intersectionPoint).normalize();
                Ray ray;
                if (newVector.dotProduct(normal) * refractedRay.getVector().dotProduct(normal)> 0){
                    //the new vector is the same sign like the original refracted ray (i.e it isn't under the tangent line)
                    ray = new Ray(intersectionPoint, newVector);
                    refractedRayList.add(ray);
                }
            }

            Color refractedLight = Color.BLACK;
            GeoPoint refractedPoint1 = getClosestPoint(_scene.getGeometries().findIntersections(refractedRay));
            if (refractedPoint1 != null) {

                for (Ray ray : refractedRayList) {
                    GeoPoint refractedPoint = getClosestPoint(_scene.getGeometries().findIntersections(ray));
                    if (refractedPoint != null) {
                        //Color c=calcColor(reflectedPoint, ray, level - 1, k * kr).scale(kr);
                        //Color c = calcColor(reflectedPoint, ray, level - 1, k * kr).scale(kr);
                        refractedLight = refractedLight.add(calcColor(refractedPoint, ray, level - 1, k * kr).scale(kr));
                    }
                }
                refractedLight = refractedLight.scale(1.0 / refractedRayList.size());
                color = color.add(refractedLight);
            }


            //GeoPoint refractedPoint = getClosestPoint(_scene.getGeometries().findIntersections(refractedRay));
            //Color refractedLight;
            //if (refractedPoint != null) {
            //    refractedLight = calcColor(refractedPoint, refractedRay, level - 1, k * kt).scale(kt);
            //    color = color.add(refractedLight);
            //}
        }
        return color;
    }


    /**
     * constructReflectedRay function.
     * calculates the reflected ray that created when the first ray intersect with the object.
     *
     * @param normal       normal vector from object
     * @param intersection intersection point (between ray and object)
     * @param inRay        the ray that comes and intersects the object
     * @return reflection ray
     */
    private Ray constructReflectedRay(Vector normal, Point3D intersection, Ray inRay) {
        //𝒓 = 𝒗 − 𝟐 ∙ 𝒗 ∙ 𝒏 ∙ n
        Vector v = inRay.getVector();
        Vector reflection;
        try {
            reflection = v.add(normal.scale(v.scale(-1).dotProduct(normal) * 2)).normalize();
            return new Ray(intersection, reflection);
        } catch (IllegalArgumentException e) {
            //if the angle is very very small, normal.scale(...) will throw zero vector exception, and we return null:
            return null;
        }
    }

    /**
     * constructRefractedRay function to calculates the refracted ray that continues from the intersection point.
     * *** in this implementation we assume that all geometries are hollow and with zero thickness -
     * So, the ray always continues in the same direction ***
     *
     * @param intersection intersection
     * @param inRay        intersection point (between ray and object)
     * @return refracted ray.
     */
    private Ray constructRefractedRay(Point3D intersection, Ray inRay) {
        return new Ray(intersection, inRay.getVector());
    }

    private static final double EPS = 1.0;

    /**
     * unshaded function check if specific ray from light source to geometry passes through other geometry
     *
     * @param l        vector from light source to point on geometry
     * @param normal   a unit vector from, vertical to intersection point.
     * @param geoPoint current geoPoint (the intersection point)
     * @return true if there is no hindrance, and false otherwise
     */
    private double transparency(Vector l, Vector normal, GeoPoint geoPoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = normal.scale(normal.dotProduct(lightDirection) > 0 ? EPS : -EPS);
        Point3D point = geoPoint.getPoint().add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        double ktr = 1;
        for (GeoPoint gp : intersections)
            ktr *= gp.getGeometry().getMaterial().getKT();
        return ktr;
    }

    /**
     * calcDiffusive function.
     * Calculates the diffusive light.
     *
     * @param Kd             factor reduces the diffusive light.
     * @param l              direction vector from light source to intersection point on geometry.
     * @param normal         normal vector from geometry.
     * @param lightIntensity the color of light.
     * @return diffusive light (color)
     */
    private Color calcDiffusive(double Kd, Vector l, Vector normal, Color lightIntensity) {
        //note: assume that vectors l and normal are normalized.
        return lightIntensity.scale(Kd * Math.abs(l.dotProduct(normal)));
    }

    /**
     * @param Ks             factor reduces the specular light.
     * @param l              direction vector from light source to intersection point on geometry.
     * @param normal         normal vector from geometry.
     * @param view           direction vector
     * @param nShininess     level of shininess (for calculate the specular light)
     * @param lightIntensity color of light from light source
     * @return specular light (color).
     */
    private Color calcSpecular(double Ks, Vector l, Vector normal, Vector view, int nShininess, Color
            lightIntensity) {
        try {
            //note: assume that vectors l and normal are normalized.
            Vector reflection = l.add(normal.scale(l.scale(-1).dotProduct(normal) * 2)).normalize();
            return lightIntensity.scale(Ks * Math.pow(Math.max(0, view.scale(-1).dotProduct(reflection)), nShininess));
        } catch (IllegalArgumentException e) {
            //if normal is orthogonal to l, there is no reflection. (exception will be thrown due to scale by dot product result of 0)
            return Color.BLACK;
        }
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



