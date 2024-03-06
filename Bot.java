// Importar las clases necesarias
import java.awt.Desktop;
import java.net.URI;

// Crear una clase Bot
public class Bot {

  // Crear un método para abrir la página de YouTube
  public void abrirYouTube() {
    try {
      // Obtener el objeto Desktop del sistema
      Desktop desktop = Desktop.getDesktop();
      // Crear un objeto URI con la dirección de YouTube
      URI uri = new URI("https://www.youtube.com");
      // Abrir la página de YouTube usando el método browse del objeto Desktop
      desktop.browse(uri);
    } catch (Exception e) {
      // Manejar las posibles excepciones
      e.printStackTrace();
    }
  }

  // Crear un método principal para probar el bot
  public static void main(String[] args) {
    // Crear un objeto Bot
    Bot bot = new Bot();
    // Llamar al método abrirYouTube del objeto Bot
    bot.abrirYouTube();
  }
}
