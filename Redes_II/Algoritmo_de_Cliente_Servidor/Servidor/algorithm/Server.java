/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Matricula........: 202011230
* Inicio...........: 12/05/2023
* Ultima alteracao.: 03/06/2023
* Nome.............: Server
* Funcao...........: Responsavel por iniciar e controlar o servidor
*************************************************************** */
package algorithm;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Server {
  public Server(){}

  private static String ip;
  private final static int portaLocal = 7763;

  private ServerSocket serverSocket;
  private static ArrayList<ClientThread> clients = new ArrayList<ClientThread>();

  
  public Server(ServerSocket serverSocket){
    this.serverSocket = serverSocket;
  }

  public void startServer() {
    Thread serverThread = new Thread(() -> {
      Socket socket;
      try {
        serverSocket = new ServerSocket(portaLocal);
        while (true) {
          System.out.println("Waiting for clients...");
          socket = serverSocket.accept();
          System.out.println("Connected");
          ClientThread clientThread = new ClientThread(socket, clients);
          clients.add(clientThread);
          clientThread.start();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    serverThread.start();
  }


  public static String showIpAddress(){
    Enumeration<NetworkInterface> interfaces;
    try {
      interfaces = NetworkInterface.getNetworkInterfaces();
      while (interfaces.hasMoreElements()) {
        NetworkInterface iface = interfaces.nextElement();
        if (iface.isLoopback() || !iface.isUp())
            continue;
  
        Enumeration<InetAddress> addresses = iface.getInetAddresses();
        while(addresses.hasMoreElements()) {
            InetAddress addr = addresses.nextElement();
            ip = addr.getHostAddress();
          }
          return ip;
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String getIpAdress(){
    return ip;
  }

  public static int getPortaLocal(){
    return portaLocal;
  }
}
