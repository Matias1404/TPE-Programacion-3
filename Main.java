import java.util.List;

import Node.Tarea;

public class Main {

	public static void main(String args[]) {
		
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");

		//Servicio 1
		Tarea task = servicios.servicio1("T1");
		System.out.println(task.toString());

		//Servicio 2
		List<Tarea> tareas = servicios.servicio2(false);
		for (Tarea tarea : tareas) {
			System.out.println(tarea.getNombre());
		}

		//Servicio 3
		List<Tarea> tareas2 = servicios.servicio3(57,71);
		for (Tarea tarea : tareas2) {
			System.out.println(tarea.getNombre());
		}
	}
}
