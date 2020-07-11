package Classes.Threads;

import Classes.Generador_latex;
import Classes.agente;
import Classes.enfermedad;

import java.io.IOException;
import java.util.ArrayList;

public class ImageThread extends Thread{

    ArrayList<Integer> listaGrafico;
    ArrayList<agente> arreglo_de_los_agentes;           // Esta variable contiene toda la información de las personas en el mapa
    enfermedad configuracion_de_la_enfermedad;          // Esta variable nos permite utilizar toda la configuración de la enfermedad
    ArrayList<Integer> agentes_viajeros = new ArrayList<Integer>();

    public ImageThread(ArrayList<Integer> listaGrafico, ArrayList<agente> arreglo_de_los_agentes, enfermedad configuracion_de_la_enfermedad, ArrayList<Integer> agentes_viajeros){

        this.listaGrafico = listaGrafico;
        this.arreglo_de_los_agentes = arreglo_de_los_agentes;
        this.configuracion_de_la_enfermedad = configuracion_de_la_enfermedad;
        this.agentes_viajeros = agentes_viajeros;
    }

    public void ImageFunction(){

        Generador_latex gl = new Generador_latex();
        try {
            gl.generarLatex(arreglo_de_los_agentes.size(), configuracion_de_la_enfermedad.getDias_totales(), listaGrafico, agentes_viajeros);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(){
        ImageFunction();
    }

}
