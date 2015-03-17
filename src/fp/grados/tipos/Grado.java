package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

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
	
	/* Boletín 12 */
	SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas();
}
