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

            if(cantidad_de_curados > 0) {
                personas[i] = new agente(0, "c", Math.random() * velocidad_maxima[i] + velocidad_minima[i],
                        Math.random() * velocidad_maxima[i] + velocidad_minima[i], 0, Math.random() * 390,
                        Math.random() * 660);
                cantidad_de_curados--;
            }else if(cantidad_de_enfermos > 0) {
                personas[i] = new agente(0, "e", Math.random() * velocidad_maxima[i] + velocidad_minima[i],
                        Math.random() * velocidad_maxima[i] + velocidad_minima[i], 0, Math.random() * 390,
                        Math.random() * 660);
                cantidad_de_enfermos--;
            }else {
                personas[i] = new agente(0, "s", Math.random() * velocidad_maxima[i] + velocidad_minima[i],
                        Math.random() * velocidad_maxima[i] + velocidad_minima[i], 0, Math.random() * 390,
                        Math.random() * 660);
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

        t.start();
    }
    public void actionPerformed(ActionEvent e){

        for(int i = 0; i < cantidad_de_personas;i++){
            if(personas[i].getPosicion_en_eje_x() < 0 || personas[i].getPosicion_en_eje_x() > 390) personas[i].invertir_velocidad_x();
            if(personas[i].getPosicion_en_eje_y() < 0 || personas[i].getPosicion_en_eje_y() > 660) personas[i].invertir_velocidad_y();

            personas[i].setPosicion_en_eje_x(personas[i].getVelocidad_x());
            personas[i].setPosicion_en_eje_y(personas[i].getVelocidad_y());
            repaint();
        }
    }

}
