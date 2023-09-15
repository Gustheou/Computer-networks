/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Matricula........: 202011230
* Inicio...........: 22/05/2023
* Ultima alteracao.: 03/06/2023
* Nome.............: ClientThread
* Funcao...........: Gerenciar as mensagens enviadas pelos clientes
*************************************************************** */
package algorithm;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread {
  private Socket serverSocket;
  private BufferedReader entrada;
  private PrintWriter saida;
  public ArrayList<ClientThread> clientsThreads = new ArrayList<>();

  public ClientThread(){}

  public ClientThread(Socket socket, ArrayList<ClientThread> clientsThreads){
    try {
      this.serverSocket = socket;
      this.saida = new PrintWriter(socket.getOutputStream(),true);
      this.entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      this.clientsThreads = clientsThreads;
    } catch (IOException e) {
      closeServerSocket(serverSocket, entrada, saida);
    }
  }

  @Override
  public void run(){
    try {
      String msg;
      while ((msg = entrada.readLine()) != null) {
        if (msg.equalsIgnoreCase("exit")) {
          break;
        }
        for (ClientThread cl : clientsThreads) {
          cl.saida.println(msg);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeServerSocket(serverSocket, entrada, saida);
    }
  }

  public void closeServerSocket(Socket serverSocket, BufferedReader bufferedReader, PrintWriter PrintWriter) {
    try {
      if (entrada != null){
        entrada.close();
      }
      if (saida != null){
        saida.close();
      }
      if (serverSocket != null){
        serverSocket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
