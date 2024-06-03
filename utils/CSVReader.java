package utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Node.*;


public class CSVReader {

	private HashMap<String, Procesador> procesadorHashMap = new HashMap<String, Procesador>();
	private HashMap<String, Tarea> tareaHashMap = new HashMap<String, Tarea>();
	private List<Tarea> criticas = new ArrayList<Tarea>();
	private List<Tarea> noCriticas = new ArrayList<Tarea>();
	private List<Tarea> tareasLista = new ArrayList<Tarea>();
	private List<Procesador> tareasProcesadores = new ArrayList<Procesador>();


	public CSVReader(){
	}

	public HashMap<String, Tarea> getHashMapTareas(){
		return (HashMap<String, Tarea>) this.tareaHashMap.clone();
	}

	public HashMap<String, Procesador> getHashMapProcesador(){
		return (HashMap<String, Procesador>) this.procesadorHashMap.clone();
	}

	public List<Tarea> getCriticas(){
		return new ArrayList<Tarea>(this.criticas);
	}

	public List<Tarea> getNoCriticas(){
		return new ArrayList<Tarea>(this.noCriticas);
	}

	public List<Tarea> getListaTareas(){
		return new ArrayList<Tarea>(this.tareasLista);
	}

	public List<Procesador> getListaProcesadores(){
		return new ArrayList<Procesador>(this.tareasProcesadores);
	}

	public void readTasks(String taskPath) {
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent(taskPath);

		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String id = line[0].trim();
			String nombre = line[1].trim();
			Integer tiempo = Integer.parseInt(line[2].trim());
			Boolean critica = Boolean.parseBoolean(line[3].trim());
			Integer prioridad = Integer.parseInt(line[4].trim());
			Tarea nodo = new Tarea(nombre, tiempo, critica, prioridad);
			this.tareaHashMap.put(id, nodo);
			this.tareasLista.add(nodo);
			if(critica){
				this.criticas.add(nodo);
			} else {
				this.noCriticas.add(nodo);
			}
		}
		
	}
	
	public void readProcessors(String processorPath) {
		
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent(processorPath);

		for (String[] line: lines) {
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String id = line[0].trim();
			String codigo = line[1].trim();
			Boolean refrigerado = Boolean.parseBoolean(line[2].trim());
			Integer anio = Integer.parseInt(line[3].trim());
			Procesador nodo = new Procesador(codigo, refrigerado, anio);
			this.procesadorHashMap.put(id, nodo);
			this.tareasProcesadores.add(nodo);
		}
	}

	private ArrayList<String[]> readContent(String path) {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}
	
}
