

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Collections;
import Node.*;
import utils.CSVReader;
import utils.ComparadorTarea;


/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	private static final boolean Tarea = false;
	private CSVReader reader;

	//O(n*m) siendo n la cantidad de filas del archivo y siendo m la cantidad de elementos de la fila
	public Servicios(String pathProcesadores, String pathTareas){
		this.reader = new CSVReader();
		this.reader.readProcessors(pathProcesadores);
		this.reader.readTasks(pathTareas);
	}
	
	//O(1) ya que acceder a un valor dado una key, la estructura HashMap utiliza una funcion hash para determinar la ubicacion por lo que la busqueda es de tiempo constante.
	public Tarea servicio1(String ID) {	
		HashMap<String, Tarea> tareas = this.reader.getHashMapTareas();
		Tarea tarea = tareas.get(ID);
		return tarea;
	}

    //O(1) ya que al tener las listas de tareas criticas y no criticas instanciadas, solo implica hacer una comparacion y retornar la lista solicitada
	public List<Tarea> servicio2(boolean esCritica){
		if (esCritica) {
			return this.reader.getCriticas();
		} else {
			return this.reader.getNoCriticas();
		}
	}

    //O(n) n siendo la cantidad de tareas dentro del HashMap
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior){
		HashMap<String, Tarea> tareas = this.reader.getHashMapTareas();
		List<Tarea> listaTareas = new ArrayList<Tarea>();
		for (String id : tareas.keySet()){
			Tarea tarea = tareas.get(id);
			if (tarea.getPrioridad() >= prioridadInferior && tarea.getPrioridad() <= prioridadSuperior) {
				listaTareas.add(tarea);
			}
		}
		return listaTareas;
	}

  /* La idea principal del backtracking es iterar sobre la lista de tareas a asignar y ir removiendo una a una cuando se llama recursivamente, para esta ser removida debera 
			de ser asignada a un procesador, teniendo en cuenta las dos condiciones de podas dadas en el enunciado, estas podas se encuentran representadas en los if de la linea 84 y 85
			Una vez que todas las tareas se asignen, revisaremos si la solucion parcial encontrada es valida, en caso de serlo nos quedaremos con esta solucion siempre y cuando sea mejor que 
			la ya obtenida y seguiremos iterando hasta que ya no queden mas casos que probar */

	public Solucion backtracking(int tiempoLimite) {
		Solucion solucion = new Solucion();
		this.resolverBacktracking(this.reader.getListaTareas(), this.reader.getListaProcesadores(), tiempoLimite, solucion);
		return solucion;
	}
	
	private void resolverBacktracking(List<Tarea> tareas, List<Procesador> procesadores, int tiempoLimite, Solucion solucion) {
		if (tareas.isEmpty())
		{
			int tiempo_final = calcularTiempoFinal(procesadores);
			if(solucion.isSolucion(tiempo_final))
			{
				solucion.setTiempoFinalEjecucion(tiempo_final);
				solucion.setProcesadores(procesadores);
			}
		}
		else 
		{
			List<Tarea> tareasCopia = new ArrayList<Tarea>(tareas); 
			for (Tarea tarea : tareas) {
					for (Procesador procesador : procesadores) {
							if ((procesador.getCantTareasCriticas() == 2 && !tarea.getCritica()) || (procesador.getCantTareasCriticas() < 2)) {
									if ((!procesador.getRefrigerado() && tarea.getTiempo() <= tiempoLimite) || (procesador.getRefrigerado())) {
											solucion.AddMetrica();
											procesador.asignarTarea(tarea);
											tareasCopia.remove(tarea);
											resolverBacktracking(tareasCopia, procesadores, tiempoLimite, solucion);
											tareasCopia.add(tarea);
											procesador.desasignarTarea(tarea);
									}
							}
					}
			}
		}
	}

	private int calcularTiempoFinal(List<Procesador> procesadores){
		int tiempo_parcial = 0;
		int tiempo_final = 0;
		for(Procesador procesador : procesadores)
		{
			for(Tarea tarea : procesador.getListaTareasAsignadas())
				tiempo_parcial+= tarea.getTiempo();
			if (tiempo_parcial > tiempo_final)
				tiempo_final = tiempo_parcial;
			tiempo_parcial = 0;
		}
		return tiempo_final;
	}

	/* Para resolver este problema utilizando la tecnica Greedy lo primero que se hara es ordenar la lista de tareas de mayor a menor por su atributo tiempo, ya que luego de realizar pruebas
			observamos que ordenando de mayor a menor Greedy nos devolvia la mejor solucion entre los casos de ordenar de menor a mayor o dejarla desordenada.
			La idea seria tomar una tarea y recorrer la lista de procesadores, recorriendo esta lista lo que se quiere lograr es buscar el procesador que menor tiempo de ejecucion tenga actualmente 
			y que ademas cumpla con las condiciones de la poda, una vez encontrado este procesador asignaremos la tarea al mismo y avanzaremos con la siguiente tarea y asi sucesivamente 
			hasta que todas esten asignadas. */

	public Solucion greedy(int tiempoLimite){
		Solucion solucion = new Solucion();
		this.resolverGreedy(this.reader.getListaTareas(), this.reader.getListaProcesadores(), tiempoLimite, solucion);
		return solucion;
	}

	private void resolverGreedy(List<Tarea> tareas, List<Procesador> procesadores, int tiempoLimite, Solucion solucion){
		Collections.sort(tareas, new ComparadorTarea());
		Iterator<Tarea> itTareas = tareas.iterator();
		boolean asignada = true;

		while(itTareas.hasNext() && asignada){
			Tarea tarea = itTareas.next();
			Procesador procesadorAAsignar = this.seleccionarProcesador(tarea, procesadores, tiempoLimite, solucion);
			if (procesadorAAsignar != null)
				procesadorAAsignar.asignarTarea(tarea);
			else
				asignada = false;
		}

		if (asignada) {
			int tiempo_final = calcularTiempoFinal(procesadores);
			solucion.setTiempoFinalEjecucion(tiempo_final);
			solucion.setProcesadores(procesadores);
		}
	}

	private Procesador seleccionarProcesador(Tarea tarea, List<Procesador> procesadores, int tiempoLimite, Solucion solucion) {
		Procesador retorno = null;
		for (Procesador procesador : procesadores) {
			solucion.AddMetrica();
			if ((procesador.getCantTareasCriticas() == 2 && !tarea.getCritica()) || (procesador.getCantTareasCriticas() < 2))
				if ((!procesador.getRefrigerado() && tarea.getTiempo() <= tiempoLimite) || (procesador.getRefrigerado()))
					if (retorno == null) 
						retorno = procesador;
					else if (retorno.getTiempoEjecucion() > procesador.getTiempoEjecucion())
						retorno = procesador;
		}
		return retorno;
	}
}
