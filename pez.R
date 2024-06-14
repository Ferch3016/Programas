
# Llamar librerias
library(phytools)
library(ape)

# Cargar dataset
data(sunfish.data)
# Cargar arbol
data(sunfish.tree)

# Algunos ajustes extra
# Cambiar los nombres de hábito alimenticio (que es el caracter)
levels(sunfish.data$feeding.mode)<-c("no-piscivoro","piscivoro")

# Colores bonitos, ¡CAMBIA LOS COLORES!
cols<-setNames(c("#E4BE9E","#71697A"),levels(sunfish.data[[1]]))

# Extraer variable con el caracter de interés
habito_alimenticio <- setNames(sunfish.data$feeding.mode, rownames(sunfish.data))

# Modelo de ajuste suponiendo tasas iguales
modelo_ER <-fitMk(sunfish.tree,habito_alimenticio, model="ER")
print(modelo_ER)

# Modelo de ajuste suponiendo tasas diferentes
modelo_ARD <-fitMk(sunfish.tree,habito_alimenticio, model="ARD")
print(modelo_ARD)

# comparar modelos utilizando criterios AIC
AIC(modelo_ER,modelo_ARD)

# Semilla de aleatoriedad para tener resultados similares
set.seed(10)

# Mapeo estocástico con el modelo de elección, numero de escenarios = 100
# Recuerda cambiar el valor de X
mapeo_arboles_100 <-make.simmap(sunfish.tree,habito_alimenticio,model="ARD",nsim=100)

# Plotear
# Rejilla para cada arbol

# Plotear cada simulacion
sapply(mapeo_arboles_100, plotSimmap, colors=cols, lwd=2, ftype="i",
       fsize=.1)
legend("bottomleft",c("no
 piscivoro","piscivoro"),pch=21,pt.bg=cols,pt.cex=2)

# Iniciar la captura de la imagen en un archivo PNG
png("pez_luna_100X.png", res = 500, units = "in", height = 7, width = 5)

# Generar el gráfico
plot(summary(mapeo_arboles_100), colors=cols, ftype="i")
legend("topleft", c("no piscivoro", "piscivoro"), pch=21, pt.bg=cols, pt.cex=2)

# Aumentar el tamaño del texto de las etiquetas de los nodos terminales
nodelabels(cex=1.5)

# Detener la captura de la imagen y guardar el archivo PNG
dev.off()



