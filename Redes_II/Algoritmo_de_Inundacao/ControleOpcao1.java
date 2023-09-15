/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 03/02/2023
* Ultima alteracao.: 19/02/2023
* Nome.............: ControleOpcao1
* Funcao...........: Realizar o controle do algoritmo de inundacao referente a opcao1
*************************************************************** */
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javax.sound.sampled.Clip;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ControleOpcao1 implements Initializable {

  @FXML
  public Label contadorLabel;

  @FXML
  private ImageView iniciarImage, voltarImage, soundImage;
  @FXML
  public static ImageView destinoImage;

  private Roteador host, roteador1, roteador2, roteador3, roteador4, roteador5, roteador6, roteador7;

  @FXML
  private ImageView packageImage, packageImage1, packageImage2, packageImage3, packageImage4, packageImage5, packageImage6, packageImage7, packageImage8, packageImage9, packageImage10, packageImage11, packageImage12, packageImage13, packageImage14, packageImage15, packageImage16, packageImage17, packageImage18, packageImage19, packageImage20, packageImage21, packageImage22, packageImage23, packageImage24, packageImage25, packageImage26, packageImage27, packageImage28;
  
  private int somStatus = 1; //valor 1 corresponde ao som ON; valor 0 corresponde ao som OFF

  private Pacote[] deliver = new Pacote[29];

  File musicFile = new File ("Songs/Persona5_song_opcao1.wav");
  public static Clip clip;


/* ***************************************************************
* Metodo: initialize
* Funcao: Inicializar junto com a classe principal
* Parametros: location, resources
* Retorno: void
*************************************************************** */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    AudioFiles.audioInitializeOpcao1(musicFile, clip);
    destinoImage = new ImageView(Gallery.personaStandBy);   
  }

/* ***************************************************************
* Metodo: iniciarImageOnMouseClicked
* Funcao: Iniciar as threads e todo funcionamento da opcao1
* Parametros: event = Quando o mouse clicar
* Retorno: void
*************************************************************** */
  @FXML
  public void iniciarImageOnMouseClicked(MouseEvent event) {
    iniciarImage.setVisible(false);
    voltarImage.setVisible(true);
    soundImage.setVisible(true);
    AudioFiles.audioStartButtonOpcao1(musicFile, clip);
    Subrede subrede = new Subrede();
    definir_roteadores(subrede);
    
    for (int i = 0; i < 7; i++) {
      if (i < 3){
        deliver[i] = new Pacote(0, resgatar_imagem_do_pacote(i), i+1);
        deliver[i].start();
      }else if (i == 3){
        deliver[i] = new Pacote(1, resgatar_imagem_do_pacote(i), 0);
        deliver[i+1] = new Pacote(1, resgatar_imagem_do_pacote(i+1), 2);
        deliver[i].start();
        deliver[i+1].start();
        i+=1;
      } else {
        deliver[i] = new Pacote(1, resgatar_imagem_do_pacote(i), i);
        deliver[i].start();
      }
    }
    for (int i = 0; i < 5; i++) {
      if (i == 2){
        i+=1;
      }
      deliver[i+7] = new Pacote(2, resgatar_imagem_do_pacote(i+7), i);
      deliver[i+7].start();
    }
    deliver[12] = new Pacote(3, resgatar_imagem_do_pacote(12),0);
    deliver[13] = new Pacote(3, resgatar_imagem_do_pacote(13),2);
    deliver[14] = new Pacote(3, resgatar_imagem_do_pacote(14),4);
    deliver[15] = new Pacote(4, resgatar_imagem_do_pacote(15),2);
    deliver[16] = new Pacote(4, resgatar_imagem_do_pacote(16),3);
    deliver[17] = new Pacote(4, resgatar_imagem_do_pacote(17),6);
    deliver[18] = new Pacote(4, resgatar_imagem_do_pacote(18),8);
    deliver[19] = new Pacote(5, resgatar_imagem_do_pacote(19),1);
    deliver[20] = new Pacote(5, resgatar_imagem_do_pacote(20),6);
    deliver[21] = new Pacote(5, resgatar_imagem_do_pacote(21),7);
    deliver[22] = new Pacote(6, resgatar_imagem_do_pacote(22),1);
    deliver[23] = new Pacote(6, resgatar_imagem_do_pacote(23),4);
    deliver[24] = new Pacote(6, resgatar_imagem_do_pacote(24),5);
    deliver[25] = new Pacote(6, resgatar_imagem_do_pacote(25),7);
    deliver[26] = new Pacote(7, resgatar_imagem_do_pacote(26),5);
    deliver[27] = new Pacote(7, resgatar_imagem_do_pacote(27),6);

    for (int i = 12; i < 28; i++)
      deliver[i].start();
  }//Fim do metodo iniciarImageOnMouseClicked

/* ***************************************************************
* Metodo: iniciarImageOnMouseEntered
* Funcao: Trocar a imagem quando o mouse passar por cima
* Parametros: event = o mouse em si
* Retorno: void
*************************************************************** */
  @FXML
  public void iniciarImageOnMouseEntered(MouseEvent event) {
    iniciarImage.setImage(Gallery.iniciarOnMouseEntered);
  }

/* ***************************************************************
* Metodo: iniciarImageOnMouseExited
* Funcao: Trocar a imagem quando tirar o mouse da imagem de iniciar
* Parametros: event = o mouse em si
* Retorno: void
*************************************************************** */
  @FXML
  public void iniciarImageOnMouseExited(MouseEvent event) {
    iniciarImage.setImage(Gallery.iniciarOnMouseExited);
  }

/* ***************************************************************
* Metodo: voltarImageOnMouseClicked
* Funcao: Parar a musica, interromper as threads e voltar para o menu
* Parametros: event = o mouse em si
* Retorno: void
*************************************************************** */
  @FXML
  public void voltarImageOnMouseClicked(MouseEvent event) {
    iniciarImage.setVisible(true);
    soundImage.setVisible(false);
    soundImage.setImage(Gallery.soundExited);//soundExited == som ligado
    if (clip.isActive()){
      clip.stop();
      clip.close();
    }
    for (int i = 0; i < deliver.length; i++) {
      if (deliver[i] != null){
        deliver[i].interrupt();
      }
    }
    Pacote.contadorDePacote = 0;
    Principal.changeScreenMenu(event);
  }

/* ***************************************************************
* Metodo: voltarImageOnMouseEntered
* Funcao: Trocar a imagem quando o mouse passar por cima
* Parametros: event = o mouse em si
* Retorno: void
*************************************************************** */
  @FXML
  public void voltarImageOnMouseEntered(MouseEvent event) {
    voltarImage.setImage(Gallery.voltarOnMouseEntered);
  }

/* ***************************************************************
* Metodo: voltarImageOnMouseExited
* Funcao: Trocar a imagem quando tirar o mouse da imagem voltar
* Parametros: event = o mouse em si
* Retorno: void
*************************************************************** */
  @FXML
  public void voltarImageOnMouseExited(MouseEvent event) {
    voltarImage.setImage(Gallery.voltarOnMouseExited);
  }

/* ***************************************************************
* Metodo: soundImageOnMouseClicked
* Funcao: Parar/continuar a musica
* Parametros: event = o mouse em si
* Retorno: void
*************************************************************** */
  @FXML
  public void soundImageOnMouseClicked(MouseEvent event) {
    if (somStatus == 1) {
      somStatus = 0;
      soundImage.setImage(Gallery.soundEntered);//Som off
      clip.stop();
    } else {
      somStatus = 1;
      soundImage.setImage(Gallery.soundExited);//Som on
      clip.start();
    }
  }

/* ***************************************************************
* Metodo: soundImageOnMouseEntered
* Funcao: Trocar a imagem quando o mouse passar por cima
* Parametros: event = o mouse em si
* Retorno: void
*************************************************************** */
  @FXML
  public void soundImageOnMouseEntered(MouseEvent event) {
    if (somStatus == 1){
      soundImage.setImage(Gallery.soundEntered);
    } else {
      soundImage.setImage(Gallery.soundExited);
    }
  }

/* ***************************************************************
* Metodo: soundImageOnMouseExited
* Funcao: Trocar a imagem quando o mouse sai passar por cima
* Parametros: event = o mouse em si
* Retorno: void
*************************************************************** */
  @FXML
  public void soundImageOnMouseExited(MouseEvent event) {
    if (somStatus == 1){
      soundImage.setImage(Gallery.soundExited);
    } else {
      soundImage.setImage(Gallery.soundEntered);
    }
  }

/* ***************************************************************
* Metodo: definir_roteadores
* Funcao: Estabelecer os locais e adicionar ao grafo de subrede
* Parametros: subrede = estrutura que armazena os roteadores e seus vizinhos
* Retorno: void
*************************************************************** */
  public void definir_roteadores(Subrede subrede) {
    host = new Roteador(118,227,0, subrede);
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

/* ***************************************************************
* Metodo: definir_vizinhos
* Funcao: Adicionar ao grafo os vizinhos da subrede
* Parametros: subrede = estrutura que armazena os roteadores e seus vizinhos e todos os roteadores do grafo
* Retorno: void
*************************************************************** */
  public void definir_vizinhos(Subrede subrede, Roteador host, Roteador roteador1, Roteador roteador2, Roteador roteador3,
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

/* ***************************************************************
* Metodo: resgatar_imagem_do_pacote
* Funcao: Funciona como um vetor para ser usado por cada pacote (o vetor estava resultando em excecao "Null pointer Exception")
* Parametros: seletor = pacote que esta solicitando
* Retorno: ImageView = imagem do pacote
*************************************************************** */
  public ImageView resgatar_imagem_do_pacote (int seletor){

    switch (seletor){
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
      case 21:{
        return packageImage21;
      }
      case 22:{
        return packageImage22;
      }
      case 23:{
        return packageImage23;
      }
      case 24:{
        return packageImage24;
      }
      case 25:{
        return packageImage25;
      }
      case 26:{
        return packageImage26;
      }
      case 27:{
        return packageImage27;
      }
      case 28: {
        return packageImage28;
      }
      default: {
        return null;
      }
    }
  }//Fim do metodo resgatar_imagem_do_pacote
}

