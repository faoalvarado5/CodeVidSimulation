package Multimap;

import Classes.agente;
import Classes.mapa;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class LogicaDeLosAgentes extends Thread{

    agente agente;
    mapa configuracion_del_mapa;                        // Esta variable la ocupamos únicamente para poner las paredes en el mapa


    public LogicaDeLosAgentes(mapa configuracion_del_mapa, agente agente){
        this.configuracion_del_mapa = configuracion_del_mapa;
        this.agente = agente;
    }

    public void logica(){

        // Esta variable la vamos a utilizar para poder ver si la persona choca con pared, si no lo hace entonces
        // puede chocar con alguna linea puesta por el usuario.
        int validador_de_pared = 0;
        Double posicion_de_la_pared_que_choco = 0.0;
        if(agente.getTipo() != 3) {

            agente.agregar_puntos_al_agente_de_tipo_2();

            // Si la persona llega al borde, entonces se debe devolver
            if ((agente.getPosicion_en_eje_x() + agente.getVelocidad_x()< 0 || agente.getPosicion_en_eje_x() + agente.getVelocidad_x() > configuracion_del_mapa.getAncho() - 20)) {
                agente.invertir_posicion_x();
            }
            if (agente.getPosicion_en_eje_y() + agente.getVelocidad_y() < 0 || agente.getPosicion_en_eje_y() + agente.getVelocidad_y() > configuracion_del_mapa.getLargo() - 25) {
                agente.invertir_posicion_y();
            }

            for (int j = 0; j < configuracion_del_mapa.getParedes().size(); j++) {
                ArrayList<Integer[]> funciones_lineales_de_las_paredes = configuracion_del_mapa.getParedes().get(j).getFunciones_lineales_de_las_paredes();

                double valor_que_contiene_pixeles_de_distancia = 0;
                for (int z = 0; z < funciones_lineales_de_las_paredes.size(); z++) {

                    if (configuracion_del_mapa.getParedes().get(j).getEs_horizontal()) {

                        valor_que_contiene_pixeles_de_distancia = Math.abs(funciones_lineales_de_las_paredes.get(z)[1] - agente.getPosicion_en_eje_y());

                        if (valor_que_contiene_pixeles_de_distancia < Math.abs(agente.getVelocidad_y()) &&
                                agente.getPosicion_en_eje_x() == funciones_lineales_de_las_paredes.get(z)[0]) {
                            agente.invertir_posicion_y();
                            if(Math.random() < 0.5){
                                agente.invertir_posicion_x();
                            }

                            agente.mover_eje_y();
                            agente.mover_eje_x();
                        }
                    } else {

                        valor_que_contiene_pixeles_de_distancia = Math.abs(funciones_lineales_de_las_paredes.get(z)[0] - agente.getPosicion_en_eje_x());

                        if (valor_que_contiene_pixeles_de_distancia < Math.abs(agente.getVelocidad_x()) &&
                                agente.getPosicion_en_eje_y() == funciones_lineales_de_las_paredes.get(z)[1]) {
                            if(Math.random() < 0.5){
                                agente.invertir_posicion_y();
                            }agente.invertir_posicion_x();
                            agente.mover_eje_y();
                            agente.mover_eje_x();
                        }
                    }
                }
            }

            if(agente.getTipo() == 1 || agente.getPosiciones_del_tipo_2().size() < 7) {
                agente.mover_eje_y();
                agente.mover_eje_x();
            }else if(agente.getPosiciones_del_tipo_2().size() >= 7){
                ArrayList<Double[]> puntos_del_tipo_2 = agente.getPosiciones_del_tipo_2();
                agente.setPosicion_en_eje_x(puntos_del_tipo_2.get(agente.getPosicion_del_tipo_2())[0]);
                agente.setPosicion_en_eje_y(puntos_del_tipo_2.get(agente.getPosicion_del_tipo_2())[1]);

                agente.aumentar_posicion_del_tipo_2();
            }
        }else {

            Boolean validador_para_averiguar_si_toca_pared_del_frame = false;

            // Si la persona llega al borde, entonces se debe devolver
            if ((agente.getPosicion_en_eje_x() < 0 || agente.getPosicion_en_eje_x() > configuracion_del_mapa.getAncho() - 20)) {
                agente.invertir_posicion_x();
                validador_para_averiguar_si_toca_pared_del_frame = true;
            }
            if (agente.getPosicion_en_eje_y() < 0 || agente.getPosicion_en_eje_y() > configuracion_del_mapa.getLargo() - 25) {
                agente.invertir_posicion_y();
                validador_para_averiguar_si_toca_pared_del_frame = true;
            }

            for (int j = 0; j < configuracion_del_mapa.getParedes().size(); j++) {
                ArrayList<Integer[]> funciones_lineales_de_las_paredes = configuracion_del_mapa.getParedes().get(j).getFunciones_lineales_de_las_paredes();
                double valor_que_contiene_pixeles_de_distancia = 0;

                for (int z = 0; z < funciones_lineales_de_las_paredes.size(); z++) {
                    if (configuracion_del_mapa.getParedes().get(j).getEs_horizontal()) {

                        valor_que_contiene_pixeles_de_distancia = Math.abs(funciones_lineales_de_las_paredes.get(z)[1] - agente.getPosicion_en_eje_y());

                        if (valor_que_contiene_pixeles_de_distancia < Math.abs(agente.getVelocidad_y()) &&
                                agente.getPosicion_en_eje_x() == funciones_lineales_de_las_paredes.get(z)[0]) {
                            agente.mover_eje_y();
                            agente.mover_eje_x();
                        }
                    } else {

                        valor_que_contiene_pixeles_de_distancia = Math.abs(funciones_lineales_de_las_paredes.get(z)[0] - agente.getPosicion_en_eje_x());

                        if (valor_que_contiene_pixeles_de_distancia < Math.abs(agente.getVelocidad_x()) &&
                                agente.getPosicion_en_eje_y() == funciones_lineales_de_las_paredes.get(z)[1]) {
                            agente.mover_eje_y();
                            agente.mover_eje_x();
                        }
                    }
                }
            }

            if(!validador_para_averiguar_si_toca_pared_del_frame){
                agente.mover_aleatoreamente();
            }
            agente.mover_eje_y();
            agente.mover_eje_x();
        }
    }


    @Override
    public void run(){
        logica();
    }

    public agente getAgentes(){
        return agente;
    }
}
