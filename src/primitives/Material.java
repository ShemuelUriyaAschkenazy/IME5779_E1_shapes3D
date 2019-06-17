package primitives;


/**
 * material class
 * uses for save material parameters of object, that influence on the light from it.
 * 6 fields:
 * _kD- factor for diffusive light
 * _kS- factor for specular light
 * _kR- factor for reflection light
 * _kT- factor for transparent of object
 *  _nShininess- level of shininess (for calculate the specular light)
 */
public class Material {
    private double _kD;
    private double _kS;
    private double _kR;
    private double _kT;
    private double _kDG;  //k factor for diffuse glass
    private double _kGS;  //k factor for glossy surfaces
    private int _nShininess;

    /**
     * constructor
     * @param _kD factor for diffusive light
     * @param _kS factor for specular light
     * @param _nShininess level of shininess (for calculate the specular light)
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }

    /**
     * @param _kD factor for diffusive light
     * @param _kS factor for specular light
     * @param _kR factor for reflection light
     * @param _kT factor for transparent of object
     * @param _nShininess level of shininess (for calculate the specular light)
     */
    public Material(double _kD, double _kS, double _kR, double _kT, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._kR = _kR;
        this._kT = _kT;
        this._nShininess = _nShininess;
    }

    /**
     * @param _kD factor for diffusive light
     * @param _kS factor for specular light
     * @param _kR factor for reflection light
     * @param _kT factor for transparent of object
     * @param _kDG factor for diffuse glass
     * @param _kGS factor for glossy surface
     * @param _nShininess level of shininess (for calculate the specular light)
     */
    public Material(double _kD, double _kS, double _kR, double _kT, double _kDG, double _kGS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._kR = _kR;
        this._kT = _kT;
        this._kDG = _kDG;
        this._kGS = _kGS;
        this._nShininess = _nShininess;
    }


    //**********getters/setters***************

    /**
     * getKD function for get the factor of diffusive
     * @return kd factor (double)
     */
    public double getKD() {
        return _kD;
    }

    /**
     * getKS function for get the factor of specular light
     * @return kS factor (double)
     */
    public double getKS() {
        return _kS;
    }

    /**
     * getKR function for get the factor of reflection light
     * @return kR factor (double)
     */
    public double getKR() { return _kR; }

    /**
     * getKS function for get the transparent of object (between 0-1)
     * @return kT factor (double)
     */
    public double getKT() { return _kT; }

    /**
     * getNShininess function for get the level of shininess (for calculate the specular light)
     * @return shininess level (int)
     */
    public int getNShininess() {
        return _nShininess;
    }


    /**
     * getKDG function for get the factor of diffuse glass
     @return kDG factor (double)
     */
    public double getKDG() {
        return _kDG;
    }

    /**
     * getKGS function for get the factor of glass surface
     @return kgs factor (double)
     */
    public double getKGS() {
        return _kGS;
    }
}
