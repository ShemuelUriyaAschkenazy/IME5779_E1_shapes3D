// Shimon Balsam 203076880 shimonaabb@gmail.com
// Shemuel Uriya Ashkenazy 312194244 uriyaas@gmail.com
package main;
import java.text.DecimalFormat;


import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;
public class Main {
    //uses for presenting formatting double in the toStrong function
    public static DecimalFormat df2 = new DecimalFormat("#.##");

    public static void main(String[] args) throws Exception {
        Coordinate a = new Coordinate(2);
        Coordinate b = new Coordinate(4);
        Coordinate c = new Coordinate(5);
        Coordinate d = new Coordinate(6);
        Coordinate e = new Coordinate(1);
        Coordinate f = new Coordinate(0);

        Point3D p1= new Point3D(a,b,c);
        Point3D p2= new Point3D(e,e,f);

        Vector vector1 = new Vector(p1);
        Vector vector2 = new Vector(new Point3D(d, e, f));


        System.out.println("substract of "+p1+" by "+p2+" :");
        System.out.println(p1.subtract(p2)+"\n");

        System.out.println("add of "+p1+" and "+vector1+" :");
        System.out.println(p1.add(vector1)+"\n");

        System.out.println("distance between of "+p1+" and "+p2+" :");
        System.out.println(df2.format(p1.distance(p2))+"\n");

        System.out.println("normalize of "+vector1+" :");
        System.out.println(vector1.normalize()+"\n");

        System.out.println("substract of "+vector1+" by "+vector2+" :");
        System.out.println(vector1.substract(vector2)+"\n");

        System.out.println("add of "+vector1+" and "+vector2+" :");
        System.out.println(vector1.add(vector2)+"\n");

        System.out.println("cross product of "+vector1+" and "+vector2+" :");
        System.out.println(vector1.crossProduct(vector2)+"\n");

        System.out.println("scale by 3 of "+vector1+" :");
        System.out.println(vector1.scale(3)+"\n");

        System.out.println("length of "+vector1+" :");
        System.out.println(df2.format(vector1.length())+"\n");

        System.out.println("dot product of "+vector1+" and "+vector2+" :");
        System.out.println(df2.format(vector1.dotProduct(vector2)));


    }
}