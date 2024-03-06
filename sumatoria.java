public class sumatoria { // Define la clase pública 
    public static void main(String[] args) { // Define el método principal
        int m = 10000; // Declara una variable entera "m" y la inicializa con 1000. Esta es la cantidad de iteraciones que se realizarán.
        double sum = 0.0; // Declara una variable de punto flotante "sum" y se inicia con 0.0. Esta variable se utiliza para acumular la suma de los términos de la serie.
        for (int n = 0; n < m; n++) { // Inicia un bucle for que se ejecuta "m" veces. En cada iteración, "n" aumenta en 1.
            sum += Math.pow(-1, n) / (2.0 * n + 1); // Calcula el término actual de la serie (-1)^n / (2n + 1) y lo suma a "sum".
        }
        double result = 4 * sum; // Multiplica la suma acumulada por 4 para obtener el resultado final.
        System.out.println("El resultado de la serie es: " + result); // Imprime el resultado final.
    }
}