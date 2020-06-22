package Classes;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Generador_latex {

    public void generarLatex(int totalAgentes, int totalDias, ArrayList<Integer> listaGrafico) throws IOException {

        File archivo = new File("latex/salida.tex");
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(archivo, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String contents[] = new File("latex/").list();

        // Este es el string para generar la portada del documento de salida.
        String documento = "\"" + "documentclass{report}" + "\n" +
                "\"" + "usepackage{graphicx}" + "\n" +
                "\"" + "usepackage{pgfplots}" + "\n" +
                "\"" + "begin{document}" + "\n" +
                "\"" + "begin{titlepage}" + "\n" +
                "\"" + "centering" + "\n" +
                "{\"" + "bfseries\\LARGE Instituto Tecnol\\'ogico de Costa Rica \\par}" + "\n" +
                "\"" + "vspace{1cm}" + "\n" +
                "{\"" + "scshape\\Large Facultad de Ingenier\\'ia en Computaci\\'on \\par}" + "\n" +
                "\"" + "vspace{3cm}" + "\n" +
                "{\"" + "scshape\\Huge Simulaci\\'on de propagaci\\'on \\\\" + "\n" +
                "de COVID-19\\par}" + "\n" +
                "\"" + "vspace{3cm}" + "\n" +
                "{\"" + "itshape\\Large Proyecto 2\\par}" + "\n" +
                "\"" + "vfill" + "\n" +
                "{\"" + "Large Emanuelle Jim\\'enez S.\\par}" + "\n" +
                "{\"" + "Large Fabrizio Alvarado B\\par}" + "\n" +
                "\"" + "vfill" + "\n" +
                "{\"" + "Large Junio 2020 \\par}" + "\n" +
                "\"" + "end{titlepage}" + "\n" +
                "\"" + "newpage" + "\n" +
                "\"" + "section{Gr\\'afico}" + "\n" +
                "\"" + "begin{tikzpicture}" + "\n" +
                "\"" + "begin{axis}[" + "\n" +
                "xlabel={D\\'ias}," + "\n" +
                "ylabel={Cantidad}," + "\n" +
                "xmin=0, xmax=" + totalDias + ","+ "\n" +
                "ymin=0, ymax=" + totalAgentes + "," + "\n" +
                "legend pos=north west," + "\n" +
                "ymajorgrids=true," + "\n" +
                "grid style=dashed," + "\n" +
                "]" + "\n" +
                "\"" + "addplot[" + "\n" +
                "color=red," + "\n" +
                "mark=square," + "\n" +
                "]" + "\n" +
                "coordinates {" + "\n";

        for (int i=0; i<listaGrafico.size(); i+=2) {

            documento += "(" + listaGrafico.get(i) + ", " + listaGrafico.get(i+1) + ")";
        }
        documento += "\n";
        documento += "};" + "\n" +
                "\"" + "legend{Infecci\\'ones}" + "\n" +
                "\"" + "end{axis}" + "\n" +
                "\"" + "end{tikzpicture}" + "\n" +
                "\"" + "newpage" + "\n" +
                "\"" + "section{Cambios en el mapa}" + "\n";
        for (int i = 1; i < contents.length - 1; i++) {

            documento += "\"" + "includegraphics[scale=0.20]{" + contents[i] + "}" + "\n";
        }

        documento += "\"" + "end{document}" + "\n";

        documento = documento.replaceAll("\"", "\\\\");
        try {
            fileWriter.write(documento);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Latex generado con exito.");
    }
}
