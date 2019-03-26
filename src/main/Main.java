
package main;

import primitives.Coordinate;
import primitives.Vector;
public class Main {
    public static void main(String[] args) throws Exception {
        Coordinate a = new Coordinate(2);
        Coordinate b = new Coordinate(4);
        Coordinate c = new Coordinate(5);
        Vector vector1 = new Vector(a, b, c);

        Coordinate d = new Coordinate(6);
        Coordinate e = new Coordinate(1);
        Coordinate f = new Coordinate(0);
        Vector vector2 = new Vector(d, e, f);

        Vector vector3 = vector1.normalize();
        Vector vector4= vector1.crossProduct(vector2);
        double result =vector1.dotProduct(vector2);

        System.out.println(vector3);
        System.out.println(vector4);
        System.out.println(result);

    }
}