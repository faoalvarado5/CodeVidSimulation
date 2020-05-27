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

        graficas.add("Grafica para total", new GraficaMultiple(arreglo_de_enfermos,arreglo_de_curados,arreglo_de_sanos));
        graficas.add("Grafica para curados", new GraficaIndividual(arreglo_de_enfermos,0));
        graficas.add("Grafica para enfermos", new GraficaIndividual(arreglo_de_curados,1));
        graficas.add("Grafica para sanos", new GraficaIndividual(arreglo_de_sanos,2));

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
