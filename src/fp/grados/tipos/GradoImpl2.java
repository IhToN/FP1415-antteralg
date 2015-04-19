package fp.grados.tipos;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
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

	public Asignatura getAsignatura(String codigo) {
		Asignatura res = getAsignaturasObligatorias().stream()
				.filter(a -> a.getCodigo().equals(codigo)).findAny().get();
		if (res == null)
			res = getAsignaturasOptativas().stream()
					.filter(a -> a.getCodigo().equals(codigo)).findAny().get();
		return res;
	}

	@SuppressWarnings("unchecked")
	public Set<Departamento> getDepartamentos() {
		Set<Departamento> res = new HashSet<Departamento>();
		res.addAll((Collection<? extends Departamento>) getAsignaturasObligatorias()
				.stream().collect(Collectors.toSet()));
		res.addAll((Collection<? extends Departamento>) getAsignaturasOptativas()
				.stream().collect(Collectors.toSet()));
		return res;
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> res = new TreeSet<Profesor>();
		getDepartamentos().stream().forEach(d -> res.addAll(d.getProfesores()));
		return res;
	}

	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		SortedMap<Asignatura, Double> res = new TreeMap<Asignatura, Double>();
		getAsignaturasObligatorias().stream().forEach(
				a -> res.put(a, a.getCreditos()));
		getAsignaturasOptativas().stream().forEach(
				a -> res.put(a, a.getCreditos()));
		return res;
	}
}
