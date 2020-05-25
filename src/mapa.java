public class mapa {

    private int largo;
    private int ancho;

    public mapa(int largo, int ancho) {
        this.largo = largo;
        this.ancho = ancho;
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

    @Override
    public String toString() {
        return "mapa{" +
                "largo=" + largo +
                ", ancho=" + ancho +
                '}';
    }
}
