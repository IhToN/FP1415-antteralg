package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.SortedSet;

public interface Profesor extends Persona {
	public Categoria getCategoria();

	public void setCategoria(Categoria c);

	public SortedSet<Tutoria> getTutorias();

	public void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos,
			DayOfWeek dia);

	public void borraTutoria(LocalTime horaComienzo, DayOfWeek dia);

	public void borraTutorias();

	/* Boletin 6 */
	public Departamento getDepartamento();

	public void setDepartamento(Departamento dep);

	/* Boletin 7 */
	public List<Asignatura> getAsignaturas();

	public List<Double> getCreditos();

	public Double getDedicacionTotal();

	public void imparteAsignatura(Asignatura asig, Double dedicacion);

	public Double dedicacionAsignatura(Asignatura asig);

	public void eliminaAsignatura(Asignatura asig);
}
