package elements;

import primitives.Color;

public class AmbientLight {
    private Color Ia;
    private double Ka;
    private Color getIntensity;

    public AmbientLight(Color ia, double ka) {
        Ia = ia;
        Ka = ka;
        getIntensity=Ia.scale(Ka);
    }

    public Color getIa() {
        return Ia;
    }

    public double getKa() {
        return Ka;
    }

    public Color getIntensity(){
        return getIntensity;
    }



}
