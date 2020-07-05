import Classes.agente;

import java.io.*;
import java.net.Socket;

public class SocketTestClient {

    public static void main(String []args){

        agente a = new agente();

        a.setVelocidad_maxima(3);
        a.setVelocidad_minima(6);
        a.setTipo(1);
        a.setEstado("e");
        a.setTiempo_enfermo(0);


        try {

            Socket socket = new Socket("localhost",1234);

            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

            outStream.writeObject(a);

            agente recvPacket = (agente) inStream.readObject();
            System.out.println(recvPacket.toString());


        }catch (Exception e){
            System.out.println(e);
        }

    }

}
