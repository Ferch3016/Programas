import numpy as np

def estimar_pi(M):
    D = 0
    for i in range(1, M+1):
        x = np.random.uniform(-1, 1)
        y = np.random.uniform(-1, 1)
        d = np.sqrt(x**2 + y**2)
        if d <= 1:
            D += 1
    pi_estimado = 4 * D / M
    print("Estimación de pi con", M, "iteraciones:", pi_estimado)
    return pi_estimado

# Ejemplo de uso: 
estimacion = estimar_pi(10000)

