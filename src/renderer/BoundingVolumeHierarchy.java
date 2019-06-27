package renderer;

import geometries.Geometries;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class BoundingVolumeHierarchy {
    private Geometry _geometry;
    private List<BoundingVolumeHierarchy> _boundingVolumeList = new ArrayList<>();
    private boolean _isLeaf = false;
    private Point3D _minP, _maxP;
    private double _xMax, _xMin;
    private double _yMax, _yMin;
    private double _zMax, _zMin;

    public BoundingVolumeHierarchy(Point3D min, Point3D max) {
        _minP = new Point3D(min);
        _maxP = new Point3D(max);
    }

    public BoundingVolumeHierarchy() {
    }

    public double get_xMax() {
        return _xMax;
    }

    public double get_xMin() {
        return _xMin;
    }

    public double get_yMax() {
        return _yMax;
    }

    public double get_yMin() {
        return _yMin;
    }

    public double get_zMax() {
        return _zMax;
    }

    public double get_zMin() {
        return _zMin;
    }

    public BoundingVolumeHierarchy(Geometry geometry) {
        this._geometry = geometry;
        _isLeaf = true;
        _minP = new Point3D(geometry.getMinX(), geometry.getMinY(), geometry.getMinZ());
        _maxP = new Point3D(geometry.getMaxX(), geometry.getMaxY(), geometry.getMaxZ());

        _xMax = geometry.getMaxX();
        _xMin = geometry.getMinX();
        _yMax = geometry.getMaxY();
        _yMin = geometry.getMinY();
        _zMax = geometry.getMaxZ();
        _zMin = geometry.getMinZ();
    }

    public BoundingVolumeHierarchy(List<Intersectable> intersectables) {
        List<Geometry> geometries = new ArrayList<>();
        List<Intersectable> intersectables2 = ((Geometries) (intersectables.get(0))).getIntersectableList();
        for (Intersectable i : intersectables2) {
            geometries.add((Geometry) i);
        }
        _xMax = geometries.get(0).getMaxX();
        _xMin = geometries.get(0).getMinX();
        _yMax = geometries.get(0).getMaxY();
        _yMin = geometries.get(0).getMinY();
        _zMax = geometries.get(0).getMaxZ();
        _zMin = geometries.get(0).getMinZ();
        for (Geometry g : geometries) {
            if (g.getMinX() < _xMin)
                _xMin = g.getMinX();
            if (g.getMinY() < _yMin)
                _yMin = g.getMinY();
            if (g.getMinZ() < _zMin)
                _zMin = g.getMinZ();
            if (g.getMaxX() > _xMax)
                _xMax = g.getMaxX();
            if (g.getMaxY() > _yMax)
                _yMax = g.getMaxY();
            if (g.getMaxZ() > _zMax)
                _zMax = g.getMaxZ();
        }
        _minP = new Point3D(_xMin, _yMin, _zMin);
        _maxP = new Point3D(_xMax, _yMax, _zMax);

        for (Geometry g : geometries)
            _boundingVolumeList.add(new BoundingVolumeHierarchy(g));

        if (this._boundingVolumeList.size() > 1)
            splitBounds(this);
        else _isLeaf = true;
    }

    private void splitBounds(BoundingVolumeHierarchy b) {
        double dx = b._xMax - b._xMin;
        double dy = b._yMax - b._yMin;
        double dz = b._zMax - b._zMin;
        double greatest = Math.max(dx, Math.max(dy, dz));

        BoundingVolumeHierarchy childMin = new BoundingVolumeHierarchy();
        BoundingVolumeHierarchy childMax = new BoundingVolumeHierarchy();
        BoundingVolumeHierarchy childMiddle = new BoundingVolumeHierarchy();

        List<BoundingVolumeHierarchy> main = new ArrayList<>();
        main = (ArrayList<BoundingVolumeHierarchy>) ((ArrayList) (b._boundingVolumeList)).clone();

        if (greatest == dx) {
            for (BoundingVolumeHierarchy bv : main) {
                if ((bv.get_xMin()) > b.get_xMin() + dx / 2) {
                    childMax._boundingVolumeList.add(bv);
                    b._boundingVolumeList.remove(bv);
                } else if (bv.get_xMax() < b.get_xMin() + dx / 2) {
                    childMin._boundingVolumeList.add(bv);
                    b._boundingVolumeList.remove(bv);
                } else {
                    childMiddle._boundingVolumeList.add(bv);
                    b._boundingVolumeList.remove(bv);
                }
            }
        } else if (greatest == dy) {
            for (BoundingVolumeHierarchy bv : main) {
                if ((bv.get_yMin()) > b.get_yMin() + dy / 2) {
                    childMax._boundingVolumeList.add(bv);
                    b._boundingVolumeList.remove(bv);
                } else if (bv.get_yMax() < b.get_yMin() + dy / 2) {
                    childMin._boundingVolumeList.add(bv);
                    b._boundingVolumeList.remove(bv);
                } else {
                    childMiddle._boundingVolumeList.add(bv);
                    b._boundingVolumeList.remove(bv);
                }
            }

        } else {
            for (BoundingVolumeHierarchy bv : main) {
                if ((bv.get_zMin()) > b.get_zMin() + dz / 2) {
                    childMax._boundingVolumeList.add(bv);
                    b._boundingVolumeList.remove(bv);
                } else if (bv.get_zMax() < b.get_zMin() + dz / 2) {
                    childMin._boundingVolumeList.add(bv);
                    b._boundingVolumeList.remove(bv);
                } else {
                    childMiddle._boundingVolumeList.add(bv);
                    b._boundingVolumeList.remove(bv);
                }
            }
        }

        childMin.setValues(childMin);
        childMax.setValues(childMax);
        childMiddle.setValues(childMiddle);

        if (childMin._boundingVolumeList.size() > 1)
            b._boundingVolumeList.add(childMin);
        else if (childMin._boundingVolumeList.size() == 1) {
            childMiddle._isLeaf = true;
            b._boundingVolumeList.add(childMin._boundingVolumeList.get(0));
        }
        if (childMax._boundingVolumeList.size() > 1)
            b._boundingVolumeList.add(childMax);
        else if (childMax._boundingVolumeList.size() == 1) {
            childMiddle._isLeaf = true;
            b._boundingVolumeList.add(childMax._boundingVolumeList.get(0));
        }
        if (childMiddle._boundingVolumeList.size() > 1)
            b._boundingVolumeList.add(childMiddle);
        else if (childMiddle._boundingVolumeList.size() == 1) {
            childMiddle._isLeaf = true;
            b._boundingVolumeList.add(childMiddle._boundingVolumeList.get(0));
        }

        if (this._boundingVolumeList.size() == 1) {
            for (BoundingVolumeHierarchy bv : _boundingVolumeList.get(0)._boundingVolumeList)
                _boundingVolumeList.add(bv);
            _boundingVolumeList.remove(0);
            return;
        }

        for (BoundingVolumeHierarchy bv : b._boundingVolumeList) {
            if (bv._boundingVolumeList.size() > 1) {
                System.out.println(bv);
                splitBounds(bv);
            }
        }
    }

    private void setValues(BoundingVolumeHierarchy b) {
        if (b._boundingVolumeList.size() < 1)
            return;
        b._xMax = b._boundingVolumeList.get(0).get_xMax();
        b._xMin = b._boundingVolumeList.get(0).get_xMin();
        b._yMax = b._boundingVolumeList.get(0).get_yMax();
        b._yMin = b._boundingVolumeList.get(0).get_xMin();
        b._zMax = b._boundingVolumeList.get(0).get_zMax();
        b._zMin = b._boundingVolumeList.get(0).get_zMin();
        for (BoundingVolumeHierarchy bv : b._boundingVolumeList) {
            if (bv.get_xMin() < b._xMin)
                b._xMin = bv.get_xMin();
            if (bv.get_yMin() < _yMin)
                b._yMin = bv.get_yMin();
            if (bv.get_zMin() < b._zMin)
                b._zMin = bv.get_zMin();
            if (bv.get_xMax() > b._xMax)
                b._xMax = bv.get_xMax();
            if (bv.get_yMax() > b._yMax)
                b._yMax = bv.get_yMax();
            if (bv.get_zMax() > b._zMax)
                b._zMax = bv.get_zMax();
        }
        b._minP = new Point3D(b._xMin, b._yMin, b._zMin);
        b._maxP = new Point3D(b._xMax, b._yMax, b._zMax);
    }

    public boolean isIntersect(Ray r, double t0, double t1) {
        double _tmin, _tmax, _tymin, _tymax, _tzmin, _tzmax;
        Point3D vHead = r.getVector().getHead();
        double x = vHead.getX().getCoordinate();
        double y = vHead.getY().getCoordinate();
        double z = vHead.getZ().getCoordinate();
        Vector inv_direction = new Vector(1 / x, 1 / y, 1 / z);
        _tmin = (x < 0 ? _maxP : _minP).getX().subtract(r.getPoint().getX()).getCoordinate() * inv_direction.getHead().getX().getCoordinate();
        _tmax = (x >= 0 ? _maxP : _minP).getX().subtract(r.getPoint().getX()).getCoordinate() * inv_direction.getHead().getX().getCoordinate();
        _tymin = (y < 0 ? _maxP : _minP).getY().subtract(r.getPoint().getY()).getCoordinate() * inv_direction.getHead().getY().getCoordinate();
        _tymax = (y >= 0 ? _maxP : _minP).getY().subtract(r.getPoint().getY()).getCoordinate() * inv_direction.getHead().getY().getCoordinate();
        if ((_tmin > _tymax) || (_tymin > _tmax))
            return false;
        if (_tymin > _tmin)
            _tmin = _tymin;
        if (_tymax < _tmax)
            _tmax = _tymax;
        _tzmin = (z < 0 ? _maxP : _minP).getZ().subtract(r.getPoint().getZ()).getCoordinate() * inv_direction.getHead().getZ().getCoordinate();
        _tzmax = (z >= 0 ? _maxP : _minP).getZ().subtract(r.getPoint().getZ()).getCoordinate() * inv_direction.getHead().getZ().getCoordinate();
        if ((_tmin > _tzmax) || (_tzmin > _tmax))
            return false;
        if (_tzmin > _tmin)
            _tmin = _tzmin;
        if (_tzmax < _tmax)
            _tmax = _tzmax;

        //System.out.println(_tmin);
        //System.out.println(_tmax);
        //System.out.println(r.getPoint().add(r.getVector().scale(_tmin)));
        //System.out.println(r.getPoint().add(r.getVector().scale(_tmax)));

        if (!(_tmin < t1) && (_tmax > t0)) return false;
        return true;

//        if (!(_tmin < t1) && (_tmax > t0)) return false;
//        if (this._isLeaf) return true;
//
//        for (BoundingVolumeHierarchy bound : this._boundingVolumeList)
//            if (bound.isIntersect(r, t0, t1)) return true;
//
//        return false;
    }


    @Override
    public String toString() {
        return "_minP: " + _minP + " _maxP: " + _maxP;
    }
}
