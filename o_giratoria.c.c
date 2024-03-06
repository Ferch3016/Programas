#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define DIAMETER 10 // Diámetro de la "o" giratoria

int main() {
    int x, y;
    int centerX = DIAMETER / 2;
    int centerY = DIAMETER / 2;

    // Bucle infinito para mantener la "o" giratoria en la pantalla
    while (1) {
        system("clear"); // Limpiar la pantalla (puedes usar "cls" en Windows)

        // Dibuja la "o" giratoria en la consola
        for (y = 0; y < DIAMETER; y++) {
            for (x = 0; x < DIAMETER; x++) {
                if ((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) <= (DIAMETER / 2) * (DIAMETER / 2)) {
                    printf("* ");
                } else {
                    printf("  ");
                }
            }
            printf("\n");
        }

        // Actualiza la posición del centro de la "o" para simular la rotación
        centerX = (centerX + 1) % DIAMETER;
        centerY = (centerY + 1) % DIAMETER;

        usleep(100000); // Espera 100ms antes de redibujar la "o" (puedes ajustar el tiempo según prefieras)
    }

    return 0;
}