package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Triangle extends Plane{

    Point3D p1;
    Point3D p2;
    Point3D p3;


    /********** Constructors ***********/
    public Triangle(Point3D point, Vector vector, Point3D p1, Point3D p2, Point3D p3) {
        super(point, vector);
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
}
