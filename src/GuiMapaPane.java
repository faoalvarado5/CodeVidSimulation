import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class GuiMapaPane extends JPanel implements ActionListener {

    Timer t = new Timer(5, this);
    double x = 0, y = 0, velX = 3, velY = 3;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D mapa = (Graphics2D) g;

        Ellipse2D persona = new Ellipse2D.Double(x,y,10,10);
        mapa.fill(persona);


        t.start();
    }
    public void actionPerformed(ActionEvent e){

        if(x < 0 || x > 370) velX = -velX;
        if(y < 0 || y > 670) velY = -velY;
        x += velX;
        y += velY;
        repaint();
    }

}
