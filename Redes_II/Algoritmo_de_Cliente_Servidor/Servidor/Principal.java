/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Matricula........: 202011230
* Inicio...........: 11/05/2023
* Ultima alteracao.: 03/06/2023
* Nome.............: Principal
* Funcao...........: Exibir a tela do servidor
*************************************************************** */

import java.io.IOException;

import controller.ControleServidor;
import controller.ControleTelaServidor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

  private static Scene telaPrograma;

/* ***************************************************************
* Metodo: start
* Funcao: Iniciar a exibição de telas
* Parametros: cenario=responsavel por permitir o uso de telas
* Retorno: void
*************************************************************** */
  @Override
  public void start (Stage cenario) throws IOException {
    ControleServidor controleServidor = new ControleServidor();
    ControleTelaServidor controleTelaServidor = new ControleTelaServidor();
    cenario.setTitle("Servidor");
    Parent fxmlTelaPrograma = FXMLLoader.load(getClass().getResource("view/servidor.fxml"));
    telaPrograma = new Scene (fxmlTelaPrograma);
    cenario.getIcons().add(new Image("images/Icon.png"));
    cenario.setResizable(false);
    cenario.setOnCloseRequest(event -> {
      System.exit(0);
    });
    cenario.setScene(telaPrograma);
    cenario.show();
  }//Fim do metodo start
}//Fim da classe Principal
