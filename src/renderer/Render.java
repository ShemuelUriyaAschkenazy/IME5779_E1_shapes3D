package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.awt.*;
import java.util.List;

public class Render {
    private ImageWriter _imageWriter;
    private Scene _scene;

    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    public void renderImage (int i, int j){
        Ray ray;
        for(int m=0; m<i;i++)
            for(int n = 0; n<j; n++)
            {
                ray = _scene.getCamera().constructRayThroughPixel
                        (_imageWriter.getNx(),_imageWriter.getNy(),m,n,_scene.getDistCameraScreen(),_imageWriter.getWidth(),_imageWriter.getHeight());
                List<Point3D>intersectionPoint  = _scene.getGeometries().findIntersections(ray);
                if(intersectionPoint.isEmpty())
                    _imageWriter.writePixel(m,n,_scene.getBackground().getColor());
                Point3D closestPoint = getClosestPoint(intersectionPoint);
                _imageWriter.writePixel(m,n,calcColor(closestPoint).getColor());

            }

    }
    private Color calcColor(Point3D point3D){
        return _scene.getAmbientLight().getIntensity();
    }
    private Point3D getClosestPoint(List<Point3D> intersectionPoint){
        Point3D closestPoint = intersectionPoint.get(0);
        for(int i = 1; i<intersectionPoint.size();i++)
        {
            if (intersectionPoint.get(i).distanceInSquare(_scene.getCamera().getP0()) <
                    closestPoint.distanceInSquare(_scene.getCamera().getP0()))
                closestPoint = intersectionPoint.get(i);
        }
        return closestPoint;
    }



}
