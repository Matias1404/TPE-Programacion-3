# Trabajo Práctico: Asignación de Tareas a Procesadores

## Descripción

Este proyecto resuelve distintos problemas asociados a un contexto simplificado de procesadores y tareas. Se divide en dos partes:

1. **Búsqueda de tareas:** Implementación de servicios de consulta sobre tareas.

2. **Asignación de tareas a procesadores:** Uso de técnicas algorítmicas para optimizar la distribución de tareas en los procesadores.


### Parte 1: Servicios de Búsqueda

Se implementan los siguientes servicios:

1. **Búsqueda por ID:** Dado un identificador de tarea, obtener su información.

2. **Filtrado por criticidad:** Listar todas las tareas críticas o no críticas.

3. **Filtrado por prioridad:** Obtener todas las tareas en un rango de prioridades.

### Parte 2: Asignación de Tareas a Procesadores

El objetivo es minimizar el tiempo final de ejecución bajo las siguientes restricciones:

- Un procesador no puede ejecutar más de 2 tareas críticas.

- Un procesador no refrigerado tiene un límite de tiempo de ejecución X.

#### Algoritmos Implementados

**Backtracking**

Explora todas las posibles asignaciones de tareas a procesadores para encontrar la solución óptima.

- Solución obtenida: Lista de procesadores con sus tareas asignadas.

- Tiempo máximo de ejecución.

- Costo de la solución: Cantidad de estados generados.

**Greedy**

Utiliza una estrategia voraz para asignar tareas de manera eficiente, aunque no garantiza la solución óptima.

- Solución obtenida: Lista de procesadores con sus tareas asignadas.

- Tiempo máximo de ejecución.

- Costo de la solución: Cantidad de candidatos considerados.
