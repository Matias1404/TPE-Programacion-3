import java.util.ArrayList;
import java.util.List;
import Node.Procesador;
import Node.Tarea;

public class Solucion {
    private int metrica;
    private List<Procesador> procesadores;
    private int tiempo_final_ejecucion;

    public Solucion(){
      this.metrica = 0;
      this.tiempo_final_ejecucion = Integer.MAX_VALUE;
      this.procesadores = new ArrayList<Procesador>();
    }

    public boolean isSolucion(int tiempo_final_ejecucion) {
      return tiempo_final_ejecucion < this.tiempo_final_ejecucion;
    }

    public int getTiempoFinalEjecucion(){
      return this.tiempo_final_ejecucion;
    }

    public void AddMetrica(){
      this.metrica++;
    }

    public int getMetrica() {
      return this.metrica;
    }

    public void setTiempoFinalEjecucion(int tiempo)
    {
      this.tiempo_final_ejecucion = tiempo; 
    }

    public void setProcesadores(List<Procesador> procesadores)    
    { 
      this.procesadores.clear();
      for(Procesador procesador : procesadores)
      {
        Procesador procesadorAux = new Procesador(procesador.getCodigoProcesador(), 
          procesador.getRefrigerado(), 
          procesador.getAnio());

        for (Tarea tarea: procesador.getListaTareasAsignadas())
          procesadorAux.asignarTarea(tarea);

        this.procesadores.add(procesadorAux);
      }
    }

    public List<Procesador> getListaProcesadores(){
		  return new ArrayList<Procesador>(this.procesadores);
    }
}

