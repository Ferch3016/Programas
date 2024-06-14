from Bio import SeqIO
from Bio.Seq import Seq

# Descarga del archivo GenBank del virus SARS-CoV-2 (COVID-19)
# Supongamos que el archivo se llama "secuencia_sars_cov2.gb"

# Cargar la secuencia del archivo GenBank
secuencia = SeqIO.read("C:/Users/ST/sars_cov_2/genbank_file.gb", "genbank").seq

# Función para encontrar ORFs en una secuencia
def encontrar_orfs(secuencia):
    orfs = []
    for frame in range(3):
        for orf in secuencia[frame:].translate(to_stop=True).split("*"):
            if len(orf) > 30:  # Longitud mínima del ORF
                orfs.append(orf)
    return orfs

# Función para determinar si un ORF es espurio o no
def es_orf_no_espurio(orf, alpha):
    # Calcula la probabilidad de que el ORF sea espurio
    # Aquí se puede implementar algún criterio estadístico
    # Supongamos que usamos una longitud mínima de 90 nucleótidos como umbral
    if len(orf) > 90:
        return True
    else:
        return False

# Encontrar ORFs en la secuencia
orfs = encontrar_orfs(secuencia_sars_cov2)

# Contar el número total de ORFs
num_orfs_total = len(orfs)

# Contadores para ORFs viables y no espurios con diferentes niveles de significancia
num_orfs_viables = 0
num_orfs_no_espurios_05 = 0
num_orfs_no_espurios_01 = 0

# Iterar sobre los ORFs y contar los que sean viables y no espurios
for orf in orfs:
    num_orfs_viables += 1
    if es_orf_no_espurio(orf, 0.05):
        num_orfs_no_espurios_05 += 1
    if es_orf_no_espurio(orf, 0.01):
        num_orfs_no_espurios_01 += 1

# Resultados
print("Número total de ORFs encontrados:", num_orfs_total)
print("Número de ORFs viables:", num_orfs_viables)
print("Número de ORFs no espurios con una probabilidad menor a 0.05:", num_orfs_no_espurios_05)
print("Número de ORFs no espurios con una probabilidad menor a 0.01:", num_orfs_no_espurios_01)
