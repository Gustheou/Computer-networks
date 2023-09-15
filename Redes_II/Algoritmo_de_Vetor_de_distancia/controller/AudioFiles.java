package controller;
/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 22/03/2023
* Ultima alteracao.: --/03/2023
* Nome.............: AudioFiles
* Funcao...........: Instanciar as musicas
*************************************************************** */
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioFiles {
  
  
  public static void audioMain(File musicFile){
    try {
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
      ControleTela.clip = AudioSystem.getClip();
      ControleTela.clip.open(audioStream);
      ControleTela.clip.start();
    } catch (Exception e) {
      System.out.println("Excecao na musica: " + e.getMessage());
    }
  }

  public static void terminateSongMain(){
    ControleTela.clip.stop();
    ControleTela.clip.close();
  }
  
}
