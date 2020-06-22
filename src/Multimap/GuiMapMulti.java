package Multimap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.ArrayList;
// The import from the object
import Classes.*;

/**
 *
 * Esta es la funcion para poder imprimir las personas en el mapa, contiene un actionlistener, este es necesario para que podamos
 * seleccionar la acción que se quiera poner en el loop con el Timer
 *
 */

public class GuiMapMulti extends JPanel implements ActionListener {

    Timer t;                                            // Esta variable nos permite poner en un loop la acción de mover las personas
    ArrayList<agente> arreglo_de_los_agentes;           // Esta variable contiene toda la información de las personas en el mapa
    enfermedad configuracion_de_la_enfermedad;          // Esta variable nos permite utilizar toda la configuración de la enfermedad
    mapa configuracion_del_mapa;                        // Esta variable la ocupamos únicamente para poner las paredes en el mapa
    DatosActuales datos_progresivos_de_la_enfermedad;   // Esta variable guarda un registro de los infectados, curados y sanos, de igual forma con los dias transcurridos
    Frame f;                                            // Este es el frame principal del programa, lo incluímos aquí para que se actualice la pantalla durante cada loop del timer
    int contador;                                       // Este contador guarda la cantidad de veces que se ha recorrido la acción del timer
    Graphics2D mapa;                                    // Se crea la variable del mapa, aquí se desplegarán las personas y las paredes
    ArrayList<Integer> listaGrafico = new ArrayList<>();

    public GuiMapMulti(enfermedad configuracion_de_la_enfermedad, ArrayList<agente> arreglo_de_los_agentes, mapa configuracion_del_mapa, DatosActuales datos_progresivos_de_la_enfermedad, Frame f){
        this.configuracion_de_la_enfermedad = configuracion_de_la_enfermedad;
        this.arreglo_de_los_agentes = arreglo_de_los_agentes;
        this.configuracion_del_mapa = configuracion_del_mapa;
        this.datos_progresivos_de_la_enfermedad = datos_progresivos_de_la_enfermedad;
        t = new Timer(30, this);
        this.f = f;
    }

    // Esta función está incluída dentro de JComponent y es necesaria para mostrar gráficos en java
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Se le suma 1 cada vez que se recorre con el timer
        contador++;

        // El mapa se le asignará la variable g, que viene siendo el parametro por defecto de JComponent
        mapa = (Graphics2D) g;

        // Este arreglo de elipse se necesitará para poder poner todas las personas en el mapa
        Ellipse2D[] persona = new Ellipse2D.Double[arreglo_de_los_agentes.size()];

        // Esta función se llama para poder cambiar las personas de sanas a enfermas, curadas a enfermas y curadas a sanas
        cambiar_color_de_las_personas();


        for(int i = 0; i < arreglo_de_los_agentes.size(); i++){
            // Por cada personas que exista se van a crear los círculos que se pondrán en el mapa
             persona[i] = new Ellipse2D.Double(arreglo_de_los_agentes.get(i).getPosicion_en_eje_x(),arreglo_de_los_agentes.get(i).getPosicion_en_eje_y(),10,10);

             // Debemos poner el color correspondiente de las personas según su estado
             if(arreglo_de_los_agentes.get(i).getEstado().equals("e")){
                 mapa.setPaint(Color.RED);
             }else if(arreglo_de_los_agentes.get(i).getEstado().equals("c")){
                 mapa.setPaint(Color.BLUE);
             }else{
                 mapa.setPaint(Color.GREEN);
             }
             // Finalmente lo metemos en el mapa
             mapa.fill(persona[i]);
        }

        for(int i = 0; i < configuracion_del_mapa.getParedes().size(); i++){
            // Por cada pared que exista en la variable de configuración del mapa se debe de meter en el mapa
            mapa.setPaint(Color.BLACK);
            if (configuracion_del_mapa.getParedes().get(i).getEs_horizontal()){
                mapa.drawLine(configuracion_del_mapa.getParedes().get(i).getX1()+5, configuracion_del_mapa.getParedes().get(i).getY1(),
                        configuracion_del_mapa.getParedes().get(i).getX2()-5, configuracion_del_mapa.getParedes().get(i).getY2());
            }else{
                mapa.drawLine(configuracion_del_mapa.getParedes().get(i).getX1(), configuracion_del_mapa.getParedes().get(i).getY1()+5,
                        configuracion_del_mapa.getParedes().get(i).getX2(), configuracion_del_mapa.getParedes().get(i).getY2()-5);
            }
        }

        // Dependiendo de la cantidad de días que una persona esté enferma se debe de sanar (Esto es una probabilidad, no es estático)
        curar_enfermos();

        // Se aumentan los dias cada vez que se recorre el timer
        datos_progresivos_de_la_enfermedad.aumentar_dias_corriendo();

        // Esta es la condición de parada
        if(datos_progresivos_de_la_enfermedad.getDias() > configuracion_de_la_enfermedad.getDias_totales()*100) {
            t.stop();
            Generador_latex gl = new Generador_latex();
            try {
                gl.generarLatex(arreglo_de_los_agentes.size(), configuracion_de_la_enfermedad.getDias_totales(), listaGrafico);
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            if(datos_progresivos_de_la_enfermedad.getDias()%100 == 0){
                //listaGrafico.add(datos_progresivos_de_la_enfermedad.getDias()/100);
                //listaGrafico.add(datos_progresivos_de_la_enfermedad.getCantidad_de_enfermos().size());
                System.out.println("Dias: " + datos_progresivos_de_la_enfermedad.getDias()/100 + " Enf: " + datos_progresivos_de_la_enfermedad.getCantidad_de_enfermos().size());
            }
            // Esta función actualiza los datos del día; esto es necesario para poder actualizar la gráfica en tiempo real
            actualizar_datos_progresivos();
        }

        // Se actualiza el frame cada vez que una persona se mueve y los datos de las gráficas cambian
        f.repaint();

        // Inicia el timer
        t.start();
        System.out.println(contador);
    }
    public void actionPerformed(ActionEvent e){

        // Este loop sirve para mover las personas
        for(int i = 0; i < arreglo_de_los_agentes.size();i++){

            // Esta variable la vamos a utilizar para poder ver si la persona choca con pared, si no lo hace entonces
            // puede chocar con alguna linea puesta por el usuario.
            int validador_de_pared = 0;
            Double posicion_de_la_pared_que_choco = 0.0;
            if(arreglo_de_los_agentes.get(i).getTipo() != 3) {

                arreglo_de_los_agentes.get(i).agregar_puntos_al_agente_de_tipo_2();

                // Si la persona llega al borde, entonces se debe devolver
                if ((arreglo_de_los_agentes.get(i).getPosicion_en_eje_x() + arreglo_de_los_agentes.get(i).getVelocidad_x()< 0 || arreglo_de_los_agentes.get(i).getPosicion_en_eje_x() + arreglo_de_los_agentes.get(i).getVelocidad_x() > configuracion_del_mapa.getAncho() - 20)) {
                    arreglo_de_los_agentes.get(i).invertir_posicion_x();
                }
                if (arreglo_de_los_agentes.get(i).getPosicion_en_eje_y() + arreglo_de_los_agentes.get(i).getVelocidad_y() < 0 || arreglo_de_los_agentes.get(i).getPosicion_en_eje_y() + arreglo_de_los_agentes.get(i).getVelocidad_y() > configuracion_del_mapa.getLargo() - 25) {
                    arreglo_de_los_agentes.get(i).invertir_posicion_y();
                }

                for (int j = 0; j < configuracion_del_mapa.getParedes().size(); j++) {
                    ArrayList<Integer[]> funciones_lineales_de_las_paredes = configuracion_del_mapa.getParedes().get(j).getFunciones_lineales_de_las_paredes();

                    double valor_que_contiene_pixeles_de_distancia = 0;
                    for (int z = 0; z < funciones_lineales_de_las_paredes.size(); z++) {

                        if (configuracion_del_mapa.getParedes().get(j).getEs_horizontal()) {

                            valor_que_contiene_pixeles_de_distancia = Math.abs(funciones_lineales_de_las_paredes.get(z)[1] - arreglo_de_los_agentes.get(i).getPosicion_en_eje_y());

                            if (valor_que_contiene_pixeles_de_distancia < Math.abs(arreglo_de_los_agentes.get(i).getVelocidad_y()) &&
                                    arreglo_de_los_agentes.get(i).getPosicion_en_eje_x() == funciones_lineales_de_las_paredes.get(z)[0]) {
                                arreglo_de_los_agentes.get(i).invertir_posicion_y();
                                if(Math.random() < 0.5){
                                    arreglo_de_los_agentes.get(i).invertir_posicion_x();
                                }

                                arreglo_de_los_agentes.get(i).mover_eje_y();
                                arreglo_de_los_agentes.get(i).mover_eje_x();
                            }
                        } else {

                            valor_que_contiene_pixeles_de_distancia = Math.abs(funciones_lineales_de_las_paredes.get(z)[0] - arreglo_de_los_agentes.get(i).getPosicion_en_eje_x());

                            if (valor_que_contiene_pixeles_de_distancia < Math.abs(arreglo_de_los_agentes.get(i).getVelocidad_x()) &&
                                    arreglo_de_los_agentes.get(i).getPosicion_en_eje_y() == funciones_lineales_de_las_paredes.get(z)[1]) {
                                if(Math.random() < 0.5){
                                    arreglo_de_los_agentes.get(i).invertir_posicion_y();
                                }arreglo_de_los_agentes.get(i).invertir_posicion_x();
                                arreglo_de_los_agentes.get(i).mover_eje_y();
                                arreglo_de_los_agentes.get(i).mover_eje_x();
                            }
                        }
                    }
                }

                if(arreglo_de_los_agentes.get(i).getTipo() == 1 || arreglo_de_los_agentes.get(i).getPosiciones_del_tipo_2().size() < 7) {
                    arreglo_de_los_agentes.get(i).mover_eje_y();
                    arreglo_de_los_agentes.get(i).mover_eje_x();
                }else if(arreglo_de_los_agentes.get(i).getPosiciones_del_tipo_2().size() >= 7){
                    ArrayList<Double[]> puntos_del_tipo_2 = arreglo_de_los_agentes.get(i).getPosiciones_del_tipo_2();
                    arreglo_de_los_agentes.get(i).setPosicion_en_eje_x(puntos_del_tipo_2.get(arreglo_de_los_agentes.get(i).getPosicion_del_tipo_2())[0]);
                    arreglo_de_los_agentes.get(i).setPosicion_en_eje_y(puntos_del_tipo_2.get(arreglo_de_los_agentes.get(i).getPosicion_del_tipo_2())[1]);

                    arreglo_de_los_agentes.get(i).aumentar_posicion_del_tipo_2();
                }
            }else {

                Boolean validador_para_averiguar_si_toca_pared_del_frame = false;

                // Si la persona llega al borde, entonces se debe devolver
                if ((arreglo_de_los_agentes.get(i).getPosicion_en_eje_x() < 0 || arreglo_de_los_agentes.get(i).getPosicion_en_eje_x() > configuracion_del_mapa.getAncho() - 20)) {
                    arreglo_de_los_agentes.get(i).invertir_posicion_x();
                    validador_para_averiguar_si_toca_pared_del_frame = true;
                }
                if (arreglo_de_los_agentes.get(i).getPosicion_en_eje_y() < 0 || arreglo_de_los_agentes.get(i).getPosicion_en_eje_y() > configuracion_del_mapa.getLargo() - 25) {
                    arreglo_de_los_agentes.get(i).invertir_posicion_y();
                    validador_para_averiguar_si_toca_pared_del_frame = true;
                }

                for (int j = 0; j < configuracion_del_mapa.getParedes().size(); j++) {
                    ArrayList<Integer[]> funciones_lineales_de_las_paredes = configuracion_del_mapa.getParedes().get(j).getFunciones_lineales_de_las_paredes();
                    double valor_que_contiene_pixeles_de_distancia = 0;

                    for (int z = 0; z < funciones_lineales_de_las_paredes.size(); z++) {
                        if (configuracion_del_mapa.getParedes().get(j).getEs_horizontal()) {

                            valor_que_contiene_pixeles_de_distancia = Math.abs(funciones_lineales_de_las_paredes.get(z)[1] - arreglo_de_los_agentes.get(i).getPosicion_en_eje_y());

                            if (valor_que_contiene_pixeles_de_distancia < Math.abs(arreglo_de_los_agentes.get(i).getVelocidad_y()) &&
                                    arreglo_de_los_agentes.get(i).getPosicion_en_eje_x() == funciones_lineales_de_las_paredes.get(z)[0]) {
                                arreglo_de_los_agentes.get(i).mover_eje_y();
                                arreglo_de_los_agentes.get(i).mover_eje_x();
                            }
                        } else {

                            valor_que_contiene_pixeles_de_distancia = Math.abs(funciones_lineales_de_las_paredes.get(z)[0] - arreglo_de_los_agentes.get(i).getPosicion_en_eje_x());

                            if (valor_que_contiene_pixeles_de_distancia < Math.abs(arreglo_de_los_agentes.get(i).getVelocidad_x()) &&
                                    arreglo_de_los_agentes.get(i).getPosicion_en_eje_y() == funciones_lineales_de_las_paredes.get(z)[1]) {
                                arreglo_de_los_agentes.get(i).mover_eje_y();
                                arreglo_de_los_agentes.get(i).mover_eje_x();
                            }
                        }
                    }
                }

                if(!validador_para_averiguar_si_toca_pared_del_frame){
                    arreglo_de_los_agentes.get(i).mover_aleatoreamente();
                }
                arreglo_de_los_agentes.get(i).mover_eje_y();
                arreglo_de_los_agentes.get(i).mover_eje_x();
            }
         }
        repaint();
    }

    public void curar_enfermos(){
        for(int i = 0; i < arreglo_de_los_agentes.size();i++){
            if(arreglo_de_los_agentes.get(i).getTiempo_enfermo() >= 180 && arreglo_de_los_agentes.get(i).getEstado().equals("e")){
                arreglo_de_los_agentes.get(i).setEstado("c");
                arreglo_de_los_agentes.get(i).setTiempo_enfermo(0);
            }else if(arreglo_de_los_agentes.get(i).getEstado().equals("e")){
                arreglo_de_los_agentes.get(i).aumentar_dias_de_enfermos();
                if(Math.random()*100 <= configuracion_de_la_enfermedad.getProbabilidad_muerte() && arreglo_de_los_agentes.get(i).getTiempo_enfermo()%10 == 0){
                    arreglo_de_los_agentes.remove(i);
                }
            }
        }
    }

    public void cambiar_color_de_las_personas(){
        for(int indiceEnfermos = 0; indiceEnfermos < arreglo_de_los_agentes.size();indiceEnfermos++){
            if(arreglo_de_los_agentes.get(indiceEnfermos).getEstado().equals("e")){

                for(int indicePersonas = 0; indicePersonas < arreglo_de_los_agentes.size();indicePersonas++) {

                    int posicion_x = (int)arreglo_de_los_agentes.get(indicePersonas).getPosicion_en_eje_x();
                    int posicion_y = (int)arreglo_de_los_agentes.get(indicePersonas).getPosicion_en_eje_y() ;
                    int posicion_x_enfermos = (int)arreglo_de_los_agentes.get(indiceEnfermos).getPosicion_en_eje_x();
                    int posicion_y_enfermos = (int)arreglo_de_los_agentes.get(indiceEnfermos).getPosicion_en_eje_y();

                    if((posicion_x + 5 >= posicion_x_enfermos && posicion_x - 5 <= posicion_x_enfermos) && (posicion_y + 5 >= posicion_y_enfermos && posicion_y - 5 <= posicion_y_enfermos)
                            && indiceEnfermos != indicePersonas){

                        int tipo1 = (int)arreglo_de_los_agentes.get(indicePersonas).getTipo();
                        int tipo2 = (int)arreglo_de_los_agentes.get(indiceEnfermos).getTipo();
                        float probabilidad_de_contagio = configuracion_de_la_enfermedad.getMatriz_de_cotagio().get(tipo1-1).get(tipo2-1);

                        if(configuracion_de_la_enfermedad.getReinfeccion() != 0 &&
                                Math.random()*100 <= probabilidad_de_contagio &&
                                arreglo_de_los_agentes.get(indicePersonas).getEstado().equals("c")){

                            arreglo_de_los_agentes.get(indicePersonas).setEstado("e");
                        }else if (Math.random()*100 <= probabilidad_de_contagio &&
                                arreglo_de_los_agentes.get(indicePersonas).getEstado().equals("s")){

                            arreglo_de_los_agentes.get(indicePersonas).setEstado("e");
                        }
                    }
                }
            }
        }
    }

    public void actualizar_datos_progresivos(){

        int cantidad_de_enfermos = 0;
        int cantidad_de_curados = 0;
        int cantidad_de_sanos = 0;

        for(int i = 0; i < arreglo_de_los_agentes.size(); i++){
            if(arreglo_de_los_agentes.get(i).getEstado().equals("e")) cantidad_de_enfermos++;
            else if(arreglo_de_los_agentes.get(i).getEstado().equals("c")) cantidad_de_curados++;
            else cantidad_de_sanos++;
        }

        datos_progresivos_de_la_enfermedad.agregar_datos_de_enfermos(cantidad_de_enfermos);
        datos_progresivos_de_la_enfermedad.agregar_datos_de_curados(cantidad_de_curados);
        datos_progresivos_de_la_enfermedad.agregar_datos_de_sanos(cantidad_de_sanos);

    }
}
