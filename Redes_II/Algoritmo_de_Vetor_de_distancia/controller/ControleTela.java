/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 24/03/2023
* Ultima alteracao.: --/03/2023
* Nome.............: ControleTela
* Funcao...........: Controlar a tela inicial e suas animacoes
*************************************************************** */

package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import util.Gallery;

public class ControleTela implements Initializable{

  @FXML
  private ImageView backgroundImage, iniciarImageButton, transition;

  private static File musicClick = new File ("songs/Click.wav");
  private static File musicTransition = new File ("songs/Bats.wav");
  private static File musicTheme = new File ("songs/MainTheme.wav");
  private static File musicInitializing = new File("songs/Initializing.wav");
  public static Clip clip;
  ProcessoTransition pT;

  @FXML
  public void iniciarImageButtonOnClicked(MouseEvent event) {
    AudioFiles.terminateSongMain();
    AudioFiles.audioMain(musicClick);
    transition.setDisable(false);
    iniciarImageButton.setDisable(true);
    AudioFiles.terminateSongMain();
    transition.setVisible(true);
    pT = new ProcessoTransition();
    pT.start();
    

    //iniciarGrafo();
  }
  public static Stage stage;
  private static Scene telaGrafo;

  public void iniciarGrafo(){
    Parent fxmlTelaGrafo;
    try {
      fxmlTelaGrafo = FXMLLoader.load(getClass().getResource("/telaGrafo.fxml"));
      telaGrafo = new Scene(fxmlTelaGrafo);
      stage.setScene(telaGrafo);
      stage.show();
    } catch (IOException e) {
      // System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    AudioFiles.audioMain(musicTheme);///Inicializando a musica
  }

  

  public class ProcessoTransition extends Thread{
    public void run () {
      Timer temporizador = new Timer();
      long tempo = 0;
      temporizador.schedule(bats, tempo);
    }//Fim do metodo run
    TimerTask bats = new TimerTask() {
      @Override
      public void run () {
        FadeTransition ft = new FadeTransition(Duration.millis(600), transition);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        AudioFiles.audioMain(musicTransition);
        try {
          Thread.sleep(600);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        transition.setImage(Gallery.gifStartup);
        AudioFiles.audioMain(musicInitializing);
        try {
          Thread.sleep(6000);
        } catch (InterruptedException e) {e.printStackTrace();}
        transition.setDisable(true);
        
        Platform.runLater(() -> iniciarGrafo());
      }//Fim do metodo run
    };//Fim do TimerTask
  }//Fim da classe processoTransition
}

