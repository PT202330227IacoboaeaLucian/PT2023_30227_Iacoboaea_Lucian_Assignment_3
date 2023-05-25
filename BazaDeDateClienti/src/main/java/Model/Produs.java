package Model;

public class Produs {
    private int id;
    private String numeProdus;
    private int cantitateProdus;
    private double pretProdus;
    public Produs(){

    }
    public Produs(int id,String numeProdus, int cantitateProdus,double pretProdus)
    {
        this.id=id;
        this.numeProdus=numeProdus;
        this.cantitateProdus=cantitateProdus;
        this.pretProdus=pretProdus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public int getCantitateProdus() {
        return cantitateProdus;
    }

    public void setCantitateProdus(int cantitateProdus) {
        this.cantitateProdus = cantitateProdus;
    }

    public double getPretProdus() {
        return pretProdus;
    }

    public void setPretProdus(double pretProdus) {
        this.pretProdus = pretProdus;
    }
}
