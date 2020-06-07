import Classes.agente;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTestServer {

    public static void main(String []args){

        agente a = new agente();

        a.setVelocidad_maxima(4);
        a.setVelocidad_minima(7);
        a.setTipo(2);
        a.setEstado("s");
        a.setTiempo_enfermo(2);

        try {

            ServerSocket serverSocket = new ServerSocket(1234);

            while (true) {

                Socket socket = serverSocket.accept();

                ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

                agente recvPacket = (agente) inStream.readObject();
                System.out.println(recvPacket.toString());

                outStream.writeObject(a);

            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

}
