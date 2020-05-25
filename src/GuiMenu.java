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

                 } catch(Exception err) {
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
                enfermedad enfermedad = new enfermedad();

                try{

                    Scanner myReader = new Scanner(archivo);

                    //Aqui leo la primera linea donde viene la probabilidad de muerte.
                    String line1 = myReader.nextLine();
                    enfermedad.setProbabilidad_muerte(Float.parseFloat(line1));

                    //Aqui leo la segunda linea donde viene la cantidad de segundos para morir.
                    String line2 = myReader.nextLine();
                    enfermedad.setDias_de_muerte(Integer.parseInt(line2));

                    //Aqui leo la tercera linea donde viene la cantidad de segundos para curarse.
                    String line3 = myReader.nextLine();
                    enfermedad.setDias_de_recuperacion(Integer.parseInt(line3));

                    ArrayList<ArrayList<Float>> matriz = new ArrayList<ArrayList<Float>>();

                    for (int x=0; x<4; x++) {

                        //Aqui leo y asigno las probabilidades de contagio segun cada individuo.
                        String linex = myReader.nextLine();
                        String[] probabilidadesx = linex.split(" ");
                        ArrayList<Float> arrayList = new ArrayList<Float>();

                        arrayList.add(Float.parseFloat(probabilidadesx[0]));
                        arrayList.add(Float.parseFloat(probabilidadesx[1]));
                        arrayList.add(Float.parseFloat(probabilidadesx[2]));
                        arrayList.add(Float.parseFloat(probabilidadesx[3]));
                        matriz.add(arrayList);
                    }
                    enfermedad.setMatriz_de_cotagio(matriz);

                    //Aqui leo la ultima linea donde indica si hay posibilidad de reinfeccion.
                    String lastLine = myReader.nextLine();
                    enfermedad.setReinfeccion(Integer.parseInt(lastLine));

                    enfermedad.setDias_corriendo(0);
                    enfermedad.setCantidad_enfermos_actuales(0);
                    enfermedad.setCantidad_recuperados_actuales(0);
                    enfermedad.setCantidad_sanos_actuales(0);
                    enfermedad.setDias_totales(Integer.MAX_VALUE);

                    System.out.println(enfermedad.toString());

                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err);
                }
            }
        }
    }

    class comenzar_prueba implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

                new GuiMapa(ruta_agentes,ruta_mapa,ruta_enfermedad);


        }
    }
}
