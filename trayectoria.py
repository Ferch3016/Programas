import numpy as np
import matplotlib.pyplot as plt

# Define la función que representa la solución del sistema
def solucion(t, c1, c2, lambda1, lambda2, X1, X2):
    return c1 * np.exp(lambda1 * t) * X1 + c2 * np.exp(lambda2 * t) * X2

# Parámetros del sistema (sustituye con tus valores reales)
c1, c2 = 1, 0
lambda1, lambda2 = -2, -4
X1, X2 = np.array([1, 1]), np.array([1, -1])

# Genera valores de tiempo
t_values = np.linspace(0, 5, 100)

# Calcula las coordenadas x e y para cada valor de tiempo
x_values = solucion(t_values, c1, c2, lambda1, lambda2, X1[0], X2[0])
y_values = solucion(t_values, c1, c2, lambda1, lambda2, X1[1], X2[1])

# Crea la gráfica
plt.figure(figsize=(8, 6))
plt.plot(x_values, y_values, label='Trayectoria')

# Marca el punto de la condición inicial
plt.scatter(c1 * X1[0] + c2 * X2[0], c1 * X1[1] + c2 * X2[1], color='red', label='Condición Inicial')

# Configura la gráfica
plt.title('Plano Fase')
plt.xlabel('$x$')
plt.ylabel('$y$')
plt.legend()
plt.grid(True)

# Guarda la gráfica en un archivo PNG
plt.savefig('trayectoria.png')

# Muestra la gráfica en pantalla (opcional)
plt.show()
