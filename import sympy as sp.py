import sympy as sp

# Definir las variables
x, y = sp.symbols('x y')

# Definir la ecuación
eq = y**3 - 6*x**2 + 11*y - 6

# Encontrar las soluciones
solutions = sp.solve((eq, sp.Eq(sp.diff(eq, y), 0)), (x, y), dict=True)

# Imprimir las soluciones
for sol in solutions:
    print(sol)
