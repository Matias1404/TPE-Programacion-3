public class NodeTask {
  private String nombre;
  private int tiempo;
  private boolean critica;
  private int prioridad;

  public NodeTask(String nombre, int tiempo, boolean critica, int prioridad) {
    this.nombre = nombre;
    this.tiempo = tiempo;
    this.critica = critica;
    this.prioridad = prioridad;
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

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setRefrigerado(boolean refrigerado) {
    this.tiempo = tiempo;
  }

  public void setAnio(int anio) {
    this.critica = critica;
  }

  public void setPrioridad(int prioridad) {
    this.prioridad = prioridad;
  }
}