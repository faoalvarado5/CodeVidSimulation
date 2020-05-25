public class agente {

    private int tipo;
    private String estado;
    private float velocidad_maxima;
    private float velocidad_minima;
    private int tiempo_enfermo;

    public agente(int tipo, String estado, float velocidad_maxima, float velocidad_minima, int tiempo_enfermo) {
        this.tipo = tipo;
        this.estado = estado;
        this.velocidad_maxima = velocidad_maxima;
        this.velocidad_minima = velocidad_minima;
        this.tiempo_enfermo = tiempo_enfermo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float isVelocidad_maxima() {
        return velocidad_maxima;
    }

    public void setVelocidad_maxima(float velocidad_maxima) {
        this.velocidad_maxima = velocidad_maxima;
    }

    public float isVelocidad_minima() {
        return velocidad_minima;
    }

    public void setVelocidad_minima(float velocidad_minima) {
        this.velocidad_minima = velocidad_minima;
    }

    public int getTiempo_enfermo() {
        return tiempo_enfermo;
    }

    public void setTiempo_enfermo(int tiempo_enfermo) {
        this.tiempo_enfermo = tiempo_enfermo;
    }

    @Override
    public String toString() {
        return "agente{" +
                "tipo=" + tipo +
                ", estado='" + estado + '\'' +
                ", velocidad_maxima=" + velocidad_maxima +
                ", velocidad_minima=" + velocidad_minima +
                ", tiempo_enfermo=" + tiempo_enfermo +
                '}';
    }
}
