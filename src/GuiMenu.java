import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuiMenu {

    JFrame f;
    String ruta_agentes;
    String ruta_mapa;
    String ruta_enfermedad;
    agente agentes[][];
    mapa mapa;
    enfermedad enfermedad;

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
                 mapa mapa = new mapa();
                 try {

                     Scanner myReader = new Scanner(archivo);

                     //Aqui leo la primera linea donde vienen las dimenciones del mapa
                     String line1 = myReader.nextLine();
                     String[] dimenciones = line1.split(" ");
                     mapa.setAncho(Integer.parseInt(dimenciones[0]));
                     mapa.setLargo(Integer.parseInt(dimenciones[1]));

                     if(myReader.hasNextLine()) {

                         //Aqui leo la cantidad de paredes que tiene el mapa.
                         String line2 = myReader.nextLine();
                         int paredes = Integer.parseInt(line2);

                         while (paredes != 0) {

                             //Aqui leo y asigno las dimenciones de las paredes hasta que ya no hayan mas.
                             String linex = myReader.nextLine();
                             String[] dimenciones2 = linex.split(" ");
                             pared pared = new pared(Integer.parseInt(dimenciones2[0]), Integer.parseInt(dimenciones2[1]), Integer.parseInt(dimenciones2[2]), Integer.parseInt(dimenciones2[3]));
                             mapa.addPared(pared);
                             paredes -= 1;
                         }
                         System.out.println(mapa.toString());
                     }

                 } catch(FileNotFoundException err) {
                     JOptionPane.showMessageDialog(null, err);
                 }
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
            if(ruta_agentes == null || ruta_mapa == null || ruta_enfermedad == null){
                JOptionPane.showMessageDialog(null, "Error al cargar archivos, intentelo nuevamente.");
            }else{

                new GuiMapa(ruta_agentes,ruta_mapa,ruta_enfermedad);

            }
        }
    }
}
