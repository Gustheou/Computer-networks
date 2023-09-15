/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 16/02/2023
* Ultima alteracao.: /02/2023
* Nome.............: Roteador
* Funcao...........: Instanciar o roteador com suas coordenadas, enviar, receber pacotes
*************************************************************** */
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;

public class Roteador {
  private int x, y, id;
  private Subrede subrede;
  private List<Pacote> pacotesRecebidos = new ArrayList<>();
  public Roteador (int x, int y, int id, Subrede subrede) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.subrede = subrede;
  }

  public void enviar (Pacote pacote, Roteador roteador_origem, Subrede subrede){
    if (pacotesRecebidos.contains(pacote)) {
      return;
    }
    List<Roteador> vizinhos = subrede.getVizinhos(roteador_origem);
    
    pacotesRecebidos.add(pacote);
    for (Roteador vizinho : vizinhos){
      ImageView carta = new ImageView(Gallery.carta);
      Pacote pacotes = new Pacote (roteador_origem.getId(), carta, vizinho.getId());
      pacotes.start();
      // pacote.roteamentoOrigemOpcao1(roteador_origem.getId(), vizinho.getId());
      vizinho.receber(pacote, roteador_origem, subrede);
    }
    
  }

  public void receber (Pacote pacote, Roteador roteador_origem, Subrede subrede){
    if (pacote.getRoteadorDestino() == roteador_origem.getId()){
      pacote.getCarta().setVisible(false);
    } else {
      enviar (pacote, roteador_origem, subrede);
    }
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  
}
