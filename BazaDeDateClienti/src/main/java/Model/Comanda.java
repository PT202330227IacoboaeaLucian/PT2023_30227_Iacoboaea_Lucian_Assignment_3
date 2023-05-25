package Model;

public class Comanda {
    private int id;
    private String clientNume;
    private String produsNume;
    private int cantitateComanda;


    public Comanda()
    {

    }
    public Comanda(int id,String clientNume,String produsNume,int cantitateComanda)
    {
        this.id=id;
        this.clientNume=clientNume;
        this.produsNume=produsNume;
        this.cantitateComanda=cantitateComanda;
    }

    public int getIdComanda() {
        return id;
    }

    public void setIdComanda(int id) {
        this.id = id;
    }

    public String getClientNume() {
        return clientNume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantitateComanda() {
        return cantitateComanda;
    }

    public void setCantitateComanda(int cantitateComanda) {
        this.cantitateComanda = cantitateComanda;
    }

    public void setClientNume(String clientNume) {
        this.clientNume = clientNume;
    }

    public String getProdusNume() {
        return produsNume;
    }

    public void setProdusNume(String produsNume) {
        this.produsNume = produsNume;
    }

    public int getCantitateComanda(int cantitateComanda) {
        return this.cantitateComanda;
    }

    public void setCanitateComanda(int canitateComanda) {
        this.cantitateComanda = canitateComanda;
    }
}
