package geometries;

import java.util.List;

public class Geometries {

    List<Intersectable> intersectableList;

/**********constructor*******/
    public Geometries(Intersectable... geometries){
        for (Intersectable g : geometries)
        intersectableList.add(g);
    }

    public void add(Intersectable geometry){
        intersectableList.add(geometry);
    }
}
