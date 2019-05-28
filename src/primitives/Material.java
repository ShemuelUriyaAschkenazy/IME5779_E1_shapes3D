package primitives;

public class Material {
    private double _kD;
    private double _kS;
    private int _nShininess;

    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }

    public double getKD() {
        return _kD;
    }

    public double getKS() {
        return _kS;
    }

    public int getNShininess() {
        return _nShininess;
    }
}
