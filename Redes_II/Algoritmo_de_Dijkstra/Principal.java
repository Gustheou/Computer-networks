/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 23/02/2023
* Ultima alteracao.: 05/03/2023
* Nome.............: Principal
* Funcao...........: Exibir as telas
*************************************************************** */
import java.io.IOException;

import Controller.ControleMenu;
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

  private static Stage stage;
  private static Scene telaMenu;

/* ***************************************************************
* Metodo: start
* Funcao: Iniciar a exibição de telas
* Parametros: cenario=responsavel por permitir o uso de telas
* Retorno: void
*************************************************************** */
  @Override
  public void start (Stage cenario) throws IOException {
    ControleMenu cM = new ControleMenu();
    stage = cenario;
    cenario.setTitle("Algoritmo de caminho mais curto");
    Parent fxmlTelaInicial = FXMLLoader.load(getClass().getResource("TelaPrincipal.fxml"));
    telaMenu = new Scene (fxmlTelaInicial);
    cenario.getIcons().add(new Image("Imagens/Icon.png"));
    cenario.setScene(telaMenu);
    cenario.setResizable(false);
    cenario.show();
  }//Fim do metodo start
  
}//Fim da classe Principal
