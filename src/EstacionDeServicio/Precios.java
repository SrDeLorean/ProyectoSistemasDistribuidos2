package EstacionDeServicio;

import java.io.Serializable;

/**
 *
 * @author SrDeLorean
 */
public class Precios implements Serializable{
    
    private double kerosene;
    private double disel;
    private double b93;
    private double b95;
    private double b97;

    public Precios(double kerosene, double disel, double b93, double b95, double b97) {
        this.kerosene = kerosene;
        this.disel = disel;
        this.b93 = b93;
        this.b95 = b95;
        this.b97 = b97;
    }

    public double getKerosene() {
        return kerosene;
    }

    public double getDisel() {
        return disel;
    }

    public double getB93() {
        return b93;
    }

    public double getB95() {
        return b95;
    }

    public double getB97() {
        return b97;
    }
    
    
}
