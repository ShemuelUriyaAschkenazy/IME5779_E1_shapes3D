package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;

public class Scene {
    private String _sceneName;
    private  Color _background;
    private AmbientLight _ambientLight;
    private Geometries _geometries;
    private Camera _camera;
    private double _distCameraScreen;

    public Scene(String _sceneName) {
        this._sceneName = _sceneName;
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

    public String get_sceneName() {
        return _sceneName;
    }

    public Color get_background() {
        return _background;
    }

    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }

    public Geometries get_geometries() {
        return _geometries;
    }

    public Camera get_camera() {
        return _camera;
    }

    public double get_distCameraScreen() {
        return _distCameraScreen;
    }

    public void addGeometry(Geometry... geometries){
        _geometries.add(geometries);
    }


}
