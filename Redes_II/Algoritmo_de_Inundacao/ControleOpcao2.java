
/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 18/02/2023
* Ultima alteracao.: 19/02/2023
* Nome.............: ControleOpcao2
* Funcao...........: Implementacao do algoritmo de inundacao referente a opcao 02 (enviar para todos exceto ao roteador que recebeu)
*************************************************************** */
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.Clip;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControleOpcao2 implements Initializable {

  @FXML
  private ImageView destinoImage;

  @FXML
  private ImageView iniciarImage;

  @FXML
  private ImageView packageImage, packageImage1, packageImage2, packageImage3, packageImage4, packageImage5,
      packageImage6, packageImage7,
      packageImage8, packageImage9, packageImage10, packageImage11, packageImage12, packageImage13, packageImage14,
      packageImage15, packageImage16, packageImage17, packageImage18, packageImage19, packageImage20, packageImage21, packageImage22,
      packageImage23, packageImage24, packageImage25, packageImage26;

  @FXML
  private ImageView soundImage;

  @FXML
  private ImageView voltarImage;

  private int somStatus = 1; // valor 1 corresponde ao som ON; valor 0 corresponde ao som OFF
  private Roteador host, roteador1, roteador2, roteador3, roteador4, roteador5, roteador6, roteador7;
  File musicFile = new File("Songs/Persona5_song_opcao2.wav");
  public static Clip clip;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    AudioFiles.audioInitializeOpcao2(musicFile, clip);
  }

  /*
   * ***************************************************************
   * Metodo: iniciarImageOnMouseClicked
   * Funcao: Inicializar a musica e o algoritmo
   * Parametros: event = o mouse em si
   * Retorno: void
   */
  @FXML
  public void iniciarImageOnMouseClicked(MouseEvent event) {
    iniciarImage.setVisible(false);
    voltarImage.setVisible(true);
    soundImage.setVisible(true);
    AudioFiles.audioStartButtonOpcao2(musicFile, clip);
    Subrede subrede = new Subrede();
    definir_roteadores(subrede);
    showDialog();
  }

  /*
   * ***************************************************************
   * Metodo: showDialog
   * Funcao: Mostrar imagem na tela
   * Parametros: void
   * Retorno: void
   */
    public void showDialog(){
      Alert dialogInfo = new Alert(Alert.AlertType.ERROR);
      dialogInfo.setContentText("Algoritmo em desenvolvimento.");
      dialogInfo.showAndWait();
    }

  /*
   * ***************************************************************
   * Metodo: iniciarImageOnMouseEntered
   * Funcao: Trocar a imagem quando o mouse passar por cima
   * Parametros: event = o mouse em si
   * Retorno: void
   */
  @FXML
  public void iniciarImageOnMouseEntered(MouseEvent event) {
    iniciarImage.setImage(Gallery.iniciarOnMouseEntered);
  }

  /*
   * ***************************************************************
   * Metodo: iniciarImageOnMouseExited
   * Funcao: Trocar a imagem quando tirar o mouse da imagem de iniciar
   * Parametros: event = o mouse em si
   * Retorno: void
   */
  @FXML
  public void iniciarImageOnMouseExited(MouseEvent event) {
    iniciarImage.setImage(Gallery.iniciarOnMouseExited);
  }

  /*
   * ***************************************************************
   * Metodo: voltarImageOnMouseClicked
   * Funcao: Parar a musica, interromper as threads e voltar para o menu
   * Parametros: event = o mouse em si
   * Retorno: void
   */
  @FXML
  public void voltarImageOnMouseClicked(MouseEvent event) {
    iniciarImage.setVisible(true);
    soundImage.setVisible(false);
    soundImage.setImage(Gallery.soundExited);// soundExited == som ligado
    if (clip.isActive()) {
      clip.stop();
      clip.close();
    }
    // for (int i = 0; i < deliver.length; i++) {
    // if (deliver[i].isAlive()){
    // deliver[i].interrupt();
    // }
    // }
    Principal.changeScreenMenu(event);
  }

  /*
   * ***************************************************************
   * Metodo: voltarImageOnMouseEntered
   * Funcao: Trocar a imagem quando o mouse passar por cima
   * Parametros: event = o mouse em si
   * Retorno: void
   */
  @FXML
  public void voltarImageOnMouseEntered(MouseEvent event) {
    voltarImage.setImage(Gallery.voltarOnMouseEntered);
  }

  /*
   * ***************************************************************
   * Metodo: voltarImageOnMouseExited
   * Funcao: Trocar a imagem quando tirar o mouse da imagem voltar
   * Parametros: event = o mouse em si
   * Retorno: void
   */
  @FXML
  public void voltarImageOnMouseExited(MouseEvent event) {
    voltarImage.setImage(Gallery.voltarOnMouseExited);
  }

  /*
   * ***************************************************************
   * Metodo: soundImageOnMouseClicked
   * Funcao: Parar/continuar a musica
   * Parametros: event = o mouse em si
   * Retorno: void
   */
  @FXML
  public void soundImageOnMouseClicked(MouseEvent event) {
    if (somStatus == 1) {
      somStatus = 0;
      soundImage.setImage(Gallery.soundEntered);// Som off
      clip.stop();
    } else {
      somStatus = 1;
      soundImage.setImage(Gallery.soundExited);// Som on
      clip.start();
    }
  }

  /*
   * ***************************************************************
   * Metodo: soundImageOnMouseEntered
   * Funcao: Trocar a imagem quando o mouse passar por cima
   * Parametros: event = o mouse em si
   * Retorno: void
   */
  @FXML
  public void soundImageOnMouseEntered(MouseEvent event) {
    if (somStatus == 1) {
      soundImage.setImage(Gallery.soundEntered);
    } else {
      soundImage.setImage(Gallery.soundExited);
    }
  }

  /*
   * ***************************************************************
   * Metodo: soundImageOnMouseExited
   * Funcao: Trocar a imagem quando o mouse sai passar por cima
   * Parametros: event = o mouse em si
   * Retorno: void
   */
  @FXML
  public void soundImageOnMouseExited(MouseEvent event) {
    if (somStatus == 1) {
      soundImage.setImage(Gallery.soundExited);
    } else {
      soundImage.setImage(Gallery.soundEntered);
    }
  }

  /*
   * ***************************************************************
   * Metodo: definir_roteadores
   * Funcao: Estabelecer os locais e adicionar ao grafo de subrede
   * Parametros: subrede = estrutura que armazena os roteadores e seus vizinhos
   * Retorno: void
   */
  public void definir_roteadores(Subrede subrede) {
    host = new Roteador(118, 227, 0, subrede);
    roteador1 = new Roteador(267, 196, 1, subrede);
    roteador2 = new Roteador(218, 286, 2, subrede);
    roteador3 = new Roteador(201, 371, 3, subrede);
    roteador4 = new Roteador(326, 326, 4, subrede);
    roteador5 = new Roteador(419, 153, 5, subrede);
    roteador6 = new Roteador(470, 237, 6, subrede);
    roteador7 = new Roteador(549, 153, 7, subrede);
    subrede.adicionar_roteador(host);
    subrede.adicionar_roteador(roteador1);
    subrede.adicionar_roteador(roteador2);
    subrede.adicionar_roteador(roteador3);
    subrede.adicionar_roteador(roteador4);
    subrede.adicionar_roteador(roteador5);
    subrede.adicionar_roteador(roteador6);
    subrede.adicionar_roteador(roteador7);

    definir_vizinhos(subrede, host, roteador1, roteador2, roteador3, roteador4, roteador5, roteador6, roteador7);
  }

  /*
   * ***************************************************************
   * Metodo: definir_vizinhos
   * Funcao: Adicionar ao grafo os vizinhos da subrede
   * Parametros: subrede = estrutura que armazena os roteadores e seus vizinhos e
   * todos os roteadores do grafo
   * Retorno: void
   */
  public void definir_vizinhos(Subrede subrede, Roteador host, Roteador roteador1, Roteador roteador2,
      Roteador roteador3,
      Roteador roteador4, Roteador roteador5, Roteador roteador6, Roteador roteador7) {

    subrede.adicionar_vizinho(host, roteador1);
    subrede.adicionar_vizinho(host, roteador2);
    subrede.adicionar_vizinho(host, roteador3);

    subrede.adicionar_vizinho(roteador1, roteador2);
    subrede.adicionar_vizinho(roteador1, roteador5);
    subrede.adicionar_vizinho(roteador1, roteador6);

    subrede.adicionar_vizinho(roteador2, roteador3);
    subrede.adicionar_vizinho(roteador2, roteador4);

    subrede.adicionar_vizinho(roteador3, roteador4);

    subrede.adicionar_vizinho(roteador5, roteador6);
    subrede.adicionar_vizinho(roteador5, roteador7);

    subrede.adicionar_vizinho(roteador6, roteador7);
  }

  /*
   * ***************************************************************
   * Metodo: resgatar_imagem_do_pacote
   * Funcao: Funciona como um vetor para ser usado por cada pacote (o vetor estava
   * resultando em excecao "Null pointer Exception")
   * Parametros: seletor = pacote que esta solicitando
   * Retorno: ImageView = imagem do pacote
   */
  public ImageView resgatar_imagem_do_pacote(int seletor) {
    switch (seletor) {
      case 0: {
        return packageImage;
      }
      case 1: {
        return packageImage1;
      }
      case 2: {
        return packageImage2;
      }
      case 3: {
        return packageImage3;
      }
      case 4: {
        return packageImage4;
      }
      case 5: {
        return packageImage5;
      }
      case 6: {
        return packageImage6;
      }
      case 7: {
        return packageImage7;
      }
      case 8: {
        return packageImage8;
      }
      case 9: {
        return packageImage9;
      }
      case 10: {
        return packageImage10;
      }
      case 11: {
        return packageImage11;
      }
      case 12: {
        return packageImage12;
      }
      case 13: {
        return packageImage13;
      }
      case 14: {
        return packageImage14;
      }
      case 15: {
        return packageImage15;
      }
      case 16: {
        return packageImage16;
      }
      case 17: {
        return packageImage17;
      }
      case 18: {
        return packageImage18;
      }
      case 19: {
        return packageImage19;
      }
      case 20: {
        return packageImage20;
      }
      case 21: {
        return packageImage21;
      }
      case 22: {
        return packageImage22;
      }
      case 23: {
        return packageImage23;
      }
      case 24: {
        return packageImage24;
      }
      case 25: {
        return packageImage25;
      }
      case 26: {
        return packageImage26;
      }
      default: {
        return null;
      }
    }
  }// Fim do metodo resgatar_imagem_do_pacote
}
