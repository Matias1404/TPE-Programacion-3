import java.util.List;
import java.util.Scanner;

import Node.Procesador;
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

		// Servicio 4
		System.out.print("Ingresa tiempo limite para tomar una tarea en los procesadores no refrigerado:");
		Scanner scanner = new Scanner(System.in);
		int tiempoLimite = scanner.nextInt();
		Solucion solucionBacktracking = servicios.backtracking(tiempoLimite);
		System.out.println("Solucion obtenida backtracking:");
		System.out.println("Metrica estados: " + solucionBacktracking.getMetrica());
		System.out.println("Tiempo final de ejecucion: " + solucionBacktracking.getTiempoFinalEjecucion());
		for(Procesador procesador : solucionBacktracking.getListaProcesadores()){
			System.out.println("Codigo procesador " + procesador.getCodigoProcesador() + " Tareas asignadas: ");
			for(Tarea tarea : procesador.getListaTareasAsignadas()){
				System.out.println("Nombre tarea: " + tarea.getNombre() + " Tiempo tarea: " + tarea.getTiempo());
			}
		}
	}
}
