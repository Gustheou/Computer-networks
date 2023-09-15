/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Matricula........: 202011230
* Inicio...........: 19/05/2023
* Ultima alteracao.: 03/06/2023
* Nome.............: ControleTelaServidor
* Funcao...........: Exibir a tela do servidor mostrando o endereco ip e a porta
*************************************************************** */
package controller;

import algorithm.Server;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaServidor implements Initializable{
  @FXML
  private Label serverAdressLabel;

  private static String ipAdress;
  private static int port;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ipAdress = Server.showIpAddress();
    port = Server.getPortaLocal();
    serverAdressLabel.setText(serverAdressLabel.getText()+"\n"+ipAdress+"\nPorta:"+"\n"+port);
  }

}
