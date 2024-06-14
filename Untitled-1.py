
def convertir_a_FASTA(archivo_texto, archivo_FASTA):
    with open(archivo_texto, 'r') as txt_file:
        texto = txt_file.read()

    with open(archivo_FASTA, 'w') as fasta_file:
        fasta_file.write(">Secuencia desde archivo de texto\n")
        fasta_file.write(texto)

# Nombre del archivo de texto de entrada y nombre del archivo FASTA de salida
archivo_texto = "C:\\Users\\ST\\Desktop\\prac\\p1_loboSolitario.txt"


archivo_FASTA = "secuencia.fasta"

# Llamada a la función para convertir
convertir_a_FASTA(archivo_texto, archivo_FASTA)
