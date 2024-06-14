import re
import matplotlib.pyplot as plt
import numpy as np

# Función para contar los promotores GATA en una secuencia
def contar_promotores(secuencia):
    return len(re.findall(r'[AT]GATA[AG]', secuencia))

# Leer el archivo y contar los promotores
with open('promotores.txt', 'r') as file:
    secuencias = file.readlines()

conteos = [contar_promotores(sec.strip()) for sec in secuencias]

# Escribir los conteos en un archivo
with open('promotores_conteo.txt', 'w') as outfile:
    for sec, conteo in zip(secuencias, conteos):
        outfile.write(f'{sec.strip()} {conteo}\n')

# Crear un boxplot
plt.boxplot(conteos)
plt.title('Distribución de Promotores GATA')
plt.ylabel('Cantidad de Promotores')
plt.savefig('boxplot_promotores.png')
plt.show()

# Calcular la media y desviación estándar
media = np.mean(conteos)
desviacion_estandar = np.std(conteos)

print(f'Media: {media}')
print(f'Desviación Estándar: {desviacion_estandar}')
