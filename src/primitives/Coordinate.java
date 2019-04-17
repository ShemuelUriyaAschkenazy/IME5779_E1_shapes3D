package primitives;
import java.text.DecimalFormat;
import static primitives.Util.*;

public final class Coordinate {
    //private static final double EPSILON = 0.0000001;
    private double _coord;

    public static Coordinate ZERO = new Coordinate(0.0);

    /********** Constructors ***********/
    public Coordinate(double coord) {
        // if it too close to zero make it zero
        _coord = alignZero(coord);
    }


    public Coordinate(Coordinate other) {
        _coord = other._coord;
    }

    /************** Getters/Setters *******/
    public double get() {
        return _coord;
    }

    /*************** Admin *****************/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;
        return usubtract(_coord, ((Coordinate)obj)._coord) == 0.0;
    }

    //uses for presenting formatting double in the toStrong function
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    @Override

    public String toString() {
        return "" + df2.format(_coord);
    }

    /************** Operations***************/


    /** * @param other- another coordinate
     * @return the result of substract one coordinate from another
     **/
    public Coordinate subtract(Coordinate other) {
        return new Coordinate(usubtract(_coord, other._coord));
    }

    /**
     * @param other- another coordinate
     * @return the result of adding the two coordinates
     */
    public Coordinate add(Coordinate other) {
        return new Coordinate(uadd(_coord, other._coord));
    }

    /**
     * @param num
     * @return result of scaling the coordinate by a number
     */
    public Coordinate scale(double num) {
        return new Coordinate(uscale(_coord, num));
    }

    /**
     * @param other- other coordinate
     * @return number (double) from multiply the two coordinates. (uses for vector multiply).
     */
    public double multiply(Coordinate other) {
        return _coord*other._coord;
    }

}


