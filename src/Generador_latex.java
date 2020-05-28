import javax.swing.*;
import java.io.*;

public class Generador_latex {

    public void generarLatex() throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File archivo = fileChooser.getSelectedFile();

        FileWriter fileWriter = new FileWriter(archivo, false);

        // Este es el string para generar la portada del documento de salida.
        String portada = "\"" + "documentclass{report}" + "\n" +
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

                "afgassfagdgh" + "\n" +

                "\"" + "newpage" + "\n" +
                "\"" + "section{Cambios en el mapa}" + "\n" +

                "Gsgsgsgsgsgsgs" + "\n" +

                "\"" + "end{document}" + "\n";

        portada = portada.replaceAll("\"","\\\\");
        fileWriter.write(portada);
        fileWriter.close();
        JOptionPane.showMessageDialog(null, "Latex generado con exito.");
    }

}
