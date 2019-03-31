package geometries;


import primitives.Point3D;

public class Sphere extends RadialGeometry {



    Point3D center;

    /********** Constructors ***********/
    public Sphere (double radius, Point3D p) throws Exception{
        super(radius);
    }

    /************** Getters/Setters *******/

    public Point3D getCenter() {
        return center;
    }





}
