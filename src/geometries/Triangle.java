package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

public class Triangle extends Plane{

    Point3D p1;
    Point3D p2;
    Point3D p3;

    /********** Constructors ***********/

    public Triangle(Point3D p1, Point3D p2, Point3D p3){
        super(p1, p2 ,p3);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

    }

    /************** Getters/Setters *******/
    public Point3D getP1() {
        return p1;
    }

    public Point3D getP2() {
        return p2;
    }

    public Point3D getP3() {
        return p3;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                ", p3=" + p3 +
                '}';
    }

    /**
     * find the intersections points between a ray and the triangle
     * @param ray from the camera
     * @return list of Intersections points
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D intersectsPlane;
        List<Point3D> intersectionsWithPlane =  super.findIntersections(ray);
        if (intersectionsWithPlane==null||intersectionsWithPlane.isEmpty())
            return null;
        else
            intersectsPlane=intersectionsWithPlane.get(0);
        Vector v1= p1.subtract(ray.getPoint());
        Vector v2= p2.subtract(ray.getPoint());
        Vector v3= p3.subtract(ray.getPoint());
        Vector n1;
        //in a case that ray point is one of the edges, or on their continuances,
        //so two vectors will be on the same line, and exception will thrown after cross product.
        //we check whether the point is on the triangle or on the edges's continuances.
        try { n1 = v1.crossProduct(v2).normalize(); }
        catch (IllegalArgumentException e){
            if(p1.distanceInSquare(p2)>=v1.length2()&&p1.distanceInSquare(p2)>=v2.length2())
                return intersectionsWithPlane;
            else return null;
        }
        Vector n2;
        try { n2=v2.crossProduct(v3).normalize(); }
        catch (IllegalArgumentException e){
            if(p2.distanceInSquare(p3)>=v2.length2()&&p2.distanceInSquare(p3)>=v3.length2())
                return intersectionsWithPlane;
            else return null;
        }
        Vector n3;
        try { n3=v3.crossProduct(v1).normalize(); }
        catch (IllegalArgumentException e){
            if(p1.distanceInSquare(p3)>=v1.length2()&&p1.distanceInSquare(p3)>=v3.length2())
                return intersectionsWithPlane;
            else return null;
        }

        Vector temp;
        //if ray point is itself the intersection point, the next point will try creating a zero vector.
        // in this case, the direction isn't significant for determining the intersection point.
        //therefore, we choose the normal vector instead
        try {
            temp = intersectsPlane.subtract(ray.getPoint());
        }
        catch (IllegalArgumentException e)
        {
            temp= this.getNormal(ray.getPoint());
        }
        System.out.println("temp"+temp);
        if ((n1.dotProduct(temp)>0
                &&n2.dotProduct(temp)>0
                &&n3.dotProduct(temp)>0)
        ||(n1.dotProduct(temp)<0
                &&n2.dotProduct(temp)<0
                &&n3.dotProduct(temp)<0))
        return intersectionsWithPlane;
        else return null;
    }
}
