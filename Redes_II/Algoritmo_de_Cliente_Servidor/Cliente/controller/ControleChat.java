/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Matricula........: 202011230
* Inicio...........: 16/05/2023
* Ultima alteracao.: 04/06/2023
* Nome.............: ControleChat
* Funcao...........: Controlar a exibicao, envio e recepcao de mensagens
*************************************************************** */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Client;

import static controller.ControleCliente.users;
import static controller.ControleCliente.username;

public class ControleChat extends Thread implements Initializable{

  public ControleChat(){}

  public BufferedReader reader;
  public PrintWriter writer;
  public Socket socket;
  public static String nomeDaSala;

  @FXML
  private TextField writeMessageTextField;
  @FXML
  public Label nomeSalaLabel;
  @FXML
  private VBox vbox_messages;
  @FXML
  private ScrollPane scrollPaneMain;

  @FXML
  public void sendMessageButtonOnMouseClicked(MouseEvent event) {
    sendMessage();
  }

  public void sendMessage(){
    String messageToSend = writeMessageTextField.getText();
    writer.println(username+": " + messageToSend);
    if (!messageToSend.isEmpty()){
      HBox hBox = new HBox();
      hBox.setAlignment(Pos.CENTER_RIGHT);
      hBox.setPadding(new Insets(5, 10, 5, 10));
      Text text = new Text(messageToSend);
      TextFlow textFlow = new TextFlow(text);
      textFlow.setStyle("-fx-color: rgb(239,242,255);"+
                        "-fx-background-color: rgb(15,125,242);"+
                        "-fx-background-radius: 20px;");
      textFlow.setPadding(new Insets(5, 10, 5, 10));
      text.setFill(Color.color(0.934,0.945,0.996));        
      hBox.getChildren().add(textFlow);
      vbox_messages.getChildren().add(hBox);
      writeMessageTextField.clear();          
    }
  }

  public void connectSocket(Socket socket){
    try {
      this.socket = socket;
      System.out.println("Socket is connected with server!");
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      writer = new PrintWriter(socket.getOutputStream(), true);
      
      vbox_messages.heightProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number>observable, Number oldValue, Number newValue){
          scrollPaneMain.setVvalue((Double) newValue.doubleValue());
        }
      });
      this.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    try {
      while (true) {
        String msg = reader.readLine();
        String[] tokens = msg.split(" ");
        String cmd = tokens[0];
        System.out.println(cmd);
        StringBuilder fulmsg = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
          fulmsg.append(tokens[i]);
        }
        System.out.println(fulmsg);
        if (cmd.equalsIgnoreCase(username + ":")) {
          continue;
        } else if (fulmsg.toString().equalsIgnoreCase("bye")) {
          break;
        }
        addServerMessage(msg, vbox_messages);
      }
      reader.close();
      writer.close();
      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void addServerMessage(String messageFromServer, VBox vBox){
    HBox hBox = new HBox();
    hBox.setAlignment(Pos.CENTER_LEFT);
    hBox.setPadding(new Insets(5, 5, 5, 10));
    Text text = new Text(messageFromServer);
    TextFlow textFlow = new TextFlow(text);
    textFlow.setStyle("-fx-background-color: rgb(233,233,235);"+
                      "-fx-background-radius: 20px;");
    textFlow.setPadding(new Insets(5, 10, 5, 10));
    hBox.getChildren().add(textFlow);
    Platform.runLater(new Runnable(){
      @Override
      public void run(){
        vBox.getChildren().add(hBox);
      }
    });
  }

  public void sendMessageByKey(KeyEvent event) {
    if (event.getCode().toString().equals("ENTER")) {
        sendMessage();
    }
}

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      String enderecoDoServidor;
      int portaLocal;
      for (Client user:users){
        if (ControleCliente.username.equals(user.name)){
          enderecoDoServidor = user.ip;
          portaLocal = user.port;
          Socket socket = new Socket(enderecoDoServidor,portaLocal);
          connectSocket(socket);
          nomeSalaLabel.setText(user.room);
          break;
        }
      }
    } catch (IOException e) {
      System.out.println("Error!");
      e.printStackTrace();
    } catch (NullPointerException e){
    }
  }
}
