package geometries;


import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Sphere extends RadialGeometry implements Geometry{



    Point3D center;

    /********** Constructors ***********/
    public Sphere (double radius, Point3D p) throws Exception{
        super(radius);
        center=new Point3D(p);
    }

    /************** Getters/Setters *******/

    public Point3D getCenter() {
        Point3D centerPoint=new Point3D(center);
        return centerPoint;
    }


    @Override
    public Vector getNormal(Point3D point3D) throws Exception {
        Vector vectorNormal = new Vector(point3D.subtract(this.center));
        return vectorNormal.normalize();
    }

    /**
     * @param ray from the camera
     * @return list of Intersections points between the ray and the sphere
     * @throws Exception
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) throws Exception {
        Vector u= center.subtract(ray.getPoint());
        double uLength= u.length();
        double DistTilHalf= ray.getVector().dotProduct(u);
        double d=Math.sqrt(uLength*uLength - (DistTilHalf*DistTilHalf));
        double halfChord=Math.sqrt(_radius*_radius-(d*d));

        double t1=DistTilHalf+halfChord;
        double t2=DistTilHalf-halfChord;

        List<Point3D> intersectionsPoints= new ArrayList<Point3D>();
        if (t1>=0)
            intersectionsPoints.add(ray.getPoint().add(ray.getVector().scale(t1)));
        if (t2>=0)
            intersectionsPoints.add(ray.getPoint().add(ray.getVector().scale(t2)));
        return intersectionsPoints;
    }
}
