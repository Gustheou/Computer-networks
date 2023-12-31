/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 19/08/2022
* Ultima alteracao.: 28/08/2022
* Nome.............: ControleMenu
* Funcao...........: Controlar a passagem da tela inicial para a tela secundária
*************************************************************** */
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ControleMenu {

/* ***************************************************************
* Metodo: startImageButton
* Funcao: Quando acionado o botao, o programa muda de tela
* Parametros: MouseEvent event = evento que requer uma acao para ser executado, que no caso eh o de apertar um "botao"
* Retorno: void
*************************************************************** */
  @FXML
  void startImageButton(MouseEvent event) {
    Principal.changeScreenPrograma(event);
  }

}
