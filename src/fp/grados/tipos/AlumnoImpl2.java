package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AlumnoImpl2 extends AlumnoImpl {

	public AlumnoImpl2(String alumno) {
		super(alumno);
	}

	public AlumnoImpl2(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
	}

	public Integer getCurso() {
		Optional<Asignatura> asig = getAsignaturas().stream().max(
				Comparator.comparing(Asignatura::getCurso));
		Integer ret = 0;
		if (asig.isPresent())
			ret = asig.get().getCurso();
		return ret;
	}

	/** Boletin 13 **/
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {

		Map<Asignatura, Calificacion> map = getExpediente()
				.getNotas()
				.stream()
				.collect(
						Collectors.toMap(n -> n.getAsignatura(),
								n -> n.getCalificacion(),
								(d1, d2) -> d1.compareTo(d2) < 0 ? d2 : d1,
								TreeMap::new));
		SortedMap<Asignatura, Calificacion> res = new TreeMap<>(map);
		return res;
	}
}
