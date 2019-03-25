package primitives;

import primitives.Coordinate;

public class Vector {
    Coordinate _x;
    Coordinate _y;
    Coordinate _z;

    public Vector(Coordinate x, Coordinate y,Coordinate z) {
        _x=new Coordinate(x);
        _y=new Coordinate(y);
        _z=new Coordinate(z);

    }

    public Vector(Vector other) {
        _x=other.getX();
        _y=other.getY();
        _z=other.getZ();
    }

    /************** Getters/Setters *******/
    public Coordinate getX() {
        return new Coordinate(_x);
    }
    public Coordinate getY() {
        return new Coordinate(_y);
    }
    public Coordinate getZ() {
        return new Coordinate(_z);
    }

    /************** Operations ***************/

    public Vector scale(double num) {
        return new Vector(_x.scale(num),_y.scale(num),_z.scale(num));
    }

    public Vector dotProduct(Vector other){
        return
    }

    public Vector crossProduct(Vector other){
    return
    }

    public double distance (){
        return Math.sqrt();
    }

    public Vector normalize (){

    }





}
