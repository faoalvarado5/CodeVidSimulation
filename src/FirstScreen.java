import Multimap.ClientGui;
import Multimap.ServerGui;
import SingleMap.MainGuiFrameSingle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Multimap.GuiMenuMulti;
import SingleMap.GuiMenuSingle;

public class FirstScreen {

    JFrame f;

    JButton singleplayer = new JButton("Single player");//creating instance of JButton
    JButton serverplayer = new JButton("Server player");//creating instance of JButton
    JButton clientplayer = new JButton("Client player");//creating instance of JButton

    FirstScreen() {

        f = new JFrame("Simulacion de propagacion de COVID-19");//creating instance of JFrame

        singleplayer.setBounds(70, 100, 150, 40);
        serverplayer.setBounds(70, 150, 150, 40);
        clientplayer.setBounds(70, 200, 150, 40);

        singleplayer.addActionListener(new SinglePlayerMode());
        serverplayer.addActionListener(new ServerPlayerMode());
        clientplayer.addActionListener(new ClientPlayerMode());

        f.add(serverplayer);
        f.add(clientplayer);
        f.add(singleplayer);//adding button in JFrame

        f.setSize(300, 400);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);
    }

    class SinglePlayerMode implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            f.dispose();
            new GuiMenuSingle();
        }
    }

    class ServerPlayerMode implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ServerGui();
        }
    }

    class ClientPlayerMode implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ClientGui();
        }
    }

}
