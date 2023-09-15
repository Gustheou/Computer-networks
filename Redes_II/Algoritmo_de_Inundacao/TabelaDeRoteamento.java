/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 18/02/2023
* Ultima alteracao.: 18/02/2023
* Nome.............: TabelaDeRoteamento
* Funcao...........: Armazenar as coordenadas dos roteadores
*************************************************************** */
public abstract class TabelaDeRoteamento {

/* ***************************************************************
* Metodo: tabela_de_roteamento
* Funcao: retornar as coordenadas dos roteadores
* Parametros: roteador
* Retorno: vetor de coordenadads; posicao 0 = eixo x; posicao 1 = eixo y
*************************************************************** */
  public static int [] tabela_de_roteamento(int roteador){
    int[] coordenadas = new int[2]; // 0 = x; 1 = y;
    switch (roteador){
      case 0: {//Host
        coordenadas[0] = 118;
        coordenadas[1] = 227;
        return coordenadas;
      }
      case 1: {
        coordenadas[0] = 267;
        coordenadas[1] = 196;
        return coordenadas;
      }
      case 2: {
        coordenadas[0] = 218;
        coordenadas[1] = 286;
        return coordenadas;
      }
      case 3: {
        coordenadas[0] = 201;
        coordenadas[1] = 371;
        return coordenadas;
      }
      case 4: {
        coordenadas[0] = 326;
        coordenadas[1] = 326;
        return coordenadas;
      }
      case 5: {
        coordenadas[0] = 419;
        coordenadas[1] = 153;
        return coordenadas;
      }
      case 6: {
        coordenadas[0] = 470;
        coordenadas[1] = 237;
        return coordenadas;
      }
      case 7: {
        coordenadas[0] = 549;
        coordenadas[1] = 153;
        return coordenadas;
      }
      case 8:{//Destino
        coordenadas[0] = 454;
        coordenadas[1] = 386;
        return coordenadas;
      }
      default: {
        return null;
      }
    }
  }
}
