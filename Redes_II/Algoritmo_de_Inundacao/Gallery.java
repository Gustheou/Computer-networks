/* ***************************************************************
* Autor............: Gustavo Pereira Nunes
* Inicio...........: 13/02/2023
* Ultima alteracao.: 18/02/2023
* Nome.............: Gallery
* Funcao...........: Guardar as imagens que são chamadas
*************************************************************** */
import javafx.scene.image.Image;

public abstract class Gallery {
  public static final Image carta = new Image(Gallery.class.getResourceAsStream("Imagens/Package.jpg"));
  public static final Image iniciarOnMouseEntered = new Image("Imagens/IniciarButtonPressed.png");
  public static final Image iniciarOnMouseExited = new Image("Imagens/IniciarButton.png");
  public static final Image voltarOnMouseEntered = new Image("Imagens/VoltarPressed.png");
  public static final Image voltarOnMouseExited = new Image("Imagens/Voltar.png");
  public static final Image soundExited = new Image ("Imagens/Sound_on.png");
  public static final Image soundEntered = new Image ("Imagens/Sound_off.png");
  public static final Image aboutExited = new Image("Imagens/About.png");
  public static final Image aboutEntered = new Image ("Imagens/AboutClicked.png");
  public static final Image aboutReturnButtonExited = new Image ("Imagens/AboutScreenBackButton.png");
  public static final Image aboutReturnButtonEntered = new Image ("Imagens/AboutScreenBackButtonOnMouse.png");
  public static final Image personaRollOut = new Image ("Imagens/PersonaAfterPackage.png");
  public static final Image personaStandBy = new Image (Gallery.class.getResourceAsStream("Imagens/PersonaBeforePackage.png"));
}
