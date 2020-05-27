import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GuiMapaPane extends JPanel implements ActionListener {

    Timer t;
    ArrayList<agente> arreglo_de_los_agentes;
    enfermedad configuracion_de_la_enfermedad;
    mapa configuracion_del_mapa;

    public GuiMapaPane(enfermedad configuracion_de_la_enfermedad, ArrayList<agente> arreglo_de_los_agentes, mapa configuracion_del_mapa){
        this.configuracion_de_la_enfermedad = configuracion_de_la_enfermedad;
        this.arreglo_de_los_agentes = arreglo_de_los_agentes;
        this.configuracion_del_mapa = configuracion_del_mapa;
        t = new Timer(50, this);
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
            if(arreglo_de_los_agentes.get(i).getTiempo_enfermo() >= 18 && arreglo_de_los_agentes.get(i).getEstado().equals("e")){
                arreglo_de_los_agentes.get(i).setEstado("c");
                arreglo_de_los_agentes.get(i).setTiempo_enfermo(0);
            }else if(arreglo_de_los_agentes.get(i).getEstado().equals("e")){
                arreglo_de_los_agentes.get(i).aumentar_dias_de_enfermos();
            }
        }
    }

    public void cambiar_color_de_las_personas(){
        for(int indiceEnfermos = 0; indiceEnfermos < arreglo_de_los_agentes.size();indiceEnfermos++){
            if(arreglo_de_los_agentes.get(indiceEnfermos).getEstado().equals("e")){

                System.out.println(arreglo_de_los_agentes.get(indiceEnfermos).toString());
                System.out.println("------------------------------------------------");
                for(int indicePersonas = 0; indicePersonas < arreglo_de_los_agentes.size();indicePersonas++) {

                    int posicion_x = (int)arreglo_de_los_agentes.get(indicePersonas).getPosicion_en_eje_x();
                    int posicion_y = (int)arreglo_de_los_agentes.get(indicePersonas).getPosicion_en_eje_y() ;
                    int posicion_x_enfermos = (int)arreglo_de_los_agentes.get(indiceEnfermos).getPosicion_en_eje_x();
                    int posicion_y_enfermos = (int)arreglo_de_los_agentes.get(indiceEnfermos).getPosicion_en_eje_y();



                    if((posicion_x + 5 >= posicion_x_enfermos && posicion_x - 5 <= posicion_x_enfermos) && (posicion_y + 5 >= posicion_y_enfermos && posicion_y - 5 <= posicion_y_enfermos)
                            && indiceEnfermos != indicePersonas){
                            arreglo_de_los_agentes.get(indicePersonas).setEstado("e");
                            System.out.println("Entra");
                            System.out.println(posicion_x + " = " + posicion_x_enfermos);
                            System.out.println(posicion_y + " = " + posicion_y_enfermos);
                    }

                }
            }
        }
    }

}
