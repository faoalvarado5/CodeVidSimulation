import javax.swing.*;
import java.io.*;

public class Generador_latex {

    public static void main(String []args) throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File archivo = fileChooser.getSelectedFile();

        FileWriter fileWriter = new FileWriter(archivo, false);

        String uno = "\"" + "t" + "hispagestyle{empty}";
        uno = uno.replaceAll("\"","\\\\");
        fileWriter.write(uno);

        fileWriter.write(System.getProperty( "line.separator" ));

        String dos = "\"" + "b" + "egin{center}";
        dos = dos.replaceAll("\"","\\\\");
        fileWriter.write(dos);

        fileWriter.close();
    }

}
