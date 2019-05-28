
package unittests;

import elements.AmbientLight;
import elements.DirectionalLight;
import elements.SpotLight;
import org.junit.Test;
import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RenderTest {
    @Test
    public void basicRendering() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistCameraScreen(100);
        scene.setAmbientLight(new AmbientLight(new Color(200, 20, 20), 0.8));
        scene.setBackground(new Color(0, 0, 0));
        Geometries geometries = new Geometries();
        scene.addGeometry(geometries);
        geometries.add(new Sphere(50, new Point3D(0, 0, 150), new Color(20, 46, 1)));

        geometries.add(new Triangle(new Point3D(100, 0, 149),
                new Point3D(0, 100, 149),
                new Point3D(100, 100, 149), new Color(111, 46, 1)));

        geometries.add(new Triangle(new Point3D(100, 0, 149),
                new Point3D(0, -100, 149),
                new Point3D(100, -100, 149), new Color(44, 46, 1)));

        geometries.add(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(0, 100, 149),
                new Point3D(-100, 100, 149), new Color(20, 77, 1)));

        geometries.add(new Triangle(new Point3D(-100, 0, 149),
                new Point3D(0, -100, 149),
                new Point3D(-100, -100, 149), new Color(20, 98, 1)));

        ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage(500, 500);
        render.printGrid(50);
        imageWriter.writeToImage();
    }

    @Test
    public void complexRendering() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(250, 250, -300), new Vector(0, -1, 0), new Vector(1, 0, 0)));
        scene.setDistCameraScreen(20);
        scene.setAmbientLight(new AmbientLight(new Color(200, 20, 20), 0.8));
        scene.setBackground(new Color(0, 0, 0));
        Geometries geometries = new Geometries();
        scene.addGeometry(geometries);

        geometries.add(new Triangle(new Point3D(100, 100, 300),
                new Point3D(400, 100, 0),
                new Point3D(100, 400, 0), new Color(111, 46, 1), new Material(0.1, 0.2, 2)));

        geometries.add(new Triangle(new Point3D(400, 400, 300),
                new Point3D(400, 100, 0),
                new Point3D(100, 400, 0), new Color(111, 200, 1), new Material(0.1, 0.2, 2)));

        geometries.add(new Triangle(new Point3D(100, 0, 149),
                new Point3D(0, 100, 149),
                new Point3D(100, 100, 149), new Color(111, 46, 1), new Material(0.1, 0.2, 2)));

        geometries.add(new Triangle(new Point3D(100, 0, 149),
                new Point3D(0, -100, 149),
                new Point3D(100, -100, 149), new Color(44, 46, 1), new Material(0.1, 0.2, 2)));

        scene.addLightSource(new SpotLight(new Color(200, 200, 200), new Point3D(250, 250, -300), 0.1, 0.2, 0.3, new Vector(0, -0.5, 10)));

        ImageWriter imageWriter = new ImageWriter("test1", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage(500, 500);
        imageWriter.writeToImage();
    }

    @Test
    public void complexRendering2() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistCameraScreen(100);
        scene.setAmbientLight(new AmbientLight(new Color(30, 30, 30), 0.8));
        scene.setBackground(new Color(0, 0, 0));
        Geometries geometries = new Geometries();
        scene.addGeometry(geometries);

        geometries.add(new Triangle(new Point3D(110, 100, 149),
                new Point3D(-90, -100, 200),
                new Point3D(110, -100, 200), new Color(45, 52, 52), new Material(0.1, 0.2, 2)));
        geometries.add(new Triangle(new Point3D(-100, -100, 200),
                new Point3D(100, 100, 149),
                new Point3D(-100, 100, 149), new Color(45, 52, 52), new Material(0.1, 0.2, 2)));

        scene.addLightSource(new SpotLight(new Color(238, 30, 30), new Point3D(0, 0, 0), 0.01, 0.0001, 0.00001, new Vector(0, 0, 1)));
        scene.addLightSource(new DirectionalLight(new Color(2, 289, 43), new Vector(0, 0, 1)));

        ImageWriter imageWriter = new ImageWriter("test2", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage(500, 500);
        imageWriter.writeToImage();
    }

    @Test
    public void sphereTest() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistCameraScreen(100);
        scene.setAmbientLight(new AmbientLight(new Color(30, 30, 30), 0.8));
        scene.setBackground(new Color(0, 0, 0));
        Geometries geometries = new Geometries();
        scene.addGeometry(geometries);

        geometries.add(new Sphere(100, new Point3D(0, 0, 150), new Color(20, 46, 1),new Material(0.001,0.003,2)));

        scene.addLightSource(new SpotLight(new Color(238, 30, 30), new Point3D(0, 0, 30), 0.01, 0.0001, 0.00001, new Vector(0, 0, 1)));


        ImageWriter imageWriter = new ImageWriter("test3", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage(500, 500);
        imageWriter.writeToImage();
    }
}
