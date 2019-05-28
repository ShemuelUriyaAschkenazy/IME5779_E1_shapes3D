package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {

    Vector direction;

    public SpotLight(Color _color, Point3D _position, double _kC, double _kL, double _kQ, Vector direction) {
        super(_color, _position, _kC, _kL, _kQ);
        this.direction = direction;
    }


    @Override
    public Color getIntensity(Point3D point3D) {
        Vector PositionToP0 = point3D.subtract(_position).normalize(    );
        //dot product is indicator for the angle between spot light direction and the vector from spot light to the point on geometry.
        double angle = PositionToP0.dotProduct(direction);
        return super.getIntensity(point3D).scale(Math.max(0,angle));
    }
}
