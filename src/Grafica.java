import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class Grafica extends JPanel {

    JFrame f;

    private int width = 700;
    private int height = 400;
    private int padding_de_la_grafica = 25;
    private int padding_del_label = 25;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int tamaño_del_punto = 10;
    private int cantidad_de_personas_en_la_prueba = 20;
    //Esta es la cantidad de divisiones que tendrá la gráfica en el eje X, dependera mucho de los dias
    private int numberYDivisions = 20;
    private List<Integer> arreglo_de_curados;
    private List<Integer> arreglo_de_enfermos;
    private List<Integer> arreglo_de_sanos;
    private Graphics2D grafi = null;

    public Grafica(List<Integer> arreglo_de_curados,List<Integer> arreglo_de_enfermos,List<Integer> arreglo_de_sanos) {

        this.arreglo_de_curados = arreglo_de_curados;
        this.arreglo_de_enfermos = arreglo_de_enfermos;
        this.arreglo_de_sanos = arreglo_de_sanos;

    }

    protected void paintComponent(Graphics g) {

        super.paintComponent( g );
        Graphics2D grafica = (Graphics2D) g;

        grafica.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Esto viene siendo tamaño de la ventana - 2 (padding de la gráfica) - padding del texto / tamaño de puntos que
        // hay en la grafica - 1. Esto viene siendo para el ancho
        double xScale = ((double) getWidth() - (2 * padding_de_la_grafica) - padding_del_label) / (arreglo_de_curados.size() - 1);
        // Se divide por el max y min score porque limita el largo de la gráfica
        double yScale = ((double) getHeight() - 2 * padding_de_la_grafica - padding_del_label) / (getMaxScore() - getMinScore());

        // Este loop lo que permite es meter los puntos de la gráfica
        // En este caso [1,2,6,8] existira una linea que vaya del (1,2) al (6,8)
        List<Point> puntos_en_la_grafica = new ArrayList<>();
        for (int i = 0; i < arreglo_de_curados.size(); i++) {
            int x1 = (int) (i * xScale + padding_de_la_grafica + padding_del_label);
            int y1 = (int) ((getMaxScore() - arreglo_de_curados.get(i)) * yScale + padding_de_la_grafica);
            puntos_en_la_grafica.add(new Point(x1, y1));
        }

        // Dibuja un fondo blanco
        grafica.setColor(Color.WHITE);

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

        // En esta se ponen los labels en el eje Y
        for (int i = 0; i < arreglo_de_curados.size(); i++) {
            if (arreglo_de_curados.size() > 1) {
                int x0 = i * (getWidth() - padding_de_la_grafica * 2 - padding_del_label) / (arreglo_de_curados.size() - 1) + padding_de_la_grafica + padding_del_label;
                int x1 = x0;
                int y0 = getHeight() - padding_de_la_grafica - padding_del_label;
                int y1 = y0 - tamaño_del_punto;
                if ((i % ((int) ((arreglo_de_curados.size() / 20.0)) + 1)) == 0) {
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

        // Esta es la parte bonita, aquí se ponen las lineas en la gráfica y los puntos

        grafica.setColor(lineColor);
        for (int i = 0; i < puntos_en_la_grafica.size() - 1; i++) {
            int x1 = puntos_en_la_grafica.get(i).x;
            int y1 = puntos_en_la_grafica.get(i).y;
            int x2 = puntos_en_la_grafica.get(i + 1).x;
            int y2 = puntos_en_la_grafica.get(i + 1).y;
            grafica.drawLine(x1, y1, x2, y2);
        }

        grafica.setColor(pointColor);
        for (int i = 0; i < puntos_en_la_grafica.size(); i++) {
            int x = puntos_en_la_grafica.get(i).x - tamaño_del_punto / 2;
            int y = puntos_en_la_grafica.get(i).y - tamaño_del_punto / 2;
            int ovalW = tamaño_del_punto;
            int ovalH = tamaño_del_punto;
            grafica.fillOval(x, y, ovalW, ovalH);
        }

        grafi = grafica;

    }

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Integer score : arreglo_de_curados) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Integer score : arreglo_de_curados) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }
}