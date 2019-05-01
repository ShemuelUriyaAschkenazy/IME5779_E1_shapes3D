package primitives;
import java.text.DecimalFormat;
import static primitives.Util.*;

/**
 * Coordinate class.
 * contain one field: double coord
 */
public final class Coordinate {
    //private static final double EPSILON = 0.0000001;
    private double _coord;
    //const zero coordinate
    public final static Coordinate ZERO = new Coordinate(0.0);

    /**
     * consructor
     * @param coord- coordinate value
     * */
    /* ********* Constructors ***********/
    public Coordinate(double coord) {
        // if it too close to zero make it zero
        _coord = alignZero(coord);
    }

    /**
     * copy constructor
     * @param other- other coordinate
     */
    public Coordinate(Coordinate other) {
        _coord = other._coord;
    }

    /* ************* Getters/Setters *******/
    /**
     * @return the coordinate value
     */
    public double getCoordinate() {
        return _coord;
    }


    /* ************** Admin *****************/
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

    /* ************* Operations***************/
    /** subtract one coordinate from another
     * @param other- another coordinate
     * @return coordinate recieved from subtraction
     **/
    public Coordinate subtract(Coordinate other) {
        return new Coordinate(usubtract(_coord, other._coord));
    }

    /**
     * add one coordinate to another
     * @param other- another coordinate
     * @return coordinate recieved from adding
     */
    public Coordinate add(Coordinate other) {
        return new Coordinate(uadd(_coord, other._coord));
    }

    /**
     * scaling the coordinate by a number
     * @param num
     * @return coordinate recieved from scaling
     */
    public Coordinate scale(double num) {
        return new Coordinate(uscale(_coord, num));
    }

    /**
     * multiplies the two coordinates
     * @param other- other coordinate
     * @return result of multiplying (double number)
     */
    public double multiply(Coordinate other) {
        return _coord*other._coord;
    }

}


