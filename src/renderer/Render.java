package renderer;

import scene.Scene;

public class Render {
    private ImageWriter imageWriter;
    private Scene scene;

    public Render(ImageWriter imageWriter, Scene scene) {
        this.imageWriter = imageWriter;
        this.scene = scene;
    }
    
}
