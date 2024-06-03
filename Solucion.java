import java.util.ArrayList;
import java.util.List;
import Node.Procesador;

public class Solucion {
  public Solucion resolver() {
    private int metrica;
    private List<Procesador> procesadores;
    private int tiempo_final_ejecucion;

    public Solucion(){
      this.metrica = 0;
      this.tiempo_final_ejecucion = 0;
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

    public void setTiempoFinalEjecucion(int tiempo)
    {
      this.tiempo_final_ejecucion = tiempo; 
    }

    public void setProcesadores(List<Procesador> procesadores)
    {
      this.procesadores = procesadores;
    }
}
