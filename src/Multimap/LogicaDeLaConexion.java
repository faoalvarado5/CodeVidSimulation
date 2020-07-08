package Multimap;

import Classes.Server;
import Classes.agente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class LogicaDeLaConexion extends Thread{

    ArrayList<agente> arreglo_de_los_agentes;
    Server server;
    int contador;

    public LogicaDeLaConexion(ArrayList<agente> arreglo_de_agentes, Server servidores, int contador){
        this.arreglo_de_los_agentes = arreglo_de_agentes;
        this.server = servidores;
        this.contador = contador;
    }

    public void listeningConnections(){

        for(int i = 0; i<server.getLista_de_puertos().size(); i++) {

        while (true) {
                try {
                    System.out.println("recibiendo...");
                    Socket socket = server.getServerSocket().accept();
                    ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                    agente recvPacket = (agente) inStream.readObject();
                    arreglo_de_los_agentes.add(recvPacket);
                }catch(Exception exception){
                    System.out.println("---------------------------------------");
                    System.out.println(exception);
                    System.out.println("Error recibiendo agente");
                    System.out.println(server.getServerSocket().toString());
                    System.out.println("---------------------------------------");
                }
            }
        }

    }

    public ArrayList<agente> getArreglo_de_los_agentes(){
        return arreglo_de_los_agentes;
    }

    @Override
    public void run(){
        listeningConnections();
    }

}
