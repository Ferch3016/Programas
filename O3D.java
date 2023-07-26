public class O3D {

    public static void main(String[] args) {
        int size = 10; // Tamaño de la "O" en 3D (puedes ajustar este valor)

        for (int z = -size; z <= size; z++) {
            for (int y = -size; y <= size; y++) {
                for (int x = -size; x <= size; x++) {
                    double distance = Math.sqrt(x * x + y * y + z * z);
                    if (distance > size - 0.5 && distance < size + 0.5) {
                        System.out.print("* ");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}