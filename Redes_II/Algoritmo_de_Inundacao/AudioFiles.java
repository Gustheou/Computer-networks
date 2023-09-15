/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 13/02/2023
* Ultima alteracao.: /02/2023
* Nome.............: AudioFiles
* Funcao...........: Inicializar/Parar as musicas
*************************************************************** */
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioFiles {
  
  public static void audioInitiliazeMain(File musicFile, Clip clip){
    try {
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
      Principal.clip = AudioSystem.getClip();
      Principal.clip.open(audioStream);
      Principal.clip.start();
    } catch (Exception e) {
      System.out.println("Excecao na musica: " + e.getMessage());
    }
  }

  public static void terminateSongMain(Clip clip){
    Principal.clip.stop();
    Principal.clip.close();
  }
  
  public static void audioInitializeOpcao1(File musicFile, Clip clip){
    try {
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
      ControleOpcao1.clip = AudioSystem.getClip();
      ControleOpcao1.clip.open(audioStream);
      ControleOpcao1.clip.start();
      ControleOpcao1.clip.stop();
      ControleOpcao1.clip.close();
    } catch (Exception e) {
      System.out.println("Excecao na musica: " + e.getMessage());
    }
  }

  public static void audioStartButtonOpcao1(File musicFile, Clip clip){
    try {
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
      ControleOpcao1.clip = AudioSystem.getClip();
      ControleOpcao1.clip.open(audioStream);
      ControleOpcao1.clip.start();
    } catch (Exception e) {
      System.out.println("Excecao na musica: " + e.getMessage());
    }
  }
  
  public static void audioInitializeOpcao2(File musicFile, Clip clip){
    try {
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
      ControleOpcao2.clip = AudioSystem.getClip();
      ControleOpcao2.clip.open(audioStream);
      ControleOpcao2.clip.start();
      ControleOpcao2.clip.stop();
      ControleOpcao2.clip.close();
    } catch (Exception e) {
      System.out.println("Excecao na musica: " + e.getMessage());
    }
  }

  public static void audioStartButtonOpcao2(File musicFile, Clip clip){
    try {
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
      ControleOpcao2.clip = AudioSystem.getClip();
      ControleOpcao2.clip.open(audioStream);
      ControleOpcao2.clip.start();
    } catch (Exception e) {
      System.out.println("Excecao na musica: " + e.getMessage());
    }
  }

  public static void audioInitializeOpcao3(File musicFile, Clip clip){
    try {
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
      ControleOpcao3.clip = AudioSystem.getClip();
      ControleOpcao3.clip.open(audioStream);
      ControleOpcao3.clip.start();
      ControleOpcao3.clip.stop();
      ControleOpcao3.clip.close();
    } catch (Exception e) {
      System.out.println("Excecao na musica: " + e.getMessage());
    }
  }

  public static void audioStartButtonOpcao3(File musicFile, Clip clip){
    try {
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
      ControleOpcao3.clip = AudioSystem.getClip();
      ControleOpcao3.clip.open(audioStream);
      ControleOpcao3.clip.start();
    } catch (Exception e) {
      System.out.println("Excecao na musica: " + e.getMessage());
    }
  }
}
