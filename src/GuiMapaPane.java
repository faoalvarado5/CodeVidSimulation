import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class GuiMapaPane extends JPanel implements ActionListener {

    Timer t;
    ArrayList<agente> arreglo_de_los_agentes;
    enfermedad configuracion_de_la_enfermedad;
    mapa configuracion_del_mapa;
    DatosActuales datos_progresivos_de_la_enfermedad;
    Frame f;
    JLabel dias_corriendo;

    public GuiMapaPane(enfermedad configuracion_de_la_enfermedad, ArrayList<agente> arreglo_de_los_agentes, mapa configuracion_del_mapa, DatosActuales datos_progresivos_de_la_enfermedad, Frame f, JLabel dias_corriendo){
        this.configuracion_de_la_enfermedad = configuracion_de_la_enfermedad;
        this.arreglo_de_los_agentes = arreglo_de_los_agentes;
        this.configuracion_del_mapa = configuracion_del_mapa;
        this.datos_progresivos_de_la_enfermedad = datos_progresivos_de_la_enfermedad;
        t = new Timer(50, this);
        this.f = f;
        this.dias_corriendo = dias_corriendo;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D mapa = (Graphics2D) g;
        Ellipse2D[] persona = new Ellipse2D.Double[arreglo_de_los_agentes.size()];
        cambiar_color_de_las_personas();

        for(int i = 0; i < arreglo_de_los_agentes.size(); i++){

             persona[i] = new Ellipse2D.Double(arreglo_de_los_agentes.get(i).getPosicion_en_eje_x(),arreglo_de_los_agentes.get(i).getPosicion_en_eje_y(),10,10);
             if(arreglo_de_los_agentes.get(i).getEstado().equals("e")){
                 mapa.setPaint(Color.RED);
             }else if(arreglo_de_los_agentes.get(i).getEstado().equals("c")){
                 mapa.setPaint(Color.BLUE);
             }else{
                 mapa.setPaint(Color.GREEN);
             }
             mapa.fill(persona[i]);
        }
        curar_enfermos();
        actualizar_datos_progresivos();

        datos_progresivos_de_la_enfermedad.aumentar_dias_corriendo();

        if(datos_progresivos_de_la_enfermedad.getDias() == configuracion_de_la_enfermedad.getDias_totales()) t.stop();
        f.repaint();
        t.start();
    }
    public void actionPerformed(ActionEvent e){

        for(int i = 0; i < arreglo_de_los_agentes.size();i++){
            if(arreglo_de_los_agentes.get(i).getPosicion_en_eje_x() < 0 || arreglo_de_los_agentes.get(i).getPosicion_en_eje_x() > configuracion_del_mapa.getAncho()-20) arreglo_de_los_agentes.get(i).invertir_posicion_x();
            if(arreglo_de_los_agentes.get(i).getPosicion_en_eje_y() < 0 || arreglo_de_los_agentes.get(i).getPosicion_en_eje_y() > configuracion_del_mapa.getLargo()-20) arreglo_de_los_agentes.get(i).invertir_posicion_y();

            arreglo_de_los_agentes.get(i).mover_eje_x();
            arreglo_de_los_agentes.get(i).mover_eje_y();
            repaint();
         }
    }

    public void curar_enfermos(){
        for(int i = 0; i < arreglo_de_los_agentes.size();i++){
            if(arreglo_de_los_agentes.get(i).getTiempo_enfermo() >= configuracion_de_la_enfermedad.getDias_de_recuperacion() && arreglo_de_los_agentes.get(i).getEstado().equals("e")){
                arreglo_de_los_agentes.get(i).setEstado("c");
                arreglo_de_los_agentes.get(i).setTiempo_enfermo(0);
            }else if(arreglo_de_los_agentes.get(i).getEstado().equals("e") && Math.random() >= configuracion_de_la_enfermedad.getProbabilidad_muerte()){
                arreglo_de_los_agentes.get(i).aumentar_dias_de_enfermos();
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

                        if((Math.random()*100) <= probabilidad_de_contagio) {

                            arreglo_de_los_agentes.get(indicePersonas).setEstado("e");
                        } else{

                            continue;
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
