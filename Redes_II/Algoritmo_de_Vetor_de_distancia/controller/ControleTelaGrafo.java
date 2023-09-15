/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 24/03/2023
* Ultima alteracao.: --/03/2023
* Nome.............: ControleTelaGrafo
* Funcao...........: Controlar a tela do grafo (manipulacao) que aparece apos as animacoes
*************************************************************** */

package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ControleTelaGrafo implements Initializable {

  @FXML
  private ImageView backgroundImage;

  @FXML
  private Pane grafoPane;

  @FXML
  private Label infoLabel;

  @FXML
  private Label pesoLabel;

  public void iniciarGrafo() {

  }

  public Pane getPane() {
    return grafoPane;
  }

  /*
   * ***************************************************************
   * Metodo: getInfoLabel
   * Funcao: retornar o label de informacao do roteador de origem e destino para
   * ser manipulado na classe GerarGrafo.java
   * Parametros: void
   * Retorno: Label = javafx controls
   */
  public Label getInfoLabel() {
    return infoLabel;
  }

  /*
   * ***************************************************************
   * Metodo: getPesoLabel
   * Funcao: retornar o label de informacao do peso e rota de origem e destino
   * para ser manipulado na classe GerarGrafo.java
   * Parametros: void
   * Retorno: Label = javafx controls
   */
  public Label getPesoLabel() {
    return pesoLabel;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    GerarGrafo gerador = new GerarGrafo(this);
    gerador.gerarGrafo();
    grafoPane = gerador.getRoot();
    grafoPane.setVisible(true);
  }
}
