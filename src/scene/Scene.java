package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;

import javax.swing.plaf.synth.Region;

/**
 * scene class
 * <p>
 * contain 6 fields:
 * _sceneName- name of scene
 * _background- color to paint the background of scene
 * _ambientLight- light/color that lights the environment
 * _geometries- objects/shapes that exist in scene
 * _camera- point of view, that from there we look on the scene, through an imagine view plane
 * _distCameraScreen- distance from camere to the imagine view plane
 */
public class Scene {
    private String _sceneName;
    private Color _background;
    private AmbientLight _ambientLight;
    private Geometries _geometries;
    private Camera _camera;
    private double _distCameraScreen;

    /**
     * constructor
     *
     * @param _sceneName a name for scene
     *                   <p>
     *                   constructor initialize only name and geometries (to new empty list)
     */
    public Scene(String _sceneName) {
        this._sceneName = _sceneName;
        _geometries = new Geometries();
    }


    //region ***********************getters/setters***************
    public String getSceneName() {
        return _sceneName;
    }

    public Color getBackground() {
        return _background;
    }

    public AmbientLight getAmbientLight() {
        return _ambientLight;
    }

    public Geometries getGeometries() {
        return _geometries;
    }

    public Camera getCamera() {
        return _camera;
    }

    public double getDistCameraScreen() {
        return _distCameraScreen;
    }

    public void setBackground(Color _background) {
        this._background = _background;
    }

    public void setAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public void setCamera(Camera _camera) {
        this._camera = _camera;
    }

    public void setDistCameraScreen(double _distCameraScreen) {
        this._distCameraScreen = _distCameraScreen;
    }
    //endregion

    /**
     * function for adding geometries to scene
     *
     * @param geometries one or more geometries, such sphere or triangle
     */
    public void addGeometry(Intersectable... geometries) {
        _geometries.add(geometries);
    }


}
