package Node;

import java.util.List;
import java.util.ArrayList;
import Node.Tarea;

public class Procesador {
  private String codigo_procesador;
  private boolean refrigerado;
  private int anio;
  private List<Tarea> tareas_asignadas = new ArrayList<Tarea>();
  private int cant_tareas_criticas;
  private int tiempo_ejecucion;

  public Procesador(String codigo_procesador, boolean refrigerado, int anio) {
    this.codigo_procesador = codigo_procesador;
    this.refrigerado = refrigerado;
    this.anio = anio;
    this.cant_tareas_criticas = 0;
    this.tiempo_ejecucion = 0;
  }

  public String getCodigoProcesador() {
    return this.codigo_procesador;
  }

  public boolean getRefrigerado() {
    return this.refrigerado;
  }

  public int getAnio() {
    return this.anio;
  }

  public List<Tarea> getListaTareasAsignadas(){
		return new ArrayList<Tarea>(this.tareas_asignadas);
	}

  public int getCantTareasCriticas() {
    return this.cant_tareas_criticas;
  }

  public void setCodigoProcesador(String codigo_procesador) {
    this.codigo_procesador = codigo_procesador;
  }

  public void setRefrigerado(boolean refrigerado) {
    this.refrigerado = refrigerado;
  }

  public void setAnio(int anio) {
    this.anio = anio;
  }

  public void setCantTareasCriticas(int cant_tareas_criticas) {
    this.cant_tareas_criticas += cant_tareas_criticas ;
  }

  public void asignarTarea(Tarea tarea)
  {
    this.tiempo_ejecucion += tarea.getTiempo();
    this.tareas_asignadas.add(tarea);
  }

  public void desasignarTarea(Tarea tarea)
  {
    this.tiempo_ejecucion -= tarea.getTiempo();
    this.tareas_asignadas.remove(tarea);
  }

  public int getTiempoEjecucion(){
    return this.tiempo_ejecucion;
  }
}
