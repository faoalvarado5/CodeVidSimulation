import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class GraficaMultiple extends JPanel {

    private int padding_de_la_grafica = 25;
    private int padding_del_label = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color color_del_punto_curados = new Color(231, 225, 21, 180);
    private Color color_del_punto_enfermos = new Color(255, 0, 0, 180);
    private Color color_del_punto_sanos = new Color(21, 161, 0, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private int tamaño_del_punto = 10;
    private int cantidad_de_personas_en_la_prueba = 20;
    //Esta es la cantidad de divisiones que tendrá la gráfica en el eje X, dependera mucho de los dias
    private List<Integer> arreglo_de_curados = new ArrayList<>();;
    private List<Integer> arreglo_de_enfermos = new ArrayList<>();;
    private List<Integer> arreglo_de_sanos = new ArrayList<>();;

    public GraficaMultiple(List<Integer> arreglo_de_curados,List<Integer> arreglo_de_enfermos,List<Integer> arreglo_de_sanos) {

        this.arreglo_de_curados = arreglo_de_curados;
        this.arreglo_de_enfermos = arreglo_de_enfermos;
        this.arreglo_de_sanos = arreglo_de_sanos;

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

        // En esta se ponen los labels en el eje Y
        for (int i = 0; i < cantidad_de_personas_en_la_prueba + 1; i++) {
            int x0 = padding_de_la_grafica + padding_del_label;
            int x1 = tamaño_del_punto + padding_de_la_grafica + padding_del_label;
            int y0 = getHeight() - ((i * (getHeight() - padding_de_la_grafica * 2 - padding_del_label)) / cantidad_de_personas_en_la_prueba + padding_de_la_grafica + padding_del_label);
            int y1 = y0;
            if (arreglo_de_curados.size() > 0) {
                grafica.setColor(gridColor);
                grafica.drawLine(padding_de_la_grafica + padding_del_label + 1 + tamaño_del_punto, y0, getWidth() - padding_de_la_grafica, y1);
                grafica.setColor(Color.BLACK);
                // Aqui es donde se imprimen la cantidad de personas en la pantalla, eje Y
                String yLabel =  i + "";
                FontMetrics metrics = grafica.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                //Pone los labels en la pantalla
                grafica.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            grafica.drawLine(x0, y0, x1, y1);
        }



        // Esta es la parte bonita, aquí se ponen las lineas en la gráfica y los puntos
        agregarPuntosGrafica(grafica,arreglo_de_curados);
        agregarPuntosGrafica(grafica,arreglo_de_enfermos);
        agregarPuntosGrafica(grafica,arreglo_de_sanos);

        grafica.setColor(lineColor);

        agregarDatosGrafica(grafica,puntos_en_la_grafica_curados, 0);
        agregarDatosGrafica(grafica,puntos_en_la_grafica_enfermos,1);
        agregarDatosGrafica(grafica,puntos_en_la_grafica_sanos,2);

        // Dibuja un fondo blanco
        grafica.setColor(Color.WHITE);
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

    public void agregarPuntosGrafica(Graphics2D grafica, List<Integer> arreglo){
        // En esta se ponen los labels en el eje Y
        for (int i = 0; i < arreglo.size(); i++) {
            if (arreglo.size() > 1) {
                int x0 = i * (getWidth() - padding_de_la_grafica * 2 - padding_del_label) / (arreglo.size() - 1) + padding_de_la_grafica + padding_del_label;
                int x1 = x0;
                int y0 = getHeight() - padding_de_la_grafica - padding_del_label;
                int y1 = y0 - tamaño_del_punto;
                if ((i % ((int) ((arreglo.size() / 20.0)) + 1)) == 0) {
                    grafica.setColor(gridColor);
                    grafica.drawLine(x0, getHeight() - padding_de_la_grafica - padding_del_label - 1 - tamaño_del_punto, x1, padding_de_la_grafica);
                    grafica.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = grafica.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    grafica.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                grafica.drawLine(x0, y0, x1, y1);
            }
        }
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