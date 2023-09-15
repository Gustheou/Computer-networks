/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Matricula........: 202011230
* Inicio...........: 11/05/2023
* Ultima alteracao.: 03/06/2023
* Nome.............: ConsoleText
* Funcao...........: Exibir a mensagem no "terminal" do servidor
*************************************************************** */
package util;

import controller.ControleServidor;
import javafx.application.Platform;

public class ConsoleText extends Thread{
  ControleServidor controleServidor;
  private String texto;
  private int operacao; // 0 = aparecer botao; 1 = nao aparecer botao;

  public ConsoleText(){

  }
  public ConsoleText(ControleServidor controleServidor, String texto, int operacao){
    this.controleServidor = controleServidor;
    this.texto = texto;
    this.operacao = operacao;
  }

  @Override
  public void run() {
    try {
        char[] textoCaracter = texto.toCharArray();
        StringBuilder textoEmConstrucao = new StringBuilder();
        for (char letra : textoCaracter) {
          textoEmConstrucao.append(letra);
          Platform.runLater(() -> {
              controleServidor.textoPromptLabel.setText(textoEmConstrucao + "_");
          });
          Thread.sleep(130);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    if (operacao == 0){
      controleServidor.iniciarServidorButton.setVisible(true);
    } else {
      controleServidor.iniciarServidorButton.setVisible(false);
    }
  }
}
