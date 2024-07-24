package main.controllers;

import java.util.List;
import main.models.Estudiante;
import java.util.ArrayList;
import java.util.HashMap;

public class EstudianteController {

    public HashMap<String, List<Estudiante>> procesarEstudiantes(Estudiante[] estudiantes) {
        quickSort(estudiantes, 0, estudiantes.length - 1);

        HashMap<String, List<Estudiante>> categorias = new HashMap<>();
        for (Estudiante estudiante : estudiantes) {
            String categoria = obtenerCategoria(estudiante.getCalificacion());
            categorias.computeIfAbsent(categoria, k -> new ArrayList<>()).add(estudiante);
        }
        return categorias;
    }

    private void quickSort(Estudiante[] estudiantes, int low, int high) {
        if (low < high) {
            int pi = partition(estudiantes, low, high);
            quickSort(estudiantes, low, pi - 1);
            quickSort(estudiantes, pi + 1, high);
        }
    }

    private int partition(Estudiante[] estudiantes, int low, int high) {
        int pivot = estudiantes[high].getCalificacion();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (estudiantes[j].getCalificacion() <= pivot) {
                i++;
                Estudiante temp = estudiantes[i];
                estudiantes[i] = estudiantes[j];
                estudiantes[j] = temp;
            }
        }
        Estudiante temp = estudiantes[i + 1];
        estudiantes[i + 1] = estudiantes[high];
        estudiantes[high] = temp;
        return i + 1;
    }

    public String obtenerCategoria(int calificacion) {
        if (calificacion >= 90) {
            return "A";
        } else if (calificacion >= 80) {
            return "B";
        } else if (calificacion >= 70) {
            return "C";
        } else if (calificacion >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
