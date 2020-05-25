import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GuiMenu {

    JFrame f;
    String ruta_agentes;
    String ruta_mapa;
    String ruta_enfermedad;


    GuiMenu() {

        f = new JFrame();//creating instance of JFrame

        JButton boton_agentes = new JButton("Cargar agentes");//creating instance of JButton
        JButton boton_mapa = new JButton("Cargar mapa");//creating instance of JButton
        JButton boton_enferdad = new JButton("Cargar enfermedad");//creating instance of JButton
        JButton boton_comenzar = new JButton("Comenzar");//creating instance of JButton

        boton_agentes.setBounds(70, 100, 150, 40);
        boton_mapa.setBounds(70, 150, 150, 40);
        boton_enferdad.setBounds(70, 200, 150, 40);
        boton_comenzar.setBounds(70, 250, 150, 40);

        boton_agentes.addActionListener(new configuracion_agente());
        boton_mapa.addActionListener(new configuracion_mapa());
        boton_enferdad.addActionListener(new configuracion_enfermedad());
        boton_comenzar.addActionListener(new comenzar_prueba());


        f.add(boton_agentes);//adding button in JFrame
        f.add(boton_mapa);//adding button in JFrame
        f.add(boton_enferdad);//adding button in JFrame
        f.add(boton_comenzar);

        f.setSize(300, 400);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);

    }

    class configuracion_agente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            File archivo = fileChooser.getSelectedFile();
            if(archivo != null){
                ruta_agentes = archivo.getAbsolutePath();
            }
        }
    }

    class configuracion_mapa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            File archivo = fileChooser.getSelectedFile();
            if(archivo != null){
                 ruta_mapa = archivo.getAbsolutePath();
            }
        }
    }

    class configuracion_enfermedad implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            File archivo = fileChooser.getSelectedFile();
            if(archivo != null){
                ruta_enfermedad = archivo.getAbsolutePath();
            }
        }
    }

    class comenzar_prueba implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(ruta_agentes);
            System.out.println(ruta_mapa);
            System.out.println(ruta_enfermedad);
            if(ruta_agentes == null || ruta_mapa == null || ruta_enfermedad == null){
                JOptionPane.showMessageDialog(null, "Las rutas no pueden ser vacías, revíselas nuevamente");
            }else{

                new GuiMapa(ruta_agentes,ruta_mapa,ruta_enfermedad);

            }
        }
    }
}
