/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 22/03/2023
* Ultima alteracao.: --/03/2023
* Nome.............: Principal
* Funcao...........: Exibir as telas e realizar o controle da musica
*************************************************************** */

import java.io.IOException;

import controller.ControleTela;
import controller.ControleTelaGrafo;
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
    ControleTela cT = new ControleTela();
    ControleTelaGrafo cTG = new ControleTelaGrafo();
    ControleTela.stage = cenario;
    cenario.setTitle("Algoritmo de vetor de distancia");
    Parent fxmlTelaPrograma = FXMLLoader.load(getClass().getResource("telaPrograma.fxml"));
    telaPrograma = new Scene (fxmlTelaPrograma);
    cenario.getIcons().add(new Image("images/Icon.png"));
    cenario.setResizable(false);
    cenario.setScene(telaPrograma);
    cenario.show();
  }//Fim do metodo start
}//Fim da classe Principal