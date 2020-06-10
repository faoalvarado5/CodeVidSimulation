package SingleMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// The import from the object
import Classes.*;

public class MainGuiFrameSingle {

    JFrame f;
    DatosActuales datos_progresivos_de_la_enfermedad = new DatosActuales();
    int contador_de_imagenes_tomadas = 0;

    MainGuiFrameSingle(ArrayList<agente> arreglo_de_agentes, mapa configuracion_mapa, enfermedad configuracion_enfermedad, int total) {

        JFrame frame = new JFrame( "Simulación de propagación de CODE-VID" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        JButton boton_para_tomar_screen = new JButton("Guardar image");

        JTabbedPane graficas = new JTabbedPane();
        JTabbedPane mapas = new JTabbedPane();

        datos_progresivos_de_la_enfermedad.agregar_datos_de_curados(configuracion_enfermedad.getCantidad_recuperados_actuales());
        datos_progresivos_de_la_enfermedad.agregar_datos_de_enfermos(configuracion_enfermedad.getCantidad_enfermos_actuales());
        datos_progresivos_de_la_enfermedad.agregar_datos_de_sanos(configuracion_enfermedad.getCantidad_sanos_actuales());

        graficas.add("Grafica para total", new GraficaMultiple(datos_progresivos_de_la_enfermedad.getCantidad_de_curados(), datos_progresivos_de_la_enfermedad.getCantidad_de_enfermos(),
                datos_progresivos_de_la_enfermedad.getCantidad_de_sanos(), arreglo_de_agentes.size(), configuracion_enfermedad.getDias_totales()));
        graficas.add("Grafica para curados", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_sanos(),2, arreglo_de_agentes.size()));
        graficas.add("Grafica para enfermos", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_enfermos(),1, arreglo_de_agentes.size()));
        graficas.add("Grafica para sanos", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_curados(),3, arreglo_de_agentes.size()));

        mapas.add("Costa Rica",  new GuiMapSingle(configuracion_enfermedad, arreglo_de_agentes, configuracion_mapa, datos_progresivos_de_la_enfermedad, frame));

        mapas.setPreferredSize( new Dimension(configuracion_mapa.getAncho(), configuracion_mapa.getLargo()));
        graficas.setPreferredSize( new Dimension(configuracion_mapa.getAncho(), configuracion_mapa.getLargo()));

        JPanel panel_completo = new JPanel();

        panel_completo.add(mapas);
        panel_completo.add(graficas);
        panel_completo.add(boton_para_tomar_screen);
        frame.add(panel_completo);

        /**
        mapas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("X: "+x+" \t Y: "+y);

            }
        });
        */

        frame.setLocation( 200, 200 );
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible( true );

        boton_para_tomar_screen.setPreferredSize(new Dimension(200, 40));
        boton_para_tomar_screen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try
                {
                    BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics2D = image.createGraphics();
                    frame.paint(graphics2D);
                    File file = new File("latex/");
                    //Creating the directory
                    file.mkdir();
                    ImageIO.write(image,"jpeg", new File("latex/grafica"+contador_de_imagenes_tomadas+".jpeg"));
                    contador_de_imagenes_tomadas++;
                }
                catch(Exception exception)
                {
                    //code
                }
            }
        });
    }
}
