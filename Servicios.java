

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

	public Solucion backtracking() {
		int tiempoLimite = 10;
		Solucion solucion = new Solucion();
		this.resolver(this.reader.getListaTareas(), this.reader.getListaProcesadores(), tiempoLimite, solucion);
		return solucion;
	}

	private void resolver(List<Tarea> tareas, List<Procesador> procesadores, int tiempoLimite, Solucion solucion) {
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
			for(Tarea tarea : tareas)
				for(Procesador procesador : procesadores)
					if (procesador.getCantTareasCriticas() <= 2 && !tarea.getCritica())
						if ((!procesador.getRefrigerado() && tarea.getTiempo() <= tiempoLimite) || (procesador.getRefrigerado()))
						{
							solucion.AddMetrica();
							procesador.addTarea(tarea);
							resolver(tareas,  procesadores, tiempoLimite, solucion);
							procesador.removeTarea(tarea);
						}
		}
	}

	private int calcularTiempoFinal(List<Procesador> procesadores){
		int tiempo_parcial = 0;
		int tiempo_final = 0;
		for(Procesador procesador : procesadores)
			for(Tarea tarea : procesador.getListaTareasAsignadas())
				tiempo_parcial+= tarea.getTiempo();
			if (tiempo_parcial > tiempo_final)
				tiempo_final = tiempo_parcial;
			tiempo_parcial = 0;
		return tiempo_final;
	}
}
