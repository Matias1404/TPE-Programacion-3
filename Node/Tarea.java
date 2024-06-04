package Node;

public class Tarea {
  private String nombre;
  private int tiempo;
  private boolean critica;
  private int prioridad;
  private boolean asignada;

  public Tarea(String nombre, int tiempo, boolean critica, int prioridad) {
    this.nombre = nombre;
    this.tiempo = tiempo;
    this.critica = critica;
    this.prioridad = prioridad;
    this.asignada = false;
  }

  @Override
  public String toString(){
    String retorno = "Nombre: " + this.nombre + "\n" +
                    "Tiempo:" + this.tiempo + "\n" +
                    "Critica:" + this.critica + "\n" +
                    "Prioridad:" + this.prioridad;
    return retorno;
  }

  public String getNombre() {
    return this.nombre;
  }

  public int getTiempo() {
    return this.tiempo;
  }

  public boolean getCritica() {
    return this.critica;
  }

  public int getPrioridad() {
    return this.prioridad;
  }

  public boolean getAsignada() {
    return this.asignada;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setTiempo(int tiempo) {
    this.tiempo = tiempo;
  }

  public void setCritica(boolean critica) {
    this.critica = critica;
  }

  public void setPrioridad(int prioridad) {
    this.prioridad = prioridad;
  }

  public void setAsignada(boolean asignada) {
    this.asignada = asignada;
  }
}
