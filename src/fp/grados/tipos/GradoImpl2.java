package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GradoImpl2 extends GradoImpl {

	public GradoImpl2(String nombre, Set<Asignatura> asignaturasObligatorias,
			Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas) {
		super(nombre, asignaturasObligatorias, asignaturasOptativas,
				numeroMinimoCreditosOptativas);
	}

	public Double getNumeroTotalCreditos() {
		Double res = getAsignaturasObligatorias().stream()
				.mapToDouble(a -> a.getCreditos()).sum()
				+ getNumeroMinimoCreditosOptativas();
		return res;
	}

	public Set<Asignatura> getAsignaturas(Integer curso) {
		Set<Asignatura> res = new HashSet<>();

		res.addAll(seleccionaAsignaturas(getAsignaturasObligatorias(), curso));
		res.addAll(seleccionaAsignaturas(getAsignaturasOptativas(), curso));
		return res;
	}

	private Set<Asignatura> seleccionaAsignaturas(Set<Asignatura> asignaturas,
			Integer curso) {
		Set<Asignatura> res = asignaturas.stream()
				.filter(a -> a.getCurso().equals(curso))
				.collect(Collectors.toSet());
		return res;
	}

}
