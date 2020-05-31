import javax.swing.*;
import java.io.*;

public class Generador_latex {

    //public void generarLatex() throws IOException {
    public static void main(String[] args) {

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

                "\"" + "includegraphics{" + contents[0] + "}" + "\n" +

                "\"" + "newpage" + "\n" +
                "\"" + "section{Cambios en el mapa}" + "\n";

                for(int i=1; i<contents.length-1; i++) {

                    documento += "\"" + "includegraphics{" + contents[i] + "}" + "\n";
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
