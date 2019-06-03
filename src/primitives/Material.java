package primitives;

public class Material {
    private double _kD;
    private double _kS;
    private double _kR;

    private double _kT;
    private int _nShininess;

    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }

    public Material(double _kD, double _kS, double _kR, double _kT, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._kR = _kR;
        this._kT = _kT;
        this._nShininess = _nShininess;
    }
    public double getKD() {
        return _kD;
    }

    public double getKS() {
        return _kS;
    }

    public double getKR() { return _kR; }

    public double getKT() { return _kT; }

    public int getNShininess() {
        return _nShininess;
    }
}
