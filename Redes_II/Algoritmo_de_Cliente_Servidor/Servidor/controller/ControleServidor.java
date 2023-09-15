/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Matricula........: 202011230
* Inicio...........: 11/05/2023
* Ultima alteracao.: 03/05/2023
* Nome.............: ControleServidor
* Funcao...........: Controlar a tela do servidor
*************************************************************** */
package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import algorithm.Server;

import util.ConsoleText;

public class ControleServidor implements Initializable{
  
  @FXML
  public ImageView iniciarServidorButton;
  
  @FXML
  public Label textoPromptLabel;

  private String textoEscrito = "       Trabalho realizado pelo\ndiscente: Gustavo Pereira Nunes,\nnumero de matricula: 202011230.\nPara iniciar o servidor, clique no\nbotao Iniciar:";

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ConsoleText consoleText = new ConsoleText(this, textoEscrito, 0);
    consoleText.start();
  }
  
  @FXML
  public void iniciarServidorButtonOnMouseClicked(MouseEvent event) {
    String prompt = "       Servidor iniciado!\nEndereco do servidor:\n"+Server.showIpAddress()+"\nPorta:"+Server.getPortaLocal();
    ConsoleText consoleText = new ConsoleText(this, prompt, 1);
    consoleText.start();
    iniciarServidorButton.setVisible(false);
    Server servidor = new Server();
    servidor.startServer();
    try {
      Stage serverLoaded = new Stage();
      Scene telaServidor = new Scene(FXMLLoader.load(getClass().getResource("/view/servidorInicializado.fxml")));
      serverLoaded.setResizable(false);
      serverLoaded.setScene(telaServidor);
      serverLoaded.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
