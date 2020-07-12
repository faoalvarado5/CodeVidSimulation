package Classes.Threads;

import Classes.Server;
import Classes.agente;
import Classes.mapa;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class LogicaDeLaConexion extends Thread{

    ArrayList<agente> arreglo_de_los_agentes;
    Server server;
    int contador;
    mapa configuracion_del_mapa;

    public LogicaDeLaConexion(ArrayList<agente> arreglo_de_agentes, Server servidores, int contador, mapa configuracion_del_mapa){
        this.arreglo_de_los_agentes = arreglo_de_agentes;
        this.server = servidores;
        this.contador = contador;
        this.configuracion_del_mapa = configuracion_del_mapa;
    }

    public void listeningConnections(){

        for(int i = 0; i<server.getLista_de_puertos().size(); i++) {

        while (true) {
                try {
                    Socket socket = server.getServerSocket().accept();
                    ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
                    agente recvPacket = (agente) inStream.readObject();

                    if(recvPacket.getPosicion_en_eje_x() >= configuracion_del_mapa.getLargo()){
                        recvPacket.setPosicion_en_eje_x(configuracion_del_mapa.getLargo()-recvPacket.getVelocidad_maxima());
                    }

                    if(recvPacket.getPosicion_en_eje_y() >= configuracion_del_mapa.getAncho()){
                        recvPacket.setPosicion_en_eje_y(configuracion_del_mapa.getAncho()-recvPacket.getVelocidad_maxima());
                    }


                    arreglo_de_los_agentes.add(recvPacket);
                }catch(Exception exception){
                    System.out.println("---------------------------------------");
                    System.out.println(exception);
                    System.out.println("Error recibiendo agente");
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
