package Classes;

import java.util.ArrayList;

public class DatosActuales {

    ArrayList<Integer>cantidad_de_enfermos;
    ArrayList<Integer>cantidad_de_sanos;
    ArrayList<Integer>cantidad_de_curados;
    int dias;

    public DatosActuales(){
        cantidad_de_enfermos = new ArrayList<>();
        cantidad_de_sanos = new ArrayList<>();
        cantidad_de_curados = new ArrayList<>();
        dias = 1;
    }

    public void aumentar_dias_corriendo(){ this.dias++; }

    public void agregar_datos_de_enfermos(int cantidad_de_enfermos){
        this.cantidad_de_enfermos.add(cantidad_de_enfermos);
    }

    public void agregar_datos_de_sanos(int cantidad_de_sanos){
        this.cantidad_de_sanos.add(cantidad_de_sanos);
    }

    public void agregar_datos_de_curados(int cantidad_de_curados){
        this.cantidad_de_curados.add(cantidad_de_curados);
    }

    public ArrayList<Integer> getCantidad_de_enfermos() {
        return cantidad_de_enfermos;
    }

    public void setCantidad_de_enfermos(ArrayList<Integer> cantidad_de_enfermos) {
        this.cantidad_de_enfermos = cantidad_de_enfermos;
    }

    public ArrayList<Integer> getCantidad_de_sanos() {
        return cantidad_de_sanos;
    }

    public void setCantidad_de_sanos(ArrayList<Integer> cantidad_de_sanos) {
        this.cantidad_de_sanos = cantidad_de_sanos;
    }

    public ArrayList<Integer> getCantidad_de_curados() {
        return cantidad_de_curados;
    }

    public void setCantidad_de_curados(ArrayList<Integer> cantidad_de_curados) {
        this.cantidad_de_curados = cantidad_de_curados;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
}
