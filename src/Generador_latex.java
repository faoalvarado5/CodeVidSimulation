import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Generador_latex {

    public static void main(String []args) throws FileNotFoundException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File archivo = fileChooser.getSelectedFile();
        String ruta = archivo.getAbsolutePath();

        JOptionPane.showMessageDialog(null, ruta);

        PrintWriter printWriter = new PrintWriter(archivo);
        printWriter.println("hola");
        printWriter.println("hola2");
        printWriter.println(" ");
        printWriter.println("hola2");
        printWriter.close();
    }

}
