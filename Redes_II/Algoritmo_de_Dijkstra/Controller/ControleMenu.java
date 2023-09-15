/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 23/02/2023
* Ultima alteracao.: 05/03/2023
* Nome.............: ControleMenu
* Funcao...........: Controlar a funcionalidade da tela principal
*************************************************************** */
package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ControleMenu {

  @FXML
  private ImageView buttonIniciarImage;
  private static Image mouseEntered = new Image("Imagens/BotaoSelected.png");
  private static Image mouseExited = new Image("Imagens/BotaoNormal.png");
  private static Image background = new Image("Imagens/BG.png");
  @FXML
  private ImageView backgroudImageView, extraImageView;
  @FXML
  private Pane grafoPane;
  @FXML
  private Label infoLabel, pesoLabel;


/* ***************************************************************
* Metodo: buttonIniciarImageOnMouseClicked
* Funcao: iniciar o grafo ao clicado
* Parametros: event = clique do mouse
* Retorno: void
*************************************************************** */
  @FXML
  public void buttonIniciarImageOnMouseClicked(MouseEvent event) {
    buttonIniciarImage.setVisible(false);
    extraImageView.setVisible(false);
    backgroudImageView.setImage(background);
    showInfoDialog();
    exibirGrafo();
  }

/* ***************************************************************
* Metodo: buttonIniciarImageOnMouseEntered
* Funcao: Mudar a imagem ao entrar com o mouse
* Parametros: event = evento do mouse
* Retorno: void
*************************************************************** */
  @FXML
  public void buttonIniciarImageOnMouseEntered(MouseEvent event) {
    buttonIniciarImage.setImage(mouseEntered);
  }

/* ***************************************************************
* Metodo: buttonIniciarImageOnMouseExited
* Funcao: mudar imagem ao tirar o mouse
* Parametros: event = movimento do mouse
* Retorno: void
*************************************************************** */
  @FXML
  public void buttonIniciarImageOnMouseExited(MouseEvent event) {
    buttonIniciarImage.setImage(mouseExited);
  }

/* ***************************************************************
* Metodo: exibirGrafo
* Funcao: instanciar o pane que armazenara o grafo que sera criado
* Parametros: void
* Retorno: void
*************************************************************** */
  public void exibirGrafo(){
    GerarGrafo gerador = new GerarGrafo(this);
    gerador.gerarGrafo();
    grafoPane = gerador.getRoot();
    grafoPane.setVisible(true);
  }

/* ***************************************************************
* Metodo: getPane
* Funcao: retornar o pane que sera usado na classe GerarGrafo.java 
* Parametros: void
* Retorno: Pane = javafx container
*************************************************************** */
  public Pane getPane(){
    return grafoPane;
  }

/* ***************************************************************
* Metodo: showInfoDialog
* Funcao: exibir mensagem informativa
* Parametros: void
* Retorno: void
*************************************************************** */
  public void showInfoDialog(){
    Alert dialogInfo = new Alert(Alert.AlertType.INFORMATION);
    dialogInfo.setContentText("Selecione dois roteadores. A ordem de seleção implicara na origem e destino.\nO primeiro sera a origem e o segundo o destino.\nEscolha sabiamente!");
    dialogInfo.show();
  }

/* ***************************************************************
* Metodo: getInfoLabel
* Funcao: retornar o label de informacao do roteador de origem e destino para ser manipulado na classe GerarGrafo.java
* Parametros: void
* Retorno: Label = javafx controls
*************************************************************** */
  public Label getInfoLabel(){
    return infoLabel;
  }

/* ***************************************************************
* Metodo: getPesoLabel
* Funcao: retornar o label de informacao do peso e rota de origem e destino para ser manipulado na classe GerarGrafo.java
* Parametros: void
* Retorno: Label = javafx controls
*************************************************************** */
  public Label getPesoLabel(){
    return pesoLabel;
  }
}//fim da classe ControleMenu
