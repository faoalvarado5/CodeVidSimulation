public class agente {

    private int tipo;
    private String estado;
    private double velocidad_x;
    private double velocidad_y;
    private int velocidad_maxima;
    private int velocidad_minima;
    private double posicion_en_eje_x;
    private double posicion_en_eje_y;
    private int tiempo_enfermo;


    public agente(int tipo, String estado, double velocidad_x, double velocidad_y, int velocidad_maxima, int velocidad_minima, double posicion_en_eje_x, double posicion_en_eje_y, int tiempo_enfermo) {
        this.tipo = tipo;
        this.estado = estado;
        this.velocidad_x = velocidad_x;
        this.velocidad_y = velocidad_y;
        this.velocidad_maxima = velocidad_maxima;
        this.velocidad_minima = velocidad_minima;
        this.posicion_en_eje_x = posicion_en_eje_x;
        this.posicion_en_eje_y = posicion_en_eje_y;
        this.tiempo_enfermo = tiempo_enfermo;
    }

    public agente() {
    }

    public void invertir_posicion_x(){
        this.posicion_en_eje_x = -this.posicion_en_eje_x;
    }

    public void invertir_posicion_y(){
        this.posicion_en_eje_y = -this.posicion_en_eje_y;
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

    public double getVelocidad_x() {
        return velocidad_x;
    }

    public void setVelocidad_x(double velocidad_x) {
        this.velocidad_x = velocidad_x;
    }

    public double getVelocidad_y() {
        return velocidad_y;
    }

    public void setVelocidad_y(double velocidad_y) {
        this.velocidad_y = velocidad_y;
    }

    public int getVelocidad_maxima() {
        return velocidad_maxima;
    }

    public void setVelocidad_maxima(int velocidad_maxima) {
        this.velocidad_maxima = velocidad_maxima;
    }

    public int getVelocidad_minima() {
        return velocidad_minima;
    }

    public void setVelocidad_minima(int velocidad_minima) {
        this.velocidad_minima = velocidad_minima;
    }

    public double getPosicion_en_eje_x() {
        return posicion_en_eje_x;
    }

    public void setPosicion_en_eje_x(double posicion_en_eje_x) {
        this.posicion_en_eje_x = posicion_en_eje_x;
    }

    public double getPosicion_en_eje_y() {
        return posicion_en_eje_y;
    }

    public void setPosicion_en_eje_y(double posicion_en_eje_y) {
        this.posicion_en_eje_y = posicion_en_eje_y;
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
                ", velocidad_x=" + velocidad_x +
                ", velocidad_y=" + velocidad_y +
                ", velocidad_maxima=" + velocidad_maxima +
                ", velocidad_minima=" + velocidad_minima +
                ", posicion_en_eje_x=" + posicion_en_eje_x +
                ", posicion_en_eje_y=" + posicion_en_eje_y +
                ", tiempo_enfermo=" + tiempo_enfermo +
                '}';
    }
}
