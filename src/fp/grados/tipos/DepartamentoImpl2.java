package fp.grados.tipos;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DepartamentoImpl2 extends DepartamentoImpl {

	public DepartamentoImpl2(String nombre) {
		super(nombre);
	}

	public void borraTutorias() {
		// getProfesores().stream().forEach(p -> p.borraTutorias());
		getProfesores().stream().forEach(Profesor::borraTutorias);
	}

	public void borraTutorias(Categoria categoria) {
		getProfesores().stream()
				.filter(p -> p.getCategoria().equals(categoria))
				.forEach(Profesor::borraTutorias);
	}

	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		Map<Profesor, SortedSet<Tutoria>> map = getProfesores().stream()
				.collect(Collectors.toMap(p -> p, p -> p.getTutorias()));
		SortedMap<Profesor, SortedSet<Tutoria>> res = new TreeMap<>(map);
		return res;
	}

	public Boolean existeProfesorAsignado(Asignatura a) {
		return getProfesores().stream()
				.filter(p -> p.getAsignaturas().contains(a)).findAny()
				.isPresent();
	}

	public Boolean estanTodasAsignaturasAsignadas() {
		getAsignaturas().stream();
		Boolean ret = true;
		for (Asignatura a : getAsignaturas()) {
			if (!existeProfesorAsignado(a)) {
				ret = false;
				break;
			}
		}
		return ret;
	}

	public void eliminaAsignacionProfesorado(Asignatura a) {
		getProfesores().stream().forEach(p -> p.eliminaAsignatura(a));
	}
}
