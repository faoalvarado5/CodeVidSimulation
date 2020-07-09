package Multimap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class LatexThread extends Thread{

    Frame f;
    int contador_imagenes;

    public LatexThread(Frame f, int contador_imagenes){
        this.f = f;
        this.contador_imagenes = contador_imagenes;
    }

    public void latexFunction(){
        try {
            BufferedImage image = new BufferedImage(f.getWidth(), f.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = image.createGraphics();
            f.paint(graphics2D);
            File file = new File("latex/");
            //Creating the directory
            file.mkdir();
            ImageIO.write(image, "jpeg", new File("latex/" + contador_imagenes + ".jpeg"));
            //Thread.sleep(1000);
        }catch(Exception e){

        }
    }
    @Override
    public void run(){
        latexFunction();
    }

}
