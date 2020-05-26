import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class GuiMapaPane extends JPanel implements ActionListener {

    Timer t;
    agente[] personas;
    int cantidad_de_personas;


    public GuiMapaPane(int cantidad_de_personas, int cantidad_de_enfermos, int cantidad_de_sanos, int cantidad_de_curados,
                       int []velocidad_minima, int []velocidad_maxima){

        personas = new agente[cantidad_de_personas];

        for(int i = 0; i < cantidad_de_personas; i++){
            for (int j = 0; j < cantidad_de_curados; j++) {
                personas[i] = new agente(0,"c",Math.random()*velocidad_maxima[0] + velocidad_minima[0],
                        Math.random()*velocidad_maxima[0] + velocidad_minima[0],0,Math.random()*390,
                        Math.random()*660);
            }
            for (int j = 0; j < cantidad_de_enfermos; j++) {
                personas[i] = new agente(0,"e",Math.random()*velocidad_maxima[0] + velocidad_minima[0],
                        Math.random()*velocidad_maxima[0] + velocidad_minima[0],0,Math.random()*390,
                        Math.random()*660);
            }
            for (int j = 0; j < cantidad_de_sanos; j++) {
                personas[i] = new agente(0,"s",Math.random()*velocidad_maxima[0] + velocidad_minima[0],
                        Math.random()*velocidad_maxima[0] + velocidad_minima[0],0,Math.random()*390,
                        Math.random()*660);
            }
        }

        this.cantidad_de_personas = cantidad_de_personas;

        t = new Timer(15, this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D mapa = (Graphics2D) g;

        Ellipse2D[] persona = new Ellipse2D.Double[cantidad_de_personas];

        for(int i = 0; i < cantidad_de_personas; i++){
             persona[i] = new Ellipse2D.Double(personas[i].getVelocidad_x(),personas[i].getVelocidad_y(),10,10);
             mapa.setPaint(Color.BLUE);
             mapa.fill(persona[i]);
        }

        t.start();
    }
    public void actionPerformed(ActionEvent e){

        for(int i = 0; i < cantidad_de_personas;i++){
            if(personas[i].getPosicion_en_eje_x() < 0 || personas[i].getPosicion_en_eje_x() > 390) personas[i].invertir_posicion_x();
            if(personas[i].getPosicion_en_eje_y() < 0 || personas[i].getPosicion_en_eje_y() > 660) personas[i].invertir_posicion_y();

            personas[i].setPosicion_en_eje_x(personas[i].getVelocidad_x());
            personas[i].setPosicion_en_eje_y(personas[i].getPosicion_en_eje_y());
            repaint();
        }
    }

}
