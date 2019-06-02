
package unittests;

import elements.*;
import org.junit.Test;
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
    public void trianglesTest() {
        Scene scene = new Scene("triangles1");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistCameraScreen(100);
        scene.setAmbientLight(new AmbientLight(new Color(30, 30, 130), 0.8));
        scene.setBackground(new Color(0, 0, 0));
        Geometries geometries = new Geometries();
        scene.addGeometry(geometries);
        geometries.add(new Triangle(new Point3D(410, 400, 149),
                new Point3D(-400, -400, 300),
                new Point3D(410, -400, 300), new Color(45, 52, 52), new Material(0.1, 0.2, 2)));
        geometries.add(new Triangle(new Point3D(-410, -400, 300),
                new Point3D(400, 400, 149),
                new Point3D(-410, 400, 149), new Color(45, 52, 52), new Material(0.1, 0.2, 2)));
        scene.addLightSource(new SpotLight(new Color(238, 30, 30), new Point3D(100, 100, 0), 0.01, 0.0001, 0.000001, new Vector(0, -0.5, 1)));
        ImageWriter imageWriter = new ImageWriter("triangles- spot light", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage(500, 500);
        imageWriter.writeToImage();

        scene = new Scene("triangles2");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistCameraScreen(100);
        scene.setAmbientLight(new AmbientLight(new Color(30, 30, 130), 0.8));
        scene.setBackground(new Color(0, 0, 0));
        geometries = new Geometries();
        scene.addGeometry(geometries);
        geometries.add(new Triangle(new Point3D(410, 400, 149),
                new Point3D(-400, -400, 300),
                new Point3D(410, -400, 300), new Color(45, 52, 52), new Material(0.1, 0.2, 2)));
        geometries.add(new Triangle(new Point3D(-410, -400, 300),
                new Point3D(400, 400, 149),
                new Point3D(-410, 400, 149), new Color(45, 52, 52), new Material(0.1, 0.2, 2)));
        scene.addLightSource(new PointLight(new Color(238, 30, 30), new Point3D(100, 100, 0), 0.01, 0.0001, 0.000001));
        imageWriter = new ImageWriter("triangles- point light", 500, 500, 500, 500);
        render = new Render(imageWriter, scene);
        render.renderImage(500, 500);
        imageWriter.writeToImage();
    }

    @Test
    public void sphereTest() {
        Scene scene = new Scene("sphere1");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistCameraScreen(100);
        scene.setAmbientLight(new AmbientLight(new Color(223, 218, 250), 0.01));
        scene.setBackground(new Color(0, 0, 0));
        Geometries geometries = new Geometries();
        scene.addGeometry(geometries);
        geometries.add(new Sphere(100, new Point3D(0, 0, 150), new Color(63, 32, 218), new Material(0.1, 0.1, 2)));
        scene.addLightSource(new SpotLight(new Color(28, 2, 2), new Point3D(-50, -50, -50), 0.001, 0.0001, 0.0000007, new Vector(1, 1, 1)));
        //scene.addLightSource(new DirectionalLight(new Color(27, 100, 2), new Vector(1, 1, 1)));
        //scene.addLightSource(new PointLight(new Color(28, 2, 2), new Point3D(0, 0, -100), 0.001, 0.0001, 0.000007));

        ImageWriter imageWriter = new ImageWriter("sphere- spot light", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage(500, 500);
        imageWriter.writeToImage();

        scene = new Scene("sphere2");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistCameraScreen(100);
        scene.setAmbientLight(new AmbientLight(new Color(223, 218, 250), 0.01));
        scene.setBackground(new Color(0, 0, 0));
        geometries = new Geometries();
        scene.addGeometry(geometries);
        geometries.add(new Sphere(100, new Point3D(0, 0, 150), new Color(63, 32, 218), new Material(0.1, 0.1, 2)));
        //scene.addLightSource(new DirectionalLight(new Color(27, 100, 2), new Vector(1, 1, 1)));
        scene.addLightSource(new PointLight(new Color(28, 2, 2), new Point3D(-50, -50, -50), 0.001, 0.0001, 0.0000007));

        imageWriter = new ImageWriter("sphere- point light", 500, 500, 500, 500);
        render = new Render(imageWriter, scene);
        render.renderImage(500, 500);
        imageWriter.writeToImage();
    }

    @Test
    public void TriangleShadesOnSphere () {
        Scene scene = new Scene("sphereTriangle");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
        scene.setDistCameraScreen(100);
        scene.setAmbientLight(new AmbientLight(new Color(223, 218, 250), 0.01));
        scene.setBackground(new Color(0, 0, 0));
        Geometries geometries = new Geometries();
        scene.addGeometry(geometries);
        geometries.add(new Sphere(100, new Point3D(0, 0, 150), new Color(63, 32, 218), new Material(0.1, 0.1, 2)));
        geometries.add(new Triangle(new Point3D(17, 15, 20),
              new Point3D(-15, -15, 20),
             new Point3D(17, -15, 20), new Color(200, 52, 52), new Material(0.1, 0.2, 2)));
        scene.addLightSource(new SpotLight(new Color(28, 2, 2), new Point3D(-50, -50, -50), 0.001, 0.0001, 0.0000007, new Vector(1, 1, 1)));
        scene.addLightSource(new DirectionalLight(new Color(27, 100, 2), new Vector(1, 1, 1)));
        //scene.addLightSource(new PointLight(new Color(28, 2, 2), new Point3D(0, 0, -100), 0.001, 0.0001, 0.000007));

        ImageWriter imageWriter = new ImageWriter("spere- spot light34354", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage(500, 500);
        imageWriter.writeToImage();
    }


}
