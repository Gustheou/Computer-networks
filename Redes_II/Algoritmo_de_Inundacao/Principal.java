/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 02/02/2023
* Ultima alteracao.: 19/02/2023
* Nome.............: Principal
* Funcao...........: Exibir as telas e realizar o controle da musica
*************************************************************** */

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Principal extends Application{
  

/* ***************************************************************
* Metodo: main
* Funcao: lançar o programa
* Parametros: args=essencial para tornar o arquivo como principal
* Retorno: void
*************************************************************** */
  public static void main (String [] args) {
    launch (args);
  }//Fim do metodo main

  private static Stage stage;
  private static Scene telaMenu, telaOpcao1, telaOpcao2, telaOpcao3, telaOpcao4;
  private static File musicFile = new File ("Songs/Persona5_song_menu.wav");
  public static Clip clip;

/* ***************************************************************
* Metodo: start
* Funcao: Iniciar a exibição de telas
* Parametros: cenario=responsavel por permitir o uso de telas
* Retorno: void
*************************************************************** */
  @Override
  public void start (Stage cenario) throws IOException {
    ControleMenu cM = new ControleMenu();
    ControleOpcao1 cO1 = new ControleOpcao1();
    AudioFiles.audioInitiliazeMain(musicFile, clip);///Inicializando a musica
    stage = cenario;
    cenario.setTitle("Algoritmo de roteamento");
    Parent fxmlTelaInicial = FXMLLoader.load(getClass().getResource("telaMenu.fxml"));
    Parent fxmlTelaOpcao1 = FXMLLoader.load(getClass().getResource("opcao1.fxml"));
    Parent fxmlTelaOpcao2 = FXMLLoader.load(getClass().getResource("opcao2.fxml"));
    Parent fxmlTelaOpcao3 = FXMLLoader.load(getClass().getResource("opcao3.fxml"));
    telaMenu = new Scene (fxmlTelaInicial);
    telaOpcao1 = new Scene (fxmlTelaOpcao1);    
    telaOpcao2 = new Scene(fxmlTelaOpcao2);
    telaOpcao3 = new Scene(fxmlTelaOpcao3);

    cenario.getIcons().add(new Image("Imagens/Host.png"));
    cenario.setScene(telaMenu);
    cenario.show();
    showDialogStart();
  }//Fim do metodo start

  public static void changeScreenOpcao1 (MouseEvent event) {
    stage.setScene(telaOpcao1);
    AudioFiles.terminateSongMain(clip);
  }

  public static void changeScreenOpcao2 (MouseEvent event) {
    stage.setScene(telaOpcao2);
    AudioFiles.terminateSongMain(clip);
  }

  public static void changeScreenOpcao3 (MouseEvent event) {
    stage.setScene(telaOpcao3);
    AudioFiles.terminateSongMain(clip);
  }

  public static void changeScreenOpcao4 (MouseEvent event) {
    stage.setScene(telaOpcao4);
    AudioFiles.terminateSongMain(clip);
  }

  public static void changeScreenMenu(MouseEvent event){
    stage.setScene(telaMenu);
    AudioFiles.audioInitiliazeMain(musicFile, clip);
  }

  public void showDialogStart(){
    Alert dialogInfo = new Alert(Alert.AlertType.WARNING);
    dialogInfo.setContentText("Existe um video no diretorio chamado intro.mp4, por favor assista-o antes de prosseguir.\nNao se esqueca de desabilitar o som clicando no icone Sound ON");
    dialogInfo.showAndWait();
  }
}//Fim da classe Principal
