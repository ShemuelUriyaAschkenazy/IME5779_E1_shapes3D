package renderer;

import geometries.Geometry;
import sun.reflect.generics.tree.Tree;

import java.util.List;

public class BoundingVolumeHierarchy {
   Geometry _geometry;
   List <BoundingVolumeHierarchy> _boundingVolumeHierarchy;

   public BoundingVolumeHierarchy(Geometry geometry){
       this._geometry = geometry;
    }

    public  BoundingVolumeHierarchy(BoundingVolumeHierarchy... boundingList){
       for (BoundingVolumeHierarchy b : boundingList){
           this._boundingVolumeHierarchy.add(b);
       }
    }



}
