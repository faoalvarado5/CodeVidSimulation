package Multimap;

import Classes.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

// The import from the object

public class MainGuiFrameMulti {

    JFrame f;
    DatosActuales datos_progresivos_de_la_enfermedad = new DatosActuales();

    MainGuiFrameMulti(ArrayList<agente> arreglo_de_agentes, mapa configuracion_mapa, enfermedad configuracion_enfermedad, int total, Server servidores) {

        System.out.println(servidores.toString());

        JFrame frame = new JFrame( "Simulación de propagación de CODE-VID" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        JTabbedPane graficas = new JTabbedPane();
        JTabbedPane mapas = new JTabbedPane();

        datos_progresivos_de_la_enfermedad.agregar_datos_de_curados(configuracion_enfermedad.getCantidad_recuperados_actuales());
        datos_progresivos_de_la_enfermedad.agregar_datos_de_enfermos(configuracion_enfermedad.getCantidad_enfermos_actuales());
        datos_progresivos_de_la_enfermedad.agregar_datos_de_sanos(configuracion_enfermedad.getCantidad_sanos_actuales());

        graficas.add("Grafica para total", new GraficaMultiple(datos_progresivos_de_la_enfermedad.getCantidad_de_curados(), datos_progresivos_de_la_enfermedad.getCantidad_de_enfermos(),
                datos_progresivos_de_la_enfermedad.getCantidad_de_sanos(), arreglo_de_agentes.size(), configuracion_enfermedad.getDias_totales(),  datos_progresivos_de_la_enfermedad));
        graficas.add("Grafica para curados", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_sanos(),2, arreglo_de_agentes.size()));
        graficas.add("Grafica para enfermos", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_enfermos(),1, arreglo_de_agentes.size()));
        graficas.add("Grafica para sanos", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_curados(),3, arreglo_de_agentes.size()));

        mapas.add("Costa Rica",  new GuiMapMulti(configuracion_enfermedad, arreglo_de_agentes, configuracion_mapa, datos_progresivos_de_la_enfermedad, frame, servidores));

        mapas.setPreferredSize( new Dimension(configuracion_mapa.getAncho(), configuracion_mapa.getLargo()));
        graficas.setPreferredSize( new Dimension(configuracion_mapa.getAncho(), configuracion_mapa.getLargo()));

        JPanel panel_completo = new JPanel();

        panel_completo.add(mapas);
        panel_completo.add(graficas);
        frame.add(panel_completo);

        frame.setLocation( 200, 200 );
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible( true );
    }
}
