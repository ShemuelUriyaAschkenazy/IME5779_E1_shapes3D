package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *LightSource interface
 * contains 2 methods:
 * getIntensity- for getting the light color
 * getL- for getting the vector from light source to point
 */
public interface LightSource {
    /**
     * getIntensity function
     * uses for gets the light color that the light source add to the point (from the viewer point view).
     * @param point3D the point on the geometry
     * @return the light color
     */
    public Color getIntensity(Point3D point3D);

    /**
     * getL function
     * uses to gets the vector from light source to the point
     * @param point3D the point on geometry
     * @return the vector
     */
    public Vector getL(Point3D point3D);
}
