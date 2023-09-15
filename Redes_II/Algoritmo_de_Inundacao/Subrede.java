/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 17/02/2023
* Ultima alteracao.: /02/2023
* Nome.............: ControleOpcao1
* Funcao...........: Controlar a subrede da interface, adicionando os roteadores aos respectivos nodes e seus vizinhos
*************************************************************** */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subrede {

  private Map<Roteador, List<Roteador>> nosVizinhos;

  public Subrede() {
    nosVizinhos = new HashMap<>();
  }

  public void adicionar_roteador(Roteador Roteador) {
    nosVizinhos.put(Roteador, new ArrayList<>());
  }

  public void adicionar_vizinho(Roteador Roteador1, Roteador Roteador2) {
    List<Roteador> vizinhoA = nosVizinhos.get(Roteador1);
    List<Roteador> vizinhoB = nosVizinhos.get(Roteador2);
    if (vizinhoA != null && vizinhoB != null) {
      vizinhoA.add(Roteador2);
      vizinhoB.add(Roteador1);
    }
  }

  public List<Roteador> getVizinhos(Roteador Roteador) {
    return nosVizinhos.get(Roteador);
  }
}
