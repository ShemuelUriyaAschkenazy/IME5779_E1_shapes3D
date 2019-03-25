package geometries;

public abstract class RadialGeometry {
    double _radius;

    /********** Constructors ***********/
public  RadialGeometry(double num){
    _radius = num;
}
public  RadialGeometry(RadialGeometry radius){
    _radius = radius._radius;
}
    /************** Getters/Setters *******/
    public double get_radius() {
        return _radius;
    }




}
