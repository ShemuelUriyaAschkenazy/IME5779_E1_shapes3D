package geometries;
import primitives.Util;


public abstract class RadialGeometry{
    protected  double _radius;

    /********** Constructors ***********/
public  RadialGeometry(double num) throws Exception{
    if (num>=0 && Util.usubtract(num, 0.0)!=0)
        _radius = num;
    else
        throw new RadiusZeroException("radius can't be zero (or almost zero).");
}
public  RadialGeometry(RadialGeometry other) throws Exception
{
    if (Util.usubtract(other._radius, 0.0)!=0)
    _radius = other._radius;
    else
        throw new RadiusZeroException("radius can't be zero (or almost zero).");
}
    /************** Getters/Setters *******/
    public double get_radius() {
        return _radius;
    }
}

//exception thrown when trying to create radius that is zero or almost zero
class RadiusZeroException extends Exception {
    public RadiusZeroException(String str)
    {
        super(str);
    }
}