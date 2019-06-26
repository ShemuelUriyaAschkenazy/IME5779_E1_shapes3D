package renderer;

import geometries.Geometry;
import primitives.Coordinate;
import primitives.Point3D;
import sun.reflect.generics.tree.Tree;

import java.util.List;

public class BoundingVolumeHierarchy {
    Geometry _geometry;
    List<BoundingVolumeHierarchy> _boundingVolumeHierarchy;
    boolean _isLeaf = false;
    Point3D minP, maxP;
    private double _xMax, _xMin;
    private double _yMax, _yMin;
    private double _zMax, _zMin;


    public BoundingVolumeHierarchy(Geometry geometry) {
        this._geometry = geometry;
        _isLeaf = true;
        minP= new Point3D(geometry.getMinX(),geometry.getMinY(),geometry.getMinZ());
        maxP= new Point3D(geometry.getMaxX(),geometry.getMaxY(),geometry.getMaxZ());

        _xMax = geometry.getMaxX();
        _xMin = geometry.getMinX();
        _yMax = geometry.getMaxY();
        _yMin = geometry.getMinY();
        _zMax = geometry.getMaxZ();
        _zMin = geometry.getMinZ();
    }

    public BoundingVolumeHierarchy(BoundingVolumeHierarchy... boundingList) {
        for (BoundingVolumeHierarchy b : boundingList) {
            this._boundingVolumeHierarchy.add(b);
        }
    }


}
