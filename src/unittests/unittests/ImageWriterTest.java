package unittests;

import org.junit.Test;
import primitives.Color;
import renderer.ImageWriter;

import static org.junit.Assert.*;

public class ImageWriterTest {

    @Test
    public void writeToImage() {

        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);

        ImageWriter img = new ImageWriter("img1",500,500,500,500);
        for(int i=0; i<500;i++) {
            for(int j=0; j<500;j++) {
                if(i%50==0 || j%50==0)
                    img.writePixel(i,j,white.getColor());
                else
                    img.writePixel(i,j,black.getColor());

            }
        }
        img.writeToImage();
    }

    @Test
    public void writePixel() {
    }
}