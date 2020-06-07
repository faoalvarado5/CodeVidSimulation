package Classes;

import java.util.ArrayList;

public class enfermedad {

    private int dias_corriendo;
    private int cantidad_enfermos_actuales;
    private int cantidad_sanos_actuales;
    private int cantidad_recuperados_actuales;
    private float probabilidad_muerte;
    private int dias_de_muerte;
    private int dias_de_recuperacion;
    private ArrayList<ArrayList<Float>> matriz_de_cotagio;
    private int reinfeccion;
    private int dias_totales;

    public enfermedad() {
    }

    public enfermedad(int dias_corriendo, int cantidad_enfermos_actuales, int cantidad_sanos_actuales, int cantidad_recuperados_actuales, float probabilidad_muerte, int dias_de_muerte, int dias_de_recuperacion, ArrayList<ArrayList<Float>> matriz_de_cotagio, int reinfeccion, int dias_totales) {
        this.dias_corriendo = dias_corriendo;
        this.cantidad_enfermos_actuales = cantidad_enfermos_actuales;
        this.cantidad_sanos_actuales = cantidad_sanos_actuales;
        this.cantidad_recuperados_actuales = cantidad_recuperados_actuales;
        this.probabilidad_muerte = probabilidad_muerte;
        this.dias_de_muerte = dias_de_muerte;
        this.dias_de_recuperacion = dias_de_recuperacion;
        this.matriz_de_cotagio = matriz_de_cotagio;
        this.reinfeccion = reinfeccion;
        this.dias_totales = dias_totales;
    }

    public int getDias_corriendo() {
        return dias_corriendo;
    }

    public void setDias_corriendo(int dias_corriendo) {
        this.dias_corriendo = dias_corriendo;
    }

    public int getCantidad_enfermos_actuales() {
        return cantidad_enfermos_actuales;
    }

    public void setCantidad_enfermos_actuales(int cantidad_enfermos_actuales) {
        this.cantidad_enfermos_actuales = cantidad_enfermos_actuales;
    }

    public int getCantidad_sanos_actuales() {
        return cantidad_sanos_actuales;
    }

    public void setCantidad_sanos_actuales(int cantidad_sanos_actuales) {
        this.cantidad_sanos_actuales = cantidad_sanos_actuales;
    }

    public int getCantidad_recuperados_actuales() {
        return cantidad_recuperados_actuales;
    }

    public void setCantidad_recuperados_actuales(int cantidad_recuperados_actuales) {
        this.cantidad_recuperados_actuales = cantidad_recuperados_actuales;
    }

    public float getProbabilidad_muerte() {
        return probabilidad_muerte;
    }

    public void setProbabilidad_muerte(float probabilidad_muerte) {
        this.probabilidad_muerte = probabilidad_muerte;
    }

    public int getDias_de_muerte() {
        return dias_de_muerte;
    }

    public void setDias_de_muerte(int dias_de_muerte) {
        this.dias_de_muerte = dias_de_muerte;
    }

    public int getDias_de_recuperacion() {
        return dias_de_recuperacion;
    }

    public void setDias_de_recuperacion(int dias_de_recuperacion) {
        this.dias_de_recuperacion = dias_de_recuperacion;
    }

    public ArrayList<ArrayList<Float>> getMatriz_de_cotagio() {
        return matriz_de_cotagio;
    }

    public void setMatriz_de_cotagio(ArrayList<ArrayList<Float>> matriz_de_cotagio) {
        this.matriz_de_cotagio = matriz_de_cotagio;
    }

    public int getReinfeccion() {
        return reinfeccion;
    }

    public void setReinfeccion(int reinfeccion) {
        this.reinfeccion = reinfeccion;
    }

    public int getDias_totales() {
        return dias_totales;
    }

    public void setDias_totales(int dias_totales) {
        this.dias_totales = dias_totales;
    }

    @Override
    public String toString() {
        return "enfermedad{" +
                "dias_corriendo=" + dias_corriendo +
                ", cantidad_enfermos_actuales=" + cantidad_enfermos_actuales +
                ", cantidad_sanos_actuales=" + cantidad_sanos_actuales +
                ", cantidad_recuperados_actuales=" + cantidad_recuperados_actuales +
                ", probabilidad_muerte=" + probabilidad_muerte +
                ", dias_de_muerte=" + dias_de_muerte +
                ", dias_de_recuperacion=" + dias_de_recuperacion +
                ", matriz_de_cotagio=" + matriz_de_cotagio +
                ", reinfeccion=" + reinfeccion +
                ", dias_totales=" + dias_totales +
                '}';
    }
}
