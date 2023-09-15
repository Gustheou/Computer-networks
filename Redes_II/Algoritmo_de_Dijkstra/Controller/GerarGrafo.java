/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 23/02/2023
* Ultima alteracao.: 05/03/2023
* Nome.............: GerarGrafo
* Funcao...........: Criar a estrutura de dados, exibir e manipular na interface
*************************************************************** */
package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Algorithm.Dijkstra;
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
  private ArrayList possivel_caminho;
  Pane root;
  Label infoLabel,pesoLabel;
  ToggleGroup group = new ToggleGroup();

  ControleMenu controle;

  public GerarGrafo(ControleMenu controle) {
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
      double x = 220 + 160 * Math.cos(i * angle);
      double y = 200 + 160 * Math.sin(i * angle);
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
        infoLabel.setText("Origem: " + radio.getId());
        roteador_origem = radio.getId();
      }//fim do if
      if (status == 2) {//Para adquirir o destino e desativar os demais roteadores para não houver excesso de cliques
        infoLabel.setText(infoLabel.getText()+ "\n" + "Destino: "+radio.getId());
        for (int k = 0; k < nodes.length; k++)
          nodes[k].setDisable(true);
        roteador_destino = radio.getId();
        iniciarAlgoritmo(roteador_origem, roteador_destino);
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

/* ***************************************************************
* Metodo: iniciarAlgoritmo
* Funcao: pegar a origem e o destino para iniciar o algoritmo de dijkstra
* Parametros: origem = id parcial do roteador de origem; destino = id parcial do roteador de destino
* Retorno: void
*************************************************************** */
  public void iniciarAlgoritmo (String origem, String destino) {
    Dijkstra algoritmo_caminho_curto = new Dijkstra(nos_de_ida_e_volta, roteador_origem, roteador_destino);
    possivel_caminho = algoritmo_caminho_curto.define_short_path();
    desenhar_no_grafo(possivel_caminho);//Enviar o nos_de_ida_e_volta e cada i corresponde a um roteador e cada j corresponde a metrica e 
                                        //a posição estabelecida é relacionada com o destino; metrica = 0 == null;
    pesoLabel.setText("Rota: " + possivel_caminho +"\nPeso: " + algoritmo_caminho_curto.getSomador());
  }

/* ***************************************************************
* Metodo: desenhar_no_grafo
* Funcao: pintar as linhas
* Parametros: possivel_caminho = arraylist com o caminho
* Retorno: void
*************************************************************** */
  public void desenhar_no_grafo (ArrayList possivel_caminho){
    int index = 0;
    int posicaoInicial, posicaoFinal = 0;
    while (index < possivel_caminho.size()-1){
      posicaoInicial = (int) possivel_caminho.get(index);
      posicaoFinal = (int) possivel_caminho.get(index + 1);
      double[] coordenadas_origem = coordenadas(posicaoInicial);
      double[] coordenadas_destino = coordenadas(posicaoFinal);
      for (int i = 0; i < linhas.length; i++){
        if (linhas[i] != null){
          if ((linhas[i].getStartX() == coordenadas_origem[0] && linhas[i].getStartY() == coordenadas_origem[1] && linhas[i].getEndX() == coordenadas_destino[0] && linhas[i].getEndY() == coordenadas_destino[1]) ||
          (linhas[i].getStartX() == coordenadas_destino[0] && linhas[i].getStartY() == coordenadas_destino[1] && linhas[i].getEndX() == coordenadas_origem[0] && linhas[i].getEndY() == coordenadas_origem[1]))
            linhas[i].setStroke(Color.rgb(255, 0, 0));
        }//fim do if
      }//fim do for
      index++;
    }//fim do while
  }//fim do desenhar_no_grafo

/* ***************************************************************
* Metodo: coordenadas
* Funcao: setar as coordenadas do roteador para identificar qual linha a ser manipulada
* Parametros: roteador
* Retorno: vetor de double, pois as posicoes das linhas estao em numeros fracionarios
*************************************************************** */
  public double[] coordenadas (int roteador){
    double[] coordenadas = new double[2];
    String id_origem = "Roteador"+roteador;
    for (int i = 0; i < nodes.length; i++){
      if (id_origem.equals(nodes[i].getId())){
        coordenadas[0] = nodes[i].getLayoutX()+10;
        coordenadas[1] = nodes[i].getLayoutY()+10;
        return coordenadas;
      }//fim do if
    }//fim do for
    return null;
  }//fim do metodo coordenadas
}//fim da classe GerarGrafo
