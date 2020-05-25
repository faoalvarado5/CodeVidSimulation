import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiMapa {

    JFrame f;

    GuiMapa(String configuracion_agente, String configuracion_mapa, String configuracion_enfermedad) {

        System.out.println(configuracion_agente);
        System.out.println(configuracion_mapa);
        System.out.println(configuracion_enfermedad);

        JFrame frame = new JFrame( "Drawing 2D shapes" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        JTabbedPane tabbedPane = new JTabbedPane();

        List<Integer> arreglo_de_curados = new ArrayList<>();
        List<Integer> arreglo_de_enfermos = new ArrayList<>();
        List<Integer> arreglo_de_sanos = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            arreglo_de_curados.add(i);
        }
        for (int i = 0; i < 20; i++) {
            arreglo_de_enfermos.add(i*2);
        }
        for (int i = 0; i < 20; i++) {
            arreglo_de_sanos.add(i+5);
        }

        tabbedPane.add("Grafica para total", new GraficaMultiple(arreglo_de_enfermos,arreglo_de_curados,arreglo_de_sanos));
        tabbedPane.add("Grafica para curados", new GraficaIndividual(arreglo_de_enfermos,0));
        tabbedPane.add("Grafica para enfermos", new GraficaIndividual(arreglo_de_curados,1));
        tabbedPane.add("Grafica para sanos", new GraficaIndividual(arreglo_de_sanos,2));
        frame.add( tabbedPane );
        frame.setSize( 1000, 1000 );
        frame.setLocation( 200, 200 );
        frame.setVisible( true );



    }

}
