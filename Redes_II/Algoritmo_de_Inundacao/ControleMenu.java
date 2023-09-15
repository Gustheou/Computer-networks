/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 03/02/2023
* Ultima alteracao.: 19/02/2023
* Nome.............: ControleMenu
* Funcao...........: Controlar as opcoes e icones do menu
*************************************************************** */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ControleMenu {

  @FXML
  private ImageView imageAbout, aboutBackButton, aboutScreen, soundImage;

  @FXML
  private Pane infoPane;

  private int somStatus = 1;//1 == ON

  
  @FXML
  public void changeScreenOpcao1(MouseEvent event) {
    Principal.changeScreenOpcao1(event);
  }

  @FXML
  public void changeScreenOpcao2(MouseEvent event) {
    Principal.changeScreenOpcao2(event);
  }

  @FXML
  public void changeScreenOpcao3(MouseEvent event) {
    Principal.changeScreenOpcao3(event);
  }

  @FXML
  public void changeScreenOpcao4(MouseEvent event) {
    // Principal.changeScreenOpcao4(event);
    showDialog();
  }

  public void showDialog(){
    Alert dialogInfo = new Alert(Alert.AlertType.ERROR);
    dialogInfo.setContentText("Algoritmo nao encontrado.");
    dialogInfo.showAndWait();
  }

  @FXML
  public void imageAboutOnMouseClicked(MouseEvent event) {
    aboutScreen.setVisible(true);
    aboutBackButton.setVisible(true);
    infoPane.setVisible(true);
  }

  @FXML
  public void imageAboutOnMouseEntered(MouseEvent event) {
    imageAbout.setImage(Gallery.aboutEntered);
  }

  @FXML
  public void imageAboutOnMouseExited(MouseEvent event) {
    imageAbout.setImage(Gallery.aboutExited);
  }

  @FXML
  public void changeBackButtonEffectEntered(MouseEvent event) {
    aboutBackButton.setImage(Gallery.aboutReturnButtonEntered);
  }

  @FXML
  public void changeBackButtonEffectExited(MouseEvent event) {
    aboutBackButton.setImage(Gallery.aboutReturnButtonExited);
  }

  @FXML
  public void closeAboutMenuOnClicked(MouseEvent event) {
    aboutScreen.setVisible(false);
    aboutBackButton.setVisible(false);
    infoPane.setVisible(false);
  }

  
  @FXML
  public void soundImageOnMouseClicked(MouseEvent event) {
    if (somStatus == 1) {
      somStatus = 0;
      soundImage.setImage(Gallery.soundEntered);//Som off
      Principal.clip.stop();
    } else {
      somStatus = 1;
      soundImage.setImage(Gallery.soundExited);//Som on
      Principal.clip.start();
    }
  }

  @FXML
  public void soundImageOnMouseEntered(MouseEvent event) {
    if (somStatus == 1){
      soundImage.setImage(Gallery.soundEntered);
    } else {
      soundImage.setImage(Gallery.soundExited);
    }
  }

  @FXML
  public void soundImageOnMouseExited(MouseEvent event) {
    if (somStatus == 1){
      soundImage.setImage(Gallery.soundExited);
    } else {
      soundImage.setImage(Gallery.soundEntered);
    }
  }

}
