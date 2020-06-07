package Multimap;

import Classes.agente;
import SingleMap.GuiMenuSingle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientGui {

    JFrame f;

    JTextField name_of_the_country = new JTextField();
    JButton start_button = new JButton("Ready to start");//creating instance of JButton
    JLabel label = new JLabel("Connecting to server...");

    public ClientGui() {

        f = new JFrame("Simulacion de propagacion de COVID-19");//creating instance of JFrame

        name_of_the_country.setBounds(150, 50, 150, 40);

        start_button.setBounds(150, 200, 150, 40);
        label.setBounds(150, 100, 150, 40);

        start_button.addActionListener(new StartClientGui());

        f.add(name_of_the_country);
        f.add(start_button);
        f.add(label);

        f.setSize(500, 400);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);

    }

    class StartClientGui implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                Socket socket = new Socket("127.0.0.1",1234);

                ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

                outStream.writeObject(name_of_the_country.getText());
                String recvPacket = (String) inStream.readObject();

                int number_of_client = (int)inStream.readObject();

                System.out.println("My number is: " + number_of_client);

                label.setText(recvPacket);

                if(socket.isConnected()) new GuiMenuMulti();

            }catch (Exception error){
                System.out.println(error);
            }

        }
    }

}
