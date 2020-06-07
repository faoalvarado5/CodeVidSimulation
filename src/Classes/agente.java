package Classes;

import java.io.Serializable;
import java.util.ArrayList;

public class agente implements Serializable {

    private int tipo;
    private String estado;
    private double velocidad_x;
    private double velocidad_y;
    private int velocidad_maxima;
    private int velocidad_minima;
    private double posicion_en_eje_x;
    private double posicion_en_eje_y;
    private double tiempo_enfermo;
    private ArrayList<Double[]> posiciones_del_tipo_2 = new ArrayList<Double[]>();;
    private int posicion_del_tipo_2 = 0;
    private Boolean validador_para_mover_posicion_del_tipo_2 = true;

    public agente(int tipo, String estado, double velocidad_x, double velocidad_y, int velocidad_maxima, int velocidad_minima, double posicion_en_eje_x, double posicion_en_eje_y, double tiempo_enfermo) {
        this.tipo = tipo;
        this.estado = estado;
        this.velocidad_maxima = velocidad_maxima;
        this.velocidad_minima = velocidad_minima;
        this.velocidad_x = Math.random() * velocidad_maxima + velocidad_minima;
        this.velocidad_y = Math.random() * velocidad_maxima + velocidad_minima;
        this.posicion_en_eje_x = posicion_en_eje_x;
        this.posicion_en_eje_y = posicion_en_eje_y;
        this.tiempo_enfermo = tiempo_enfermo;
        this.posicion_del_tipo_2 = 0;
    }

    public agente() {
    }

    public void mover_aleatoreamente(){

        Double random_x = Math.random()*2;
        Double random_y = Math.random()*2;

        if(random_x > 0.5) this.velocidad_x = -this.velocidad_x;
        if(random_y > 0.5) this.velocidad_y = -this.velocidad_y;
    }

    public void agregar_puntos_al_agente_de_tipo_2(){
        if(posiciones_del_tipo_2.size() < 7){
            Double[] puntos = new Double[2];
            puntos[0] = posicion_en_eje_x;
            puntos[1] = posicion_en_eje_y;
            posiciones_del_tipo_2.add(puntos);
        }
    }

    public void aumentar_posicion_del_tipo_2(){

        if(this.posicion_del_tipo_2 == 0) validador_para_mover_posicion_del_tipo_2 = true;
        else if(this.posicion_del_tipo_2 == 6) validador_para_mover_posicion_del_tipo_2 = false;

        if(validador_para_mover_posicion_del_tipo_2) this.posicion_del_tipo_2++;
        else this.posicion_del_tipo_2--;


    }


    public void aumentar_dias_de_enfermos(){
        this.tiempo_enfermo = tiempo_enfermo + 0.10;
    }

    public void invertir_posicion_x() { this.velocidad_x = -this.velocidad_x; }

    public void invertir_posicion_y() { this.velocidad_y = -this.velocidad_y; }

    public void mover_eje_x(){
        this.posicion_en_eje_x += velocidad_x;
    }
    public void mover_eje_y(){
        this.posicion_en_eje_y += velocidad_y;
    }

    public void mover_para_atras_eje_x(){
        this.posicion_en_eje_x = this.posicion_en_eje_x - (velocidad_x*2);
    }
    public void mover_para_atras_eje_y(){
        this.posicion_en_eje_y = this.posicion_en_eje_y - (velocidad_y*2);
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setVelocidad_x(double velocidad_x) {
        this.velocidad_x = velocidad_x;
    }

    public void setVelocidad_y(double velocidad_y) {
        this.velocidad_y = velocidad_y;
    }

    public void setVelocidad_maxima(int velocidad_maxima) {
        this.velocidad_maxima = velocidad_maxima;
    }

    public void setVelocidad_minima(int velocidad_minima) {
        this.velocidad_minima = velocidad_minima;
    }

    public void setPosicion_en_eje_x(double posicion_en_eje_x) {
        this.posicion_en_eje_x = posicion_en_eje_x;
    }

    public void setPosicion_en_eje_y(double posicion_en_eje_y) {
        this.posicion_en_eje_y = posicion_en_eje_y;
    }

    public void setTiempo_enfermo(double tiempo_enfermo) {
        this.tiempo_enfermo = tiempo_enfermo;
    }

    public int getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public double getVelocidad_x() {
        return velocidad_x;
    }

    public double getVelocidad_y() {
        return velocidad_y;
    }

    public int getVelocidad_maxima() {
        return velocidad_maxima;
    }

    public int getVelocidad_minima() {
        return velocidad_minima;
    }

    public double getPosicion_en_eje_x() {
        return posicion_en_eje_x;
    }

    public double getPosicion_en_eje_y() {
        return posicion_en_eje_y;
    }

    public double getTiempo_enfermo() {
        return tiempo_enfermo;
    }

    public ArrayList<Double[]> getPosiciones_del_tipo_2() {
        return posiciones_del_tipo_2;
    }

    public void setPosiciones_del_tipo_2(ArrayList<Double[]> posiciones_del_tipo_2) {
        this.posiciones_del_tipo_2 = posiciones_del_tipo_2;
    }

    public int getPosicion_del_tipo_2() {
        return posicion_del_tipo_2;
    }

    public void setPosicion_del_tipo_2(int posicion_del_tipo_2) {
        this.posicion_del_tipo_2 = posicion_del_tipo_2;
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
