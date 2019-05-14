package renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.imageio.stream.*;

/**
 * imageWriter class
 * uses for design and create an image
 * contains 6 fields:
 * 1.2 _imageWidth, _imageHeight
 * 3.4._nX, _nY- number of pixels in X and Y axises
 * 5._image- the image for creating
 * 6._imageName- name for image
 */
public class ImageWriter {
    private double _imageWidth, _imageHeight;
    private int _nX, _nY;
    private BufferedImage _image;
    private String _imageName;

    final String PROJECT_PATH = System.getProperty("user.dir");


    // ***************** Constructors ********************** //

    /**
     * constructor
     *
     * @param imageName name of image
     * @param width     width of image
     * @param height    height of image
     * @param nX        number of pixels in x axis
     * @param nY        number of pixels in y axis
     */
    public ImageWriter(String imageName, double width, double height, int nX, int nY) {
        _imageName = imageName;
        _imageWidth = width;
        _imageHeight = height;
        _nX = nX;
        _nY = nY;
        _image = new BufferedImage(_nX, _nY, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * copy constructor
     *
     * @param imageWriter other imageWriter object
     */
    public ImageWriter(ImageWriter imageWriter) {
        this(imageWriter._imageName,
                imageWriter._imageWidth, imageWriter._imageHeight,
                imageWriter._nX, imageWriter._nY);
    }

    // ***************** Getters/Setters ********************** //

    public double getWidth() {
        return _imageWidth;
    }

    public double getHeight() {
        return _imageHeight;
    }

    public int getNy() {
        return _nY;
    }

    public int getNx() {
        return _nX;
    }

    public void setNy(int _Ny) {
        this._nY = _Ny;
    }

    public void setNx(int _Nx) {
        this._nX = _Nx;
    }

    // ***************** Operations ******************** //

    /**
     * function to create a file of image
     */
    public void writeToImage() {
        File ouFile = new File(PROJECT_PATH + "/" + _imageName + ".jpg");
        try {
            javax.imageio.ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
            ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
            jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            jpgWriteParam.setCompressionQuality(1f);
            jpgWriter.setOutput(new FileImageOutputStream(ouFile));
            jpgWriter.write(null, new IIOImage(_image, null, null), jpgWriteParam);
            //ImageIO.write(_image, "jpg", ouFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * function for painting a particular pixel in image
     *
     * @param xIndex index of pixel in x axis
     * @param yIndex index of pixel in y axis
     * @param color  color for paint the pixel
     */
    public void writePixel(int xIndex, int yIndex, Color color) {
        _image.setRGB(xIndex, yIndex, color.getRGB());
    }

}