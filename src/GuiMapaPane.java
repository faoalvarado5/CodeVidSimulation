import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class GuiMapaPane extends JPanel implements ActionListener {

    Timer t;
    agente[] personas;
    int cantidad_de_personas;
    int contador = 0;

    public GuiMapaPane(int cantidad_de_personas, int cantidad_de_enfermos, int cantidad_de_sanos, int cantidad_de_curados,
                       int []velocidad_minima, int []velocidad_maxima){

        personas = new agente[cantidad_de_personas*2];
        for(int i = 0; i < cantidad_de_personas; i++){

            if(cantidad_de_curados > 0) {
                personas[i] = new agente(0, "c", Math.random() * velocidad_maxima[i] + velocidad_minima[i],
                        Math.random() * velocidad_maxima[i] + velocidad_minima[i],0,0,Math.random() * 390,
                        Math.random() * 660,0);
                cantidad_de_curados--;
            }else if(cantidad_de_enfermos > 0) {
                personas[i] = new agente(0, "e",
                        Math.random() * velocidad_maxima[i] + velocidad_minima[i],
                        Math.random() * velocidad_maxima[i] + velocidad_minima[i], 0,0,Math.random() * 390,
                        Math.random() * 660,0);
                cantidad_de_enfermos--;
            }else {
                personas[i] = new agente(0, "s", Math.random() * velocidad_maxima[i] + velocidad_minima[i],
                        Math.random() * velocidad_maxima[i] + velocidad_minima[i],0,0, Math.random() * 390,
                        Math.random() * 660,0);
            }
        }

        this.cantidad_de_personas = cantidad_de_personas;

        t = new Timer(50, this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D mapa = (Graphics2D) g;

        Ellipse2D[] persona = new Ellipse2D.Double[cantidad_de_personas];
        cambiar_color_de_las_personas();

        for(int i = 0; i < cantidad_de_personas; i++){
             persona[i] = new Ellipse2D.Double(personas[i].getPosicion_en_eje_x(),personas[i].getPosicion_en_eje_y(),10,10);
             if(personas[i].getEstado().equals("e")){
                 mapa.setPaint(Color.RED);
             }else if(personas[i].getEstado().equals("c")){
                 mapa.setPaint(Color.BLUE);
             }else{
                 mapa.setPaint(Color.GREEN);
             }
             mapa.fill(persona[i]);

        }
        curar_enfermos();
        contador++;

        t.start();
    }
    public void actionPerformed(ActionEvent e){

        for(int i = 0; i < cantidad_de_personas;i++){
            if(personas[i].getPosicion_en_eje_x() < 0 || personas[i].getPosicion_en_eje_x() > 390) personas[i].invertir_posicion_x();
            if(personas[i].getPosicion_en_eje_y() < 0 || personas[i].getPosicion_en_eje_y() > 660) personas[i].invertir_posicion_y();

            personas[i].setPosicion_en_eje_x(personas[i].getVelocidad_x());
            personas[i].setPosicion_en_eje_y(personas[i].getVelocidad_y());
            repaint();
         }
    }

    public void curar_enfermos(){
        for(int i = 0; i < cantidad_de_personas;i++){
            if(personas[i].getTiempo_enfermo() >= 18 && personas[i].getEstado().equals("e")){
                personas[i].setEstado("c");
            }
        }
    }

    public void cambiar_color_de_las_personas(){
        for(int indiceEnfermos = 0; indiceEnfermos < cantidad_de_personas;indiceEnfermos++){
            if(personas[indiceEnfermos].getEstado() == "e"){


                for(int indicePersonas = 0; indicePersonas < cantidad_de_personas;indicePersonas++) {

                    int posicion_x = (int)personas[indicePersonas].getPosicion_en_eje_x();
                    int posicion_y = (int)personas[indicePersonas].getPosicion_en_eje_y() ;
                    int posicion_x_enfermos = (int)personas[indiceEnfermos].getPosicion_en_eje_x();
                    int posicion_y_enfermos = (int)personas[indiceEnfermos].getPosicion_en_eje_y();

                    if((posicion_x + 5 >= posicion_x_enfermos && posicion_x - 5 <= posicion_x_enfermos) && (posicion_y + 5 >= posicion_y_enfermos && posicion_y - 5 <= posicion_y_enfermos)
                            && indiceEnfermos != indicePersonas){
                        personas[indicePersonas].setEstado("e");
                    }

                }
            }
        }
    }

}
