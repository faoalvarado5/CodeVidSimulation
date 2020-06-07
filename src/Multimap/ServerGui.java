package Multimap;

import Classes.agente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ServerGui {

    JFrame f;

    JButton start_button = new JButton("Ready to start");//creating instance of JButton
    JLabel label = new JLabel("Looking for clients...");
    JLabel label_of_countries_connected = new JLabel("");
    int number_of_clients_connected = 1;

    public ServerGui() {

        f = new JFrame("Simulacion de propagacion de COVID-19");//creating instance of JFrame

        start_button.setBounds(150, 200, 150, 40);
        label.setBounds(150, 100, 150, 40);
        label_of_countries_connected.setBounds(150, 50, 150, 40);

        start_button.addActionListener(new StartServerGui());

        f.add(label_of_countries_connected);
        f.add(start_button);
        f.add(label);

        f.setSize(500, 400);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        f.setLocationRelativeTo(null);

    }


    class StartServerGui implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        ServerSocket serverSocket = new ServerSocket(1234);

                        int contador = 0;

                        while (contador < 5) {

                            TimeUnit.SECONDS.sleep(1);
                            contador++;
                            label.setText("Looking for clients... " + contador);
                            Socket socket = serverSocket.accept();

                            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

                            if(socket.isConnected()){
                                String recvPacket = (String) inStream.readObject();
                                label_of_countries_connected.setText(label_of_countries_connected.getText() + ", " + recvPacket);

                                String msg = "Hi you are the number " + number_of_clients_connected;
                                outStream.writeObject(msg);
                                number_of_clients_connected++;

                                outStream.writeObject(number_of_clients_connected);
                                start_button.setEnabled(true);
                            }


                        }
                    }catch (Exception error){
                        JOptionPane.showMessageDialog(null, "Something went wrong...");
                    }
                }
            });

            thread.start();

        }
    }

}
