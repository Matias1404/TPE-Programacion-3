import java.util.List;
import java.util.Scanner;

import Node.Procesador;
import Node.Tarea;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");

		//Servicio 1
		System.out.println("-------------------servicio 1--------------------------");
		Tarea task = servicios.servicio1("T1");
		System.out.println(task.toString());
		System.out.println("-------------------------------------------------------");


		//Servicio 2
		System.out.println("-------------------servicio 2--------------------------");
		List<Tarea> tareas = servicios.servicio2(false);
		for (Tarea tarea : tareas) {
			System.out.println(tarea.getNombre());
		}
		System.out.println("-------------------------------------------------------");



		//Servicio 3
		System.out.println("-------------------servicio 3--------------------------");
		List<Tarea> tareas2 = servicios.servicio3(57,71);
		for (Tarea tarea : tareas2) {
			System.out.println(tarea.getNombre());
		}
		System.out.println("-------------------------------------------------------");

		System.out.print("Ingresa tiempo limite para tomar una tarea en los procesadores no refrigerado, esta sera utilizada por los servicio 4 y 5:");
		Scanner scanner = new Scanner(System.in);
		int tiempoLimite = scanner.nextInt();

		// Servicio 4
		System.out.println("-------------------servicio 4--------------------------");
		Solucion solucionBacktracking = servicios.backtracking(tiempoLimite);
		if (solucionBacktracking.getListaProcesadores().isEmpty())
			System.out.println("Para backtracking no se hayo una solucion");
		else{
			System.out.println("Solucion obtenida backtracking:");
			System.out.println("Metrica estados: " + solucionBacktracking.getMetrica());
			System.out.println("Tiempo final de ejecucion: " + solucionBacktracking.getTiempoFinalEjecucion());
			for(Procesador procesador : solucionBacktracking.getListaProcesadores()){
				System.out.println("Codigo procesador " + procesador.getCodigoProcesador() + "- Tareas asignadas: ");
				for(Tarea tarea : procesador.getListaTareasAsignadas()){
					System.out.println("    Nombre tarea: " + tarea.getNombre() + "; Tiempo tarea: " + tarea.getTiempo());
				}
			}
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------servicio 5--------------------------");

		// Servicio 5
		Solucion solucionGreedy = servicios.greedy(tiempoLimite);
		if (solucionGreedy.getListaProcesadores().isEmpty())
			System.out.println("Para greedy no se hayo una solucion");
		else{
			System.out.println("Solucion obtenida greedy:");
			System.out.println("Metrica estados: " + solucionGreedy.getMetrica());
			System.out.println("Tiempo final de ejecucion: " + solucionGreedy.getTiempoFinalEjecucion());
			for(Procesador procesador : solucionGreedy.getListaProcesadores()){
				System.out.println("Codigo procesador " + procesador.getCodigoProcesador() + "- Tareas asignadas: ");
				for(Tarea tarea : procesador.getListaTareasAsignadas()){
					System.out.println("    Nombre tarea: " + tarea.getNombre() + "; Tiempo tarea: " + tarea.getTiempo());
				}
			}
		}
	}
}
