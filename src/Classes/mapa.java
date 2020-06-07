package Classes;

import java.util.ArrayList;

public class mapa {

    private int largo;
    private int ancho;
    private ArrayList<pared> paredes;

    public mapa() {
        this.paredes = new ArrayList<pared>();
    }

    public mapa(int largo, int ancho, ArrayList<pared> paredes) {
        this.largo = largo;
        this.ancho = ancho;
        this.paredes = paredes;
    }


    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public ArrayList<pared> getParedes() {
        return paredes;
    }

    public void setParedes(ArrayList<pared> paredes) {
        this.paredes = paredes;
    }

    public void addPared(pared pared) {

        this.paredes.add(pared);

    }

    @Override
    public String toString() {
        return "mapa{" +
                "largo=" + largo +
                ", ancho=" + ancho +
                ", paredes=" + paredes +
                '}';
    }
}
