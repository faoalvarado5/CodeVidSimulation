package Classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    ArrayList<String> lista_de_ips;
    ArrayList<Integer> lista_de_puertos;
    Double probabilidad_de_visita;
    int tiempo_para_lanzar_probabilidad;
    int tiempo_de_agente_en_la_computadora;
    ServerSocket serverSocket;   // Recibe
                                 // Input
    ArrayList<Socket> lista_de_computadoras;    // Enviar informacion
                                                // Output

    public Server(){
        this.lista_de_ips = new ArrayList<>();
        this.lista_de_puertos = new ArrayList<>();
        this.lista_de_computadoras = new ArrayList<>();
        this.probabilidad_de_visita = 0.0;
        this.tiempo_de_agente_en_la_computadora = 0;
        this.tiempo_para_lanzar_probabilidad = 0;
        this.serverSocket = null;

    }

    public Server(ArrayList<String> lista_de_ips, ArrayList<Integer> lista_de_puertos, Double probabilidad_de_visita, int tiempo_para_lanzar_probabilidad,
                  int tiempo_de_agente_en_la_computadora, int puerto, ArrayList<Socket> lista_de_computadoras) throws IOException {

        this.lista_de_ips = new ArrayList<>();
        this.lista_de_puertos = new ArrayList<>();
        this.probabilidad_de_visita = probabilidad_de_visita;
        this.tiempo_para_lanzar_probabilidad = tiempo_para_lanzar_probabilidad;
        this.tiempo_de_agente_en_la_computadora = tiempo_de_agente_en_la_computadora;
        this.serverSocket = new ServerSocket(puerto);
        this.lista_de_computadoras = new ArrayList<>();

    }

    public void addElementToListOfIps(String a){
        lista_de_ips.add(a);
    }
    public void addElementToListOfPorts(Integer a){
        lista_de_puertos.add(a);
    }
    public void addElementToListOfComputers(Socket a){
        lista_de_computadoras.add(a);
    }
    public boolean available(String ip, int port) {

        if(serverSocket == null) {
            try {
                Socket ignored = new Socket(ip, port);
                return false;
            } catch (IOException ignored) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getLista_de_ips() {
        return lista_de_ips;
    }

    public void setLista_de_ips(ArrayList<String> lista_de_ips) {
        this.lista_de_ips = lista_de_ips;
    }

    public ArrayList<Integer> getLista_de_puertos() {
        return lista_de_puertos;
    }

    public void setLista_de_puertos(ArrayList<Integer> lista_de_puertos) {
        this.lista_de_puertos = lista_de_puertos;
    }

    public Double getProbabilidad_de_visita() {
        return probabilidad_de_visita;
    }

    public void setProbabilidad_de_visita(Double probabilidad_de_visita) {
        this.probabilidad_de_visita = probabilidad_de_visita;
    }

    public int getTiempo_para_lanzar_probabilidad() {
        return tiempo_para_lanzar_probabilidad;
    }

    public void setTiempo_para_lanzar_probabilidad(int tiempo_para_lanzar_probabilidad) {
        this.tiempo_para_lanzar_probabilidad = tiempo_para_lanzar_probabilidad;
    }

    public int getTiempo_de_agente_en_la_computadora() {
        return tiempo_de_agente_en_la_computadora;
    }

    public void setTiempo_de_agente_en_la_computadora(int tiempo_de_agente_en_la_computadora) {
        this.tiempo_de_agente_en_la_computadora = tiempo_de_agente_en_la_computadora;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public ArrayList<Socket> getLista_de_computadoras() {
        return lista_de_computadoras;
    }

    public void setLista_de_computadoras(ArrayList<Socket> lista_de_computadoras) {
        this.lista_de_computadoras = lista_de_computadoras;
    }

    @Override
    public String toString() {
        return "Server{" +
                "lista_de_ips=" + lista_de_ips +
                ", lista_de_puertos=" + lista_de_puertos +
                ", probabilidad_de_visita=" + probabilidad_de_visita +
                ", tiempo_para_lanzar_probabilidad=" + tiempo_para_lanzar_probabilidad +
                ", tiempo_de_agente_en_la_computadora=" + tiempo_de_agente_en_la_computadora +
                ", serverSocket=" + serverSocket +
                ", lista_de_computadoras=" + lista_de_computadoras +
                '}';
    }
}
