import java.util.Comparator;

public class ComparadorTarea implements Comparator<Tarea> {
    @Override
    public int compare(Tarea t1, Tarea t2) {
        return Integer.compare(t1.getTiempo(), t2.getTiempo());
    }
}