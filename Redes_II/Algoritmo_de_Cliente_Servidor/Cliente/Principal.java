/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Matricula........: 202011230
* Inicio...........: 15/05/2023
* Ultima alteracao.: 04/06/2023
* Nome.............: Principal
* Funcao...........: Exibir a tela do cliente
*************************************************************** */

import java.io.IOException;

import controller.ControleCliente;
import controller.ControleChat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
  private static Stage stage;

/* ***************************************************************
* Metodo: start
* Funcao: Iniciar a exibição de telas
* Parametros: cenario=responsavel por permitir o uso de telas
* Retorno: void
*************************************************************** */
  @Override
  public void start (Stage cenario) throws IOException {
    ControleCliente controleCliente = new ControleCliente();
    ControleChat controleChat = new ControleChat();
    Parent fxmlTelaPrograma = FXMLLoader.load(getClass().getResource("/view/cliente.fxml"));
    telaPrograma = new Scene (fxmlTelaPrograma);
    cenario.setTitle("Cliente");
    cenario.getIcons().add(new Image("images/ChatIcon.png"));
    cenario.setResizable(false);
    cenario.setScene(telaPrograma);
    // cenario.initStyle(StageStyle.UNDECORATED);
    cenario.show();
  }//Fim do metodo start
}//Fim da classe Principal