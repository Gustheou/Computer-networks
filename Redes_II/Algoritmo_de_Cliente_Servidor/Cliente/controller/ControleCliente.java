/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Matricula........: 202011230
* Inicio...........: 15/05/2023
* Ultima alteracao.: 04/06/2023
* Nome.............: controleCliente
* Funcao...........: Definir o servidor, sala e nome de usuario
*************************************************************** */
package controller;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import model.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControleCliente {

  public ControleCliente(){}

  private final int WARNING_POPUP = 0;
  private final int ERROR_POPUP = 1;
  private final int HELP_POPUP = 2;
  static final Image warningPopupImage = new Image("images/Warning.png"); //Room already exist
  static final Image errorPopupImage = new Image("images/Error.png"); //Incompleted info
  static final Image helpPopupImage = new Image("images/Help.png"); //Basic guide

  public static String username;
  public static ArrayList<Client> users = new ArrayList<Client>();
  private static String enderecoServidor;
  private static int porta;
  
  @FXML
  private TextField enderecoServidorTextField, usuarioTextField, nomeSalaTextField, portaServidorTextField;
  @FXML
  private TextArea feedbackTextArea;
  @FXML
  private AnchorPane popupPaneAnchorPane, blackDimAnchorPane;
  @FXML
  private ImageView popupImageView;
  

  @FXML
  public void ajudaOnMouseClicked(MouseEvent event) {
    popupMessage(HELP_POPUP);
  }

  @FXML
  public void conectarServerOnMouseClicked(MouseEvent event) {
    try {
      porta = Integer.parseInt(portaServidorTextField.getText().toString());
      enderecoServidor = enderecoServidorTextField.getText().toString();
      Socket socket = new Socket(enderecoServidor,porta);
      feedbackTextArea.setText("Servidor conectado!");
      socket.close();
    } catch (NullPointerException e) {
      popupMessage(ERROR_POPUP);
    } catch (IOException e) {
      feedbackTextArea.setText("Servidor nao encontrado!");
    }
  }

  @FXML
  public void criarSalaOnMouseClicked(MouseEvent event) {
    popupMessage(WARNING_POPUP);
  }

  @FXML
  public void entrarSalaOnMouseClicked(MouseEvent event) {
    try {
      Client newUser = new Client();
      String roomName = nomeSalaTextField.getText();
      String userName = usuarioTextField.getText();
      int porta = Integer.parseInt(portaServidorTextField.getText());
      String endereco = enderecoServidorTextField.getText();
      newUser.name = userName;
      newUser.room = roomName;
      newUser.ip = endereco;
      newUser.port = porta;
      this.username = userName;
      if (roomName.equals("") || userName.equals("")){
        popupMessage(ERROR_POPUP);
        return;
      }
      users.add(newUser);
      changeWindow();
    } catch (NullPointerException e) {
      popupMessage(ERROR_POPUP);
    }

  }

  public void unavailableScreen(){
    usuarioTextField.setEditable(false);
    portaServidorTextField.setEditable(false);
    enderecoServidorTextField.setEditable(false);
    nomeSalaTextField.setEditable(false);
    blackDimAnchorPane.setVisible(true);
    feedbackTextArea.setText("Bom papo!");
  }

  public void changeWindow(){
    unavailableScreen();
    try {
      Stage chat = new Stage();
      Scene tela_chat = new Scene(FXMLLoader.load(getClass().getResource("/view/chat.fxml")));
      chat.setResizable(false);
      chat.setScene(tela_chat);
      chat.setTitle("Usuario:"+username + "");
      chat.setOnCloseRequest(event -> {
        System.exit(0);
      });
      chat.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void sairAjudaButtonOnMouseClicked (MouseEvent event) {
    hidePopupMessage();
  }

  @FXML
  public void sairAjudaButtonXOnMouseClicked (MouseEvent event) {
    hidePopupMessage();
  } 

  public void popupMessage(int operacao){
    switch (operacao){
      case WARNING_POPUP: {
        blackDimAnchorPane.setVisible(true);
        popupPaneAnchorPane.setVisible(true);
        popupImageView.setImage(warningPopupImage);
        break;
      }
      case ERROR_POPUP: {
        blackDimAnchorPane.setVisible(true);
        popupPaneAnchorPane.setVisible(true);
        popupImageView.setImage(errorPopupImage);
        break;
      }
      case HELP_POPUP: {
        blackDimAnchorPane.setVisible(true);
        popupPaneAnchorPane.setVisible(true);
        popupImageView.setImage(helpPopupImage);
        break;
      }
    }
  }

  public void hidePopupMessage(){
    blackDimAnchorPane.setVisible(false);
    popupPaneAnchorPane.setVisible(false);
  }

  public static String getEnderecoServidor() {
    return enderecoServidor;
  }

  public void setEnderecoServidor(String enderecoServidor) {
    ControleCliente.enderecoServidor = enderecoServidor;
  }

  public static int getPorta() {
    return porta;
  }

  public void setPorta(int porta) {
    ControleCliente.porta = porta;
  }
}
