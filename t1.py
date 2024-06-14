import re

secuencias = [
    "ATATATATACCTAGGTAATGGGGCGGTGTGTAAGTACTGTAGTGGGTAATGGGGCG",
    "GGACCACCCACCAACCATAATTGTGTTTGGGTTGCCCATCTCCTCCCTTCSSSSSGGAT",
    "TAAAGTGTTGGTGCCCSCGCACCGTGTCGTTCCTTCCGCCSSTCAGGC",
    "GTGCCGAACCCCCGAACCATCATCACSTCOTCACATTACTAATTCAAA",
    "CTGCCTTGCGTAGCCCGTAGCCCTCETGCATAOACTTGCETCCAAGTAGTTACTT",
    "GCCCCTCFSCGTTCGAATCGGCCETECTGSTCAOTBGTGCCACTGAATTACTT",
    "GGCOTCSCESTGTAOGGGSCGSCESTECTGSTCAOTTBGTGCCACTGAATTACTT",
    "AATTCGCCCOCOCCCCCOAACACAAGAACOGTTAATTCAAAAOOOOOOCCCCCCC",
    "ATTCOTCSTCOCOCCCCCOAACACAAGAACOG"
]

regiones_codificantes = []

# Expresión regular para regiones codificantes
expresion_regular = r'\b(ATG(?:[ACGT]{3})+(?:T(?:AG|AA|GA)))\b'

# Iterar sobre las secuencias y verificar si cumplen con la expresión regular
for i, secuencia in enumerate(secuencias):
    if re.search(expresion_regular, secuencia):
        regiones_codificantes.append(i)

print(regiones_codificantes)
