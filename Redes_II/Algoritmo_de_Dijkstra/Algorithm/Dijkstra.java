/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 24/02/2023
* Ultima alteracao.: 05/03/2023
* Nome.............: Dijkstra
* Funcao...........: Realizar o algoritmo de menor caminho
*************************************************************** */
package Algorithm;

import java.util.ArrayList;

public class Dijkstra {
  
  private int[][] tabela_de_roteamento;
  private String roteador_origem, roteador_destino;
  private ArrayList caminho_possivel = new ArrayList<>();
  private boolean[] no_visitado;
  private int somador;

/* ***************************************************************
* Construtor: Dijkstra
* Funcao: definir o tamanho do vetor de nos visitados, recuperar o id dos roteadores de origem e destino
* Parametros: tabela_de_roteamento = informacoes dos roteadores e seus vizinhos; roteador_origem = id parcial do roteador; roteador_destino = id parcial do roteador
*************************************************************** */
  public Dijkstra(int[][] tabela_de_roteamento, String roteador_origem, String roteador_destino){
    this.tabela_de_roteamento = tabela_de_roteamento;
    
    StringBuilder only_number_of_id_origin = new StringBuilder(roteador_origem);
    only_number_of_id_origin.reverse();
    only_number_of_id_origin.delete(1, only_number_of_id_origin.length());
    this.roteador_origem = only_number_of_id_origin.toString();
    
    StringBuilder only_number_of_id_destination = new StringBuilder(roteador_destino);
    only_number_of_id_destination.reverse();
    only_number_of_id_destination.delete(1, only_number_of_id_destination.length());
    this.roteador_destino = only_number_of_id_destination.toString();
    this.no_visitado = new boolean[tabela_de_roteamento.length];
  }

/* ***************************************************************
* Metodo: define_short_path
* Funcao: encontrar o melhor caminho
* Parametros: void
* Retorno: ArrayList = melhor caminho na ordem que deve ser executada
*************************************************************** */
  public ArrayList define_short_path (){
    int roteador_atual = Integer.parseInt(roteador_origem);
    int roteador_final = Integer.parseInt(roteador_destino);
    int menor_peso = 100;
    int somador_da_rota = 0;
    caminho_possivel.add(roteador_atual);
    for (int i = roteador_atual; i < tabela_de_roteamento.length; i++){
      if (i == roteador_atual && roteador_atual != roteador_final){
        for (int j = 0; j < tabela_de_roteamento.length; j++){
          if (no_visitado[i] == false && no_visitado[j] == false){
            if (tabela_de_roteamento[i][j] != 0){
              if (tabela_de_roteamento[i][j] <=  menor_peso){
                menor_peso = tabela_de_roteamento[i][j];
                roteador_atual = j;
                if (roteador_atual == roteador_final)
                  break;
              }//Fim do if
            }//Fim do if
          }//Fim do if
        }//Fim do for
        if (menor_peso == 100)
          menor_peso = 0;
        no_visitado[i] = true;
        caminho_possivel.add(roteador_atual);
        somador_da_rota += menor_peso;
        menor_peso = 100;
        i = roteador_atual-1;
      } else {
        break;
      }//Fim do if-else
    }//Fim do for
    setSomador(somador_da_rota);
    return caminho_possivel;
  }//Fim do metodo define_short_path

/* ***************************************************************
* Metodo: setSomador
* Funcao: setar o somador de pesos
* Parametros: somador = valor final da soma
* Retorno: void
*************************************************************** */
  public void setSomador(int somador){
    this.somador = somador;
  }

/* ***************************************************************
* Metodo: getSomador
* Funcao: retornar o valor somado para ser manipulado na classe GerarGrafo.java
* Parametros: void
* Retorno: int = soma dos pesos
*************************************************************** */
  public int getSomador(){
    return somador;
  }
}//Fim da classe Dijkstra