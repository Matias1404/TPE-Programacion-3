package utils;

import java.util.Comparator;
import Node.Tarea;

public class ComparadorTarea implements Comparator<Tarea> {
    @Override
    public int compare(Tarea t1, Tarea t2) {
        return Integer.compare(t2.getTiempo(), t1.getTiempo());
    }
}