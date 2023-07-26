public class OGiratoria {

    public static void main(String[] args) throws InterruptedException {
        int diameter = 50; // Diámetro de la "O" giratoria (puedes ajustar este valor para hacerla más grande)

        int centerX = diameter / 2;
        int centerY = diameter / 2;

        // Bucle infinito para mantener la "O" giratoria en la pantalla
        while (true) {
            clearConsole(); // Limpia la consola

            // Dibuja la "O" giratoria en la consola
            for (int y = 0; y < diameter; y++) {
                for (int x = 0; x < diameter; x++) {
                    if ((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) <= (diameter / 2) * (diameter / 2)) {
                        System.out.print("* ");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println();
            }

            // Actualiza la posición del centro de la "O" para simular la rotación
            centerX = (centerX + 1) % diameter;
            centerY = (centerY + 1) % diameter;

            Thread.sleep(100); // Espera 100ms antes de redibujar la "O" (puedes ajustar el tiempo según prefieras)
        }
    }

    // Función para limpiar la consola (Windows, Linux, macOS)
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
        } catch (Exception ex) {
            // Manejo de excepciones
        }
    }
}
