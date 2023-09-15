/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 13/02/2023
* Ultima alteracao.: 19/02/2023
* Nome.............: Pacote
* Funcao...........: Realizar a movimentacao, definir origem e destino do pacote
*************************************************************** */
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Pacote extends Thread{
    
  public static int contadorDePacote = 0;
  private int roteador_origem;
  private int roteador_destino;
  private ImageView carta;

  public Pacote (int roteador_origem, ImageView carta, int roteador_destino){
    this.roteador_origem = roteador_origem;
    this.roteador_destino = roteador_destino;
    this.carta = carta;
    carta.setFitWidth(19);
    carta.setFitHeight(29);
  }

/* ***************************************************************
* Metodo: run
* Funcao: Iniciar a thread
* Parametros:
* Retorno: void
*************************************************************** */
  @Override
  public void run() {
    try {
      if (roteador_origem != 0){
        Thread.sleep(roteadorWaiting(roteador_origem));
      }
      while(true){
        System.out.println("Pacotes enviados: " + contadorDePacote);
        roteamentoOrigemOpcao1(roteador_origem, roteador_destino);
        if (roteador_origem != 0){
          Thread.sleep(roteadorWaiting(0));
        }else
        Thread.sleep(4000);
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

/* ***************************************************************
* Metodo: roteamentoOrigemOpcao1
* Funcao: definir qual é o roteador de origem para enviar o pacote
* Parametros: roteadorDeOrigem = roteador inicial, roteadorDeDestino = roteador final
* Retorno: void
*************************************************************** */
  public void roteamentoOrigemOpcao1 (int roteadorDeOrigem, int roteadorDeDestino){
    int [] coordenadas_origem = TabelaDeRoteamento.tabela_de_roteamento(roteadorDeOrigem);
    int [] coordenadas_destino = TabelaDeRoteamento.tabela_de_roteamento(roteadorDeDestino);
    switch (roteadorDeOrigem){
      case 0: {//host to router 1,2,3
        enviar_pacote(coordenadas_origem[0], coordenadas_origem[1], coordenadas_destino[0], coordenadas_destino[1]);
        contadorDePacote += 1;
        break;
      }
      case 1: {//router 1 to host and router 2, 5 ,6
        enviar_pacote(coordenadas_origem[0], coordenadas_origem[1], coordenadas_destino[0], coordenadas_destino[1]);
        contadorDePacote +=1;
        break;
      }
      case 2: {//router 2 to host and router 1,3,4
        enviar_pacote(coordenadas_origem[0], coordenadas_origem[1], coordenadas_destino[0], coordenadas_destino[1]); 
        contadorDePacote+=1;
        break;
      }
      case 3: {// router 3 to host and router 2, 4
        enviar_pacote(coordenadas_origem[0], coordenadas_origem[1], coordenadas_destino[0], coordenadas_origem[1]);
        contadorDePacote+=1;
        break;
      }
      case 4: {// router 4 to 2, 3, 6, destination
        enviar_pacote(coordenadas_origem[0], coordenadas_origem[1], coordenadas_destino[0], coordenadas_destino[1]);

        contadorDePacote+=1;
        break;
      }
      case 5: {//router 5 to 1, 6, 7
        enviar_pacote(coordenadas_origem[0], coordenadas_origem[1], coordenadas_destino[0], coordenadas_destino[1]);
        contadorDePacote+=1;
        break;
      }
      case 6: {//router 6 to 1, 4, 5, 7
        enviar_pacote(coordenadas_origem[0], coordenadas_origem[1], coordenadas_destino[0], coordenadas_destino[1]);
        contadorDePacote+=1;
        break;
      }
      case 7: {//router 7 to 5, 6
        enviar_pacote(coordenadas_origem[0], coordenadas_origem[1], coordenadas_destino[0], coordenadas_destino[1]);
        contadorDePacote+=1;
        break;
      }
      default: {//none
        
        break;
      }
      
    }
  }

/* ***************************************************************
* Metodo: roteadorWaiting
* Funcao: Fazer o roteador aguardar
* Parametros: roteadorDeOrigem = roteador inicial, roteadorDeDestino = roteador final
* Retorno: tempo de espera de cada roteador
*************************************************************** */
  public long roteadorWaiting(int roteador_origem) throws InterruptedException{
    switch(roteador_origem){
      case 0: {
        return 2200;
      }
      case 1: {
        return 2000;
      }
      case 2: {
        return 2000;
      }
      case 3: {
        return 2000;
      }
      case 4: {
        return 4000;
      }
      case 5: {
        return 4000;
      }
      case 6: {
        return 4000;
      }
      case 7: {
        return 6000;
      }
      default: {
        return 10000;
      }
    }
  }  

/* ***************************************************************
* Metodo: enviar_pacote
* Funcao: definir a visibilidade do pacote e enviar para determinada coordenada
* Parametros: coordenadas iniciais e finais
* Retorno: void
*************************************************************** */
  public void enviar_pacote(double xInicial, double yInicial, double xFinal, double yFinal){
    carta.setVisible(true);
    carta.setLayoutX(0);
    carta.setLayoutY(0);
    carta.setX(0);
    carta.setY(0);

    animacaoDoPacote(xInicial, yInicial, xFinal, yFinal);
  }

/* ***************************************************************
* Metodo: animacaoDoPacote
* Funcao: fazer a animacao do pacote
* Parametros: coordenadas iniciais e finais
* Retorno: void
*************************************************************** */
  public void animacaoDoPacote (double xInicial, double yInicial, double xFinal, double yFinal) {
    TranslateTransition animacao = new TranslateTransition(Duration.millis(2000), carta);
    animacao.setFromX(xInicial);
    animacao.setFromY(yInicial);
    animacao.setToX(xFinal);
    animacao.setToY(yFinal);
    animacao.setOnFinished(event -> {
      carta.setVisible(false);
      if (xFinal == 454 && yFinal == 386)
        ControleOpcao1.destinoImage.setImage(null);
    });
    animacao.play();
  }

  public int getRoteadorDestino () {
    return roteador_destino;
  }

  public ImageView getCarta(){
    return carta;
  }
}