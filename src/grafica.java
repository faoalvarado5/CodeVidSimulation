import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author "Hovercraft Full of Eels", "Rodrigo Azevedo"
 *
 * This is an improved version of Hovercraft Full of Eels (https://stackoverflow.com/users/522444/hovercraft-full-of-eels)
 * answer on StackOverflow: https://stackoverflow.com/a/8693635/753012
 *
 * GitHub user @maritaria has made some performance improvements which can be found in the comment section of this Gist.
 */
public class grafica extends JPanel {
    private int width = 800;
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
    private List<Integer> scores;

    public grafica(List<Integer> scores) {
        this.scores = scores;
    }

    @Override
    protected void paintComponent(Graphics g) {


        Graphics2D grafica = (Graphics2D) g;

        // Esto viene siendo tamaño de la ventana - 2 (padding de la gráfica) - padding del texto / tamaño de puntos que
        // hay en la grafica - 1. Esto viene siendo para el ancho
        double xScale = ((double) getWidth() - (2 * padding_de_la_grafica) - padding_del_label) / (scores.size() - 1);
        // Se divide por el max y min score porque limita el largo de la gráfica
        double yScale = ((double) getHeight() - 2 * padding_de_la_grafica - padding_del_label) / (getMaxScore() - getMinScore());

        // Este loop lo que permite es meter los puntos de la gráfica
        // En este caso [1,2,6,8] existira una linea que vaya del (1,2) al (6,8)
        List<Point> puntos_en_la_grafica = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding_de_la_grafica + padding_del_label);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding_de_la_grafica);
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
            if (scores.size() > 0) {
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
        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding_de_la_grafica * 2 - padding_del_label) / (scores.size() - 1) + padding_de_la_grafica + padding_del_label;
                int x1 = x0;
                int y0 = getHeight() - padding_de_la_grafica - padding_del_label;
                int y1 = y0 - tamaño_del_punto;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
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
    }

//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(width, height);
//    }

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Integer score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Integer score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    private static void createAndShowGui() {

        // Este debería ser la cantidad de dias
        List<Integer> dias_del_virus_para_la_prueba = new ArrayList<>();
        // Aqui van los puntos en el eje X
        for (int i = 0; i < 20; i++) {
            dias_del_virus_para_la_prueba.add(i);
        }

        grafica mainPanel = new grafica(dias_del_virus_para_la_prueba);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}