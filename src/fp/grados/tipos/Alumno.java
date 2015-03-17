package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Alumno extends Persona {
	public Set<Asignatura> getAsignaturas();
	public Integer getCurso();
	
	public void matriculaAsignatura(Asignatura asig);
	public void eliminaAsignatura(Asignatura asig);
	public Boolean estaMatriculadoEn(Asignatura asig);
	
	/* Boletin 7 */
	public Expediente getExpediente();
	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota, Boolean mencionHonor);
	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota);
	
	/* Boletin 9 */
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura();
	
	/* Boletín 12 */
	public SortedSet<Asignatura> getAsignaturasOrdenadasPorCurso();
}
