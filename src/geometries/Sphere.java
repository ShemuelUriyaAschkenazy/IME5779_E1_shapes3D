package geometries;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.List;

public class Sphere extends RadialGeometry implements Geometry {
    private Point3D center;

    /********** Constructors ***********/
    public Sphere(double radius, Point3D p){
        super(radius);
        center = new Point3D(p);
    }

    /************** Getters/Setters *******/

    public Point3D getCenter() {
        Point3D centerPoint = new Point3D(center);
        return centerPoint;
    }

    @Override
    public Vector getNormal(Point3D point3D) {
        return point3D.subtract(this.center).normalize();
    }

    /**
     * @param ray from the camera
     * @return list of Intersections points between the ray and the sphere
     * @throws Exception
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector u = center.subtract(ray.getPoint());
        double uLength = u.length();
        double DistTilHalf = ray.getVector().dotProduct(u);
        double d = Math.sqrt(uLength * uLength - (DistTilHalf * DistTilHalf));
        if (d > getRadius())
            return null;

        double halfChord = Math.sqrt(getRadius() * getRadius() - (d * d));

        double t1 = DistTilHalf + halfChord;
        double t2 = DistTilHalf - halfChord;

        List<Point3D> intersectionsPoints = new ArrayList<Point3D>();
        if (t1 >= 0)
            intersectionsPoints.add(ray.getPoint().add(ray.getVector().scale(t1)));
        if (t2 >= 0)
            intersectionsPoints.add(ray.getPoint().add(ray.getVector().scale(t2)));
        return intersectionsPoints;
    }
}
