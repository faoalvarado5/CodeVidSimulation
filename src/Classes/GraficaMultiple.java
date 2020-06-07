package Classes;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class GraficaMultiple extends JPanel {

    private int padding_de_la_grafica = 25;
    private int padding_del_label = 25;
    private Color lineColor = new Color(224, 181, 28, 180);
    private Color color_del_punto_curados = new Color(21, 56, 231, 180);
    private Color color_del_punto_enfermos = new Color(255, 0, 0, 180);
    private Color color_del_punto_sanos = new Color(21, 161, 0, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private int tamaño_del_punto = 10;
    private int cantidad_de_personas_en_la_prueba;
    //Esta es la cantidad de divisiones que tendrá la gráfica en el eje X, dependera mucho de los dias
    private ArrayList<Integer> arreglo_de_curados = new ArrayList<>();;
    private ArrayList<Integer> arreglo_de_enfermos = new ArrayList<>();;
    private ArrayList<Integer> arreglo_de_sanos = new ArrayList<>();;
    public int dias_totales;

    public GraficaMultiple(ArrayList<Integer> arreglo_de_curados,ArrayList<Integer> arreglo_de_enfermos,ArrayList<Integer> arreglo_de_sanos, int cantidad_de_personas_en_la_prueba, int dias_totales) {

        this.arreglo_de_curados = arreglo_de_curados;
        this.arreglo_de_enfermos = arreglo_de_enfermos;
        this.arreglo_de_sanos = arreglo_de_sanos;
        this.cantidad_de_personas_en_la_prueba = cantidad_de_personas_en_la_prueba;
        this.dias_totales = dias_totales;
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent( g );

        Graphics2D grafica = (Graphics2D) g;

        grafica.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Este loop lo que permite es meter los puntos de la gráfica
        // En este caso [1,2,6,8] existira una linea que vaya del (1,2) al (6,8)
        List<Point> puntos_en_la_grafica_curados = agregarPuntos(arreglo_de_curados);
        List<Point> puntos_en_la_grafica_enfermos = agregarPuntos(arreglo_de_enfermos);
        List<Point> puntos_en_la_grafica_sanos = agregarPuntos(arreglo_de_sanos);

        double cantidad_de_lineas = (double)obtenerMaximoValor() / 20;
        double temp = cantidad_de_lineas;

        for (int i = 0; i <= 20; i++){
            int x0 = padding_de_la_grafica + padding_del_label;
            int x1 = tamaño_del_punto + padding_de_la_grafica + padding_del_label;
            int y0 = getHeight() - ((i * (int)cantidad_de_personas_en_la_prueba / 20 * (getHeight() - padding_de_la_grafica * 2 - padding_del_label)) / cantidad_de_personas_en_la_prueba + padding_de_la_grafica + padding_del_label);
            int y1 = y0;

            grafica.setColor(gridColor);
            grafica.drawLine(padding_de_la_grafica + padding_del_label + 1 + tamaño_del_punto, y0, getWidth() - padding_de_la_grafica, y1);
            grafica.setColor(Color.BLACK);
            // Aqui es donde se imprimen la cantidad de personas en la pantalla, eje Y

            double valor_del_label = i * temp;
            valor_del_label = new BigDecimal(valor_del_label).setScale(2, RoundingMode.HALF_UP).doubleValue();
            String yLabel = valor_del_label + "";

            FontMetrics metrics = grafica.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            //Pone los labels en la pantalla
            grafica.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            grafica.drawLine(x0, y0, x1, y1);

        }

        grafica.setColor(lineColor);

        agregarDatosGrafica(grafica,puntos_en_la_grafica_curados, 0);
        agregarDatosGrafica(grafica,puntos_en_la_grafica_enfermos,1);
        agregarDatosGrafica(grafica,puntos_en_la_grafica_sanos,2);

        // Dibuja un fondo blanco
        grafica.setColor(Color.WHITE);

        grafica.setColor(Color.BLACK);
        int x0 = getWidth()/2;
        int y0 = getHeight()-10;
        grafica.drawString("Dias totales: " + dias_totales + " Dias corriendo: " + (int)(arreglo_de_curados.size()/100), x0 , y0);



    }

    private void agregarDatosGrafica(Graphics2D grafica, List<Point> puntos, int color){
        for (int i = 0; i < puntos.size() - 1; i++) {
            int x1 = puntos.get(i).x;
            int y1 = puntos.get(i).y;
            int x2 = puntos.get(i + 1).x;
            int y2 = puntos.get(i + 1).y;
            grafica.drawLine(x1, y1, x2, y2);
        }

        if(color == 1){
            grafica.setColor(color_del_punto_enfermos);
        }else if(color == 2){
            grafica.setColor(color_del_punto_sanos);
        }else{
            grafica.setColor(color_del_punto_curados);
        }

        for (int i = 0; i < puntos.size(); i++) {
            int x = puntos.get(i).x - tamaño_del_punto / 2;
            int y = puntos.get(i).y - tamaño_del_punto / 2;
            int ovalW = tamaño_del_punto;
            int ovalH = tamaño_del_punto;
            grafica.fillOval(x, y, ovalW, ovalH);

        }
    }

    private List<Point> agregarPuntos(List<Integer> arreglo){
        List<Point> puntos = new ArrayList<>();
        // Esto viene siendo tamaño de la ventana - 2 (padding de la gráfica) - padding del texto / tamaño de puntos que
        // hay en la grafica - 1. Esto viene siendo para el ancho
        double xScale = ((double) getWidth() - (2 * padding_de_la_grafica) - padding_del_label) / (arreglo.size() - 1);
        // Se divide por el max y min score porque limita el largo de la gráfica
        double yScale = ((double) getHeight() - 2 * padding_de_la_grafica - padding_del_label) / (obtenerMaximoValor() - obtenerMinimoValor());

        for (int i = 0; i < arreglo.size(); i++) {
            int x1 = (int) (i * xScale + padding_de_la_grafica + padding_del_label);
            int y1 = (int) ((obtenerMaximoValor() - arreglo.get(i)) * yScale + padding_de_la_grafica);
            puntos.add(new Point(x1, y1));
            //System.out.println("X: " + Integer.toString(x1) + " Y: " + Integer.toString(y1));
        }
        return puntos;
    }

    private double obtenerMinimoValor() {
        double minScore = Double.MAX_VALUE;
        for (Integer score : arreglo_de_curados) {
            minScore = Math.min(minScore, score);
        }
        for (Integer score : arreglo_de_enfermos) {
            minScore = Math.min(minScore, score);
        }
        for (Integer score : arreglo_de_sanos) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double obtenerMaximoValor() {
        double maxScore = Double.MIN_VALUE;
        for (Integer score : arreglo_de_curados) {
            maxScore = Math.max(maxScore, score);
        }
        for (Integer score : arreglo_de_enfermos) {
            maxScore = Math.max(maxScore, score);
        }
        for (Integer score : arreglo_de_sanos) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }
}