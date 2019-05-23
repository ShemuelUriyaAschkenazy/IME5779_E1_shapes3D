
package unittests;

import elements.AmbientLight;
import org.junit.Test;
import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RenderTest {
    @Test
    public void basicRendering(){
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistCameraScreen(100);
        scene.setAmbientLight(new AmbientLight(new Color(200,20,20),0.8));
        scene.setBackground(new Color(0, 0, 0));
        Geometries geometries = new Geometries();
        scene.addGeometry(geometries);
        geometries.add(new Sphere( 50, new Point3D(0, 0, 150),new Color(20,46,1)));

        geometries.add(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D( 100, 100, 149),new Color(111,46,1)));

        geometries.add(new Triangle(new Point3D( 100, 0, 149),
                new Point3D(  0, -100, 149),
                new Point3D( 100,-100, 149),new Color(44,46,1)));

        geometries.add(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0, 100, 149),
                new Point3D(-100, 100, 149),new Color(20,77,1)));

        geometries.add(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(  0,  -100, 149),
                new Point3D(-100, -100, 149),new Color(20,98,1)));

        ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage(500,500);
        render.printGrid(50);
        imageWriter.writeToImage();
    }
}
