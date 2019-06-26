package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * class with list of shape.
 */
public class Geometries implements Intersectable {


    List<Intersectable> intersectableList = new ArrayList<>();

    /**
     * constructor received many geometries and adding them to the list
     *
     * @param geometries geometries to add the list
     */
    /* *********constructor*******/
    public Geometries(Intersectable... geometries) {
        for (Intersectable g : geometries)
            intersectableList.add(g);
    }

    public List<Intersectable> getIntersectableList() {
        return intersectableList;
    }

    /**
     * function who adding geometry shape to list of the class
     *
     * @param geometry geometry shape
     */
    public void add(Intersectable... geometry) {
        for (Intersectable g : geometry)
            intersectableList.add(g);
    }

    /**
     * function that produce list of  Intersections points between the ray and the geometries are exist at the list
     *
     * @param ray ray
     * @return return list of  Intersections points between the ray and the geometries are exist at the list
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> list = new ArrayList<>();
        for (Intersectable item : intersectableList) {
            List<GeoPoint> currentIntersections = item.findIntersections(ray);
            if (currentIntersections != null)
                list.addAll(currentIntersections);
        }
        return list.isEmpty() ? null : list;
    }
}
