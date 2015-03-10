package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;

public interface Grado extends Comparable<Grado> {
	String getNombre();

	Double getNumeroMinimoCreditosOptativas();

	Set<Asignatura> getAsignaturasObligatorias();

	Set<Asignatura> getAsignaturasOptativas();

	// Propiedades derivadas:

	Double getNumeroTotalCreditos();

	Set<Departamento> getDepartamentos();

	Set<Profesor> getProfesores();

	// Otras operaciones:

	Set<Asignatura> getAsignaturas(Integer curso);

	Asignatura getAsignatura(String codigo);
	
	SortedMap<Asignatura, Double> getCreditosPorAsignatura();
}
