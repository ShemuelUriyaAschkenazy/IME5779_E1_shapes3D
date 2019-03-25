
package main;

import primitives.Coordinate;
import primitives.Vector;
public class Main {
    public static void Main() {
        Coordinate a = new Coordinate(2);
        Coordinate b = new Coordinate(4);
        Coordinate c = new Coordinate(5);
        Vector vector1 = new Vector(a, b, c);

        Coordinate d = new Coordinate(6);
        Coordinate e = new Coordinate(1);
        Coordinate f = new Coordinate(0);
        Vector vector2 = new Vector(d, e, f);

        Vector vector3 = vector1.normalize();
        System.out.println(vector3);
    }
}