public class agente {

    private int tipo;
    private String estado;
    private double velocidad_x;
    private double velocidad_y;
    private double posicion_en_eje_x;
    private double posicion_en_eje_y;
    private int tiempo_enfermo;


    public agente(int tipo, String estado, double velocidad_x, double velocidad_y, int tiempo_enfermo,
                  double posicion_en_eje_x, double posicion_en_eje_y) {
        this.tipo = tipo;
        this.estado = estado;
        this.velocidad_x = velocidad_x;
        this.velocidad_y = velocidad_y;
        this.tiempo_enfermo = tiempo_enfermo;
        this.posicion_en_eje_x = posicion_en_eje_x;
        this.posicion_en_eje_y = posicion_en_eje_y;
    }

    public void invertir_posicion_x(){
        this.posicion_en_eje_x = -this.posicion_en_eje_x;
    }

    public void invertir_posicion_y(){
        this.posicion_en_eje_y = -this.posicion_en_eje_y;
    }

    public void setPosicion_en_eje_x(double posicion){
        this.posicion_en_eje_x += posicion;
    }

    public void setPosicion_en_eje_y(double posicion){
        this.posicion_en_eje_y += posicion;
    }

    public double getVelocidad_x(){ return this.velocidad_x; }

    public double getVelocidad_y(){ return this.velocidad_y; }

    public double getPosicion_en_eje_x(){ return this.posicion_en_eje_x; }

    public double getPosicion_en_eje_y(){ return this.posicion_en_eje_y; }

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

    @Override
    public String toString() {
        return "agente{" +
                "tipo=" + tipo +
                ", estado='" + estado + '\'' +
                ", velocidad_x=" + velocidad_x +
                ", velocidad_y=" + velocidad_y +
                ", tiempo_enfermo=" + tiempo_enfermo +
                '}';
    }
}
