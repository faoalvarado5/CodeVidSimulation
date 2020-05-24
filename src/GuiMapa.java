import javax.swing.*;

public class GuiMapa {

    JFrame f;

    GuiMapa(String configuracion_agente, String configuracion_mapa, String configuracion_enfermedad) {

        System.out.println(configuracion_agente);
        System.out.println(configuracion_mapa);
        System.out.println(configuracion_enfermedad);

        f = new JFrame();//creating instance of JFrame
        f.setSize(300, 400);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);

    }

}
