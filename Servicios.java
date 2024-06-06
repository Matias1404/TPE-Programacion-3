

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Node.*;
import utils.CSVReader;


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

	// Explicar codigo, breve un parrafo como mucho
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
											resolver(tareasCopia, procesadores, tiempoLimite, solucion);
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

	//Explicar solucion
	public Solucion greedy(int tiempoLimite){
		return this.resolverGreedy(this.reader.getListaTareas(), this.reader.getListaProcesadores(), tiempoLimite, solucion);
	}

	private void resolverGreedy(List<Tarea> tareas, List<Procesador> procesadores, int tiempoLimite, int tiempoLimite, Solucion solucion){
		Iterator<Tarea> itTareas = tareas.iterator();
		boolean asignada = true;
		
		while(itTareas.hasNext() && asignada){
			tarea = it.next();
			Procesador procesadorAAsignar = this.seleccionarProcesador(tarea, procesadores, tiempoLimite, solucion)
			if (procesadorAAsignar != null)
				procesadorAAsignar.asignarTarea(Tarea)	
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
			solucion.AddMetrica()
			if ((procesador.getCantTareasCriticas() == 2 && !tarea.getCritica()) || (procesador.getCantTareasCriticas() < 2))
				if ((!procesador.getRefrigerado() && tarea.getTiempo() <= tiempoLimite) || (procesador.getRefrigerado()))
					if (retorno == null) 
						retorno = procesador;
					else if (retorno.getTiempoEjecucion() > procesador.getTiempoEjecucion)
						retorno = procesador;
		}
		return retorno;
	}
}
