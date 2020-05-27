import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GuiMapa {

    JFrame f;

    GuiMapa(ArrayList<agente> arreglo_de_agentes, mapa configuracion_mapa, enfermedad configuracion_enfermedad) {

        JFrame frame = new JFrame( "Drawing 2D shapes" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        JTabbedPane graficas = new JTabbedPane();
        JTabbedPane mapas = new JTabbedPane();

        DatosActuales datos_progresivos_de_la_enfermedad = new DatosActuales(arreglo_de_agentes.size());

        datos_progresivos_de_la_enfermedad.agregar_datos_de_curados(configuracion_enfermedad.getCantidad_recuperados_actuales());
        datos_progresivos_de_la_enfermedad.agregar_datos_de_enfermos(configuracion_enfermedad.getCantidad_enfermos_actuales());
        datos_progresivos_de_la_enfermedad.agregar_datos_de_sanos(configuracion_enfermedad.getCantidad_sanos_actuales());

        datos_progresivos_de_la_enfermedad.agregar_datos_de_curados(0);
        datos_progresivos_de_la_enfermedad.agregar_datos_de_enfermos(0);
        datos_progresivos_de_la_enfermedad.agregar_datos_de_sanos(81);

        datos_progresivos_de_la_enfermedad.agregar_datos_de_curados(0);
        datos_progresivos_de_la_enfermedad.agregar_datos_de_enfermos(20);
        datos_progresivos_de_la_enfermedad.agregar_datos_de_sanos(60);

        datos_progresivos_de_la_enfermedad.agregar_datos_de_curados(0);
        datos_progresivos_de_la_enfermedad.agregar_datos_de_enfermos(45);
        datos_progresivos_de_la_enfermedad.agregar_datos_de_sanos(36);

        datos_progresivos_de_la_enfermedad.agregar_datos_de_curados(10);
        datos_progresivos_de_la_enfermedad.agregar_datos_de_enfermos(35);
        datos_progresivos_de_la_enfermedad.agregar_datos_de_sanos(36);

        graficas.add("Grafica para total", new GraficaMultiple(datos_progresivos_de_la_enfermedad.getCantidad_de_curados(), datos_progresivos_de_la_enfermedad.getCantidad_de_enfermos(),
                datos_progresivos_de_la_enfermedad.getCantidad_de_sanos(), arreglo_de_agentes.size()));
        graficas.add("Grafica para curados", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_curados(),0, arreglo_de_agentes.size()));
        graficas.add("Grafica para enfermos", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_enfermos(),1, arreglo_de_agentes.size()));
        graficas.add("Grafica para sanos", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_sanos(),2, arreglo_de_agentes.size()));

        mapas.add("Costa Rica",  new GuiMapaPane(configuracion_enfermedad, arreglo_de_agentes, configuracion_mapa));

        mapas.setPreferredSize( new Dimension(configuracion_mapa.getAncho(), configuracion_mapa.getLargo()));
        graficas.setPreferredSize( new Dimension(400, 700));

        JPanel panel_completo = new JPanel();
        panel_completo.add(mapas);
        panel_completo.add(graficas);
        panel_completo.addMouseListener(new MouseAdapter() {// provides empty implementation of all
            // MouseListener`s methods, allowing us to
            // override only those which interests us
            @Override //I override only one method for presentation
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() + "," + e.getY());
            }
        });
        frame.add(panel_completo);

        frame.setSize( 1000, 1000 );
        frame.setLocation( 200, 200 );
        frame.setVisible( true );



    }

}
