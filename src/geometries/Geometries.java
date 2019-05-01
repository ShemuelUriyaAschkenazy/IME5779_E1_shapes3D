package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {

    List<Intersectable> intersectableList = new ArrayList<>();

    /**********constructor*******/
    public Geometries(Intersectable... geometries) {
        for (Intersectable g : geometries)
            intersectableList.add(g);
    }

    public void add(Intersectable... geometry) {
        for (Intersectable g : geometry)
        intersectableList.add(g);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        // TODO
        return null;
    }
}
