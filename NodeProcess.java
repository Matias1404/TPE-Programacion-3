public class NodeProcess {
  private String codigo_procesador;
  private boolean refrigerado;
  private int anio;

  public NodeProcess(String codigo_procesador, boolean refrigerado, int anio) {
    this.codigo_procesador = codigo_procesador;
    this.refrigerado = refrigerado;
    this.anio = anio;
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

  public void setCodigoProcesador(String codigo_procesador) {
    this.codigo_procesador = codigo_procesador;
  }

  public void setRefrigerado(boolean refrigerado) {
    this.refrigerado = refrigerado;
  }

  public void setAnio(int anio) {
    this.anio = anio;
  }
}