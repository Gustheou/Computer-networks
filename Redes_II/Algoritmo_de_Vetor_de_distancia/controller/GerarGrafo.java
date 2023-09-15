/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 24/03/2023
* Ultima alteracao.: --/03/2023
* Nome.............: GerarGrafo
* Funcao...........: Criar a estrutura de dados, exibir e manipular na interface
*************************************************************** */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class GerarGrafo {
  private static String nome_do_arquivo = "backbone.txt";
  private Text[] metrica;
  private Text[] roteador;
  private int[][] nos_de_ida_e_volta;
  private int numero_de_nos;
  private Line[] linhas;
  private RadioButton[] nodes;
  private int status = 1;
  private String roteador_origem, roteador_destino;
  Pane root;
  Label infoLabel,pesoLabel;
  ToggleGroup group = new ToggleGroup();

  ControleTelaGrafo controle;

  public GerarGrafo(ControleTelaGrafo controle) {
    this.controle = controle;
    root = controle.getPane();
    infoLabel = controle.getInfoLabel();
    pesoLabel = controle.getPesoLabel();
  }

/* ***************************************************************
* Metodo: lerArquivo
* Funcao: ler o arquivo backbone.txt
* Parametros: void
* Retorno: void
*************************************************************** */
  public void lerArquivo() {
    try {
      BufferedReader br = new BufferedReader(new FileReader(nome_do_arquivo));
      String primeira_linha = br.readLine();
      int index = primeira_linha.indexOf(";");
      numero_de_nos = Integer.parseInt(primeira_linha.substring(0, index));
      nos_de_ida_e_volta = new int[numero_de_nos][numero_de_nos];
      String linha_atual;
      while ((linha_atual = br.readLine()) != null) {
        String[] parts = linha_atual.split(";");
        int no1 = Integer.parseInt(parts[0]) - 1;
        int no2 = Integer.parseInt(parts[1]) - 1;
        int peso = Integer.parseInt(parts[2]);
        nos_de_ida_e_volta[no1][no2] = peso;
        nos_de_ida_e_volta[no2][no1] = peso;
      }//fim do while
      br.close();
    } catch (IOException ioe) {
      System.out.println("Excecao em ler o arquivo: " + ioe.getMessage());
    }
  }//fim do metodo lerArquivo

/* ***************************************************************
* Metodo: gerarGrafo
* Funcao: gerar o grafo com base no arquivo lido
* Parametros: void
* Retorno: void
*************************************************************** */
  public void gerarGrafo() {
    File file = new File(nome_do_arquivo);
    if (!file.exists()) {
      System.out.println("Arquivo inexistente");
    }
    lerArquivo();
    double angulo = 105;//105, 115; Possiveis ângulos para um grafico legivel (baseado em tentativas erros)
    nodes = new RadioButton[numero_de_nos];
    definirRoteadores(angulo, nodes);
    int count = 0;
    linhas = new Line[numero_de_nos * (numero_de_nos - 1) / 2];
    roteador = new Text[numero_de_nos + 1];
    metrica = new Text[numero_de_nos * (numero_de_nos - 1) / 2];
    interligarOGrafo(linhas, roteador, metrica, count);
    setRoot(root);
  }

/* ***************************************************************
* Metodo: setRoot
* Funcao: setar o pane que sera manipulado para acoplar o grafo
* Parametros: void
* Retorno: void
*************************************************************** */
  public void setRoot(Pane pane) {
    this.root = pane;
  }

/* ***************************************************************
* Metodo: getRoot
* Funcao: retornar o root/pane para ser exibido no controleMenu
* Parametros: void
* Retorno: Pane
*************************************************************** */
  public Pane getRoot() {
    return root;
  }

/* ***************************************************************
* Metodo: definirRoteadores
* Funcao: definir as posicoes, id e adicionar ao pane,os roteadores
* Parametros: void
* Retorno: void
*************************************************************** */
  public void definirRoteadores(double angle, RadioButton[] nodes){
    for (int i = 0; i < numero_de_nos; i++) {
      double x = 120 + 100 * Math.cos(i * angle);
      double y = 120 + 100 * Math.sin(i * angle);
      RadioButton radio = new RadioButton();
      radio = instanciarRoteadores(radio, x, y, i);
      root.getChildren().add(radio);
      nodes[i] = radio;
    }
  }

/* ***************************************************************
* Metodo: instanciarRoteadores
* Funcao: posicoes, id, cursor e eventos definidos aos roteadores
* Parametros: radio = roteador sem informacao; x = eixo x; y = eixo y; id = id
* Retorno: radio = roteador com informacao definida sobre si
*************************************************************** */
  public RadioButton instanciarRoteadores(RadioButton radio, double x, double y, int id) {
    radio.setLayoutX(x);
    radio.setLayoutY(y);
    radio.setToggleGroup(group);
    radio.setCursor(Cursor.HAND);
    radio.setId("Roteador" + id);
    radio.setOnMouseClicked(e -> {
      if (status == 1) {//Para adquirir a origem
        infoLabel.setText("O: " + radio.getId());
        roteador_origem = radio.getId();
      }//fim do if
      if (status == 2) {//Para adquirir o destino e desativar os demais roteadores para não houver excesso de cliques
        infoLabel.setText(infoLabel.getText()+ "\n\n\n" + "D: "+radio.getId());
        for (int k = 0; k < nodes.length; k++)
          nodes[k].setDisable(true);
        roteador_destino = radio.getId();
      }//fim do if
      status++;
    });
    return radio;
  }//fim do metodo instanciarRoteadores

/* ***************************************************************
* Metodo: interligarOGrafo
* Funcao: criar e ligar linhas (definidas no *.txt) entre os roteadores 
* Parametros: linhas = vetor para armazenar para facilitar a manipulacao; roteador = vetor de texto para setar o nome acima de cada radio; 
                       metrica = vetor de texto para setar o peso acima de cada reta; count = variavel para armazenar no vetor de linha, a linha.
* Retorno: void
*************************************************************** */
  public void interligarOGrafo(Line[] linhas, Text[] roteador, Text[] metrica, int count){
    for (int i = 0; i < numero_de_nos; i++) {//Para a origem
      for (int j = i + 1; j < numero_de_nos; j++) {//Para o destino
        if (nos_de_ida_e_volta[i][j] > 0) {
          Line line = new Line(nodes[i].getLayoutX()+10, nodes[i].getLayoutY()+10, nodes[j].getLayoutX()+10, nodes[j].getLayoutY()+10);
          line.setStroke(Color.rgb(72, 70, 58));
          root.getChildren().add(line);
          linhas[count] = line;
          Text text = new Text((nodes[i].getLayoutX() + nodes[j].getLayoutX()) / 2,
              (nodes[i].getLayoutY() + nodes[j].getLayoutY()) / 2, Integer.toString(nos_de_ida_e_volta[i][j]));
          root.getChildren().add(text);
          metrica[count] = text;
          count++;
        }
      }//Fim do for
      Text router = new Text(nodes[i].getLayoutX() - 25, nodes[i].getLayoutY() - 5, "Roteador: " + Integer.toString(i));
      root.getChildren().add(router);
      roteador[i] = router;
    }//Fim do for
  }//Fim do metodo interligarOGrafo
}//fim da classe GerarGrafo
