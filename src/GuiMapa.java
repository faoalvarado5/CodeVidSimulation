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

        graficas.add("Grafica para total", new GraficaMultiple(datos_progresivos_de_la_enfermedad.getCantidad_de_curados(), datos_progresivos_de_la_enfermedad.getCantidad_de_enfermos(),
                datos_progresivos_de_la_enfermedad.getCantidad_de_sanos(), arreglo_de_agentes.size()));
        graficas.add("Grafica para curados", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_curados(),0, arreglo_de_agentes.size()));
        graficas.add("Grafica para enfermos", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_enfermos(),1, arreglo_de_agentes.size()));
        graficas.add("Grafica para sanos", new GraficaIndividual(datos_progresivos_de_la_enfermedad.getCantidad_de_sanos(),2, arreglo_de_agentes.size()));

        JLabel dias_corriendo = new JLabel("Los dias que han transcurrido son: " + datos_progresivos_de_la_enfermedad.getDias());

        mapas.add("Costa Rica",  new GuiMapaPane(configuracion_enfermedad, arreglo_de_agentes, configuracion_mapa, datos_progresivos_de_la_enfermedad, frame, dias_corriendo));

        mapas.setPreferredSize( new Dimension(configuracion_mapa.getAncho(), configuracion_mapa.getLargo()));
        graficas.setPreferredSize( new Dimension(400, 700));


        System.out.println(datos_progresivos_de_la_enfermedad.getDias());

        JPanel panel_completo = new JPanel();
        JPanel panel_de_texto = new JPanel();
        panel_de_texto.add(dias_corriendo);

        panel_completo.add(panel_de_texto);
        panel_completo.add(mapas);
        panel_completo.add(graficas);

        frame.add(panel_completo);

        frame.setSize( 1000, 1000 );
        frame.setLocation( 200, 200 );
        frame.setVisible( true );

    }

    public void funciones_de_las_graficas(){

    }

}
