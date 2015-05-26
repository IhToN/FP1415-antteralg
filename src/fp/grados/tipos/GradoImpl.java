package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionGradoNoValido;

public class GradoImpl implements Grado {
	private String nombre;
	private Set<Asignatura> asignaturasObligatorias;
	private Set<Asignatura> asignaturasOptativas;
	private Double numeroMinimoCreditosOptativas;

	public GradoImpl(String nombre, Set<Asignatura> asignaturasObligatorias,
			Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas) {
		checkCreditosOptativas(asignaturasOptativas);
		checkNumeroMinimoCreditosOptativas(asignaturasOptativas,
				numeroMinimoCreditosOptativas);
		this.nombre = nombre;
		this.asignaturasObligatorias = asignaturasObligatorias;
		this.asignaturasOptativas = asignaturasOptativas;
		this.numeroMinimoCreditosOptativas = numeroMinimoCreditosOptativas;
	}

	private void checkCreditosOptativas(Set<Asignatura> asignaturasOptativas) {
		Double creditosAnterior = -1.0;
		for (Asignatura a : asignaturasOptativas) {
			if (creditosAnterior == -1.0)
				creditosAnterior = a.getCreditos();
			else if (!a.getCreditos().equals(creditosAnterior))
				throw new ExcepcionGradoNoValido(
						"GradoImpl.checkCreditosOptativas:: Las optativas han de tener el mismo número de créditos.");
		}
	}

	private void checkNumeroMinimoCreditosOptativas(
			Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas) {
		Double creditosOptativas = 0.0;
		for (Asignatura a : asignaturasOptativas) {
			creditosOptativas += a.getCreditos();
		}
		if (numeroMinimoCreditosOptativas < 0.0
				|| numeroMinimoCreditosOptativas > creditosOptativas)
			throw new ExcepcionGradoNoValido(
					"GradoImpl.checkNumeroMinimoCreditosOptativas:: Los créditos optativos deben estar entre 0 y el total de creditos optativos");
	}

	public int compareTo(Grado o) {
		return getNombre().compareTo(o.getNombre());
	}

	public boolean equals(Object o) {
		if (o instanceof Grado) {
			Grado g = (Grado) o;
			return getNombre().equals(g.getNombre());
		}
		return false;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		return prime * result + getNombre().hashCode();
	}

	public String toString() {
		return this.getNombre();
	}

	public String getNombre() {
		return this.nombre;
	}

	public Double getNumeroMinimoCreditosOptativas() {
		return this.numeroMinimoCreditosOptativas;
	}

	public Set<Asignatura> getAsignaturasObligatorias() {
		return new TreeSet<Asignatura>(this.asignaturasObligatorias);
	}

	public Set<Asignatura> getAsignaturasOptativas() {
		return new TreeSet<Asignatura>(this.asignaturasOptativas);
	}

	public Double getNumeroTotalCreditos() {
		Double ret = this.getNumeroMinimoCreditosOptativas();
		for (Asignatura ob : getAsignaturasObligatorias()) {
			ret += ob.getCreditos();
		}
		return ret;
	}

	public Set<Departamento> getDepartamentos() {
		Set<Departamento> ret = new HashSet<Departamento>();
		for (Asignatura ob : getAsignaturasObligatorias()) {
			ret.add(ob.getDepartamento());
		}
		for (Asignatura op : getAsignaturasOptativas()) {
			ret.add(op.getDepartamento());
		}
		return ret;
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> ret = new TreeSet<Profesor>();
		for (Departamento d : getDepartamentos()) {
			ret.addAll(d.getProfesores());
		}
		return ret;
	}

	public Set<Asignatura> getAsignaturas(Integer curso) {
		Set<Asignatura> ret = new TreeSet<Asignatura>();
		for (Asignatura ob : getAsignaturasObligatorias()) {
			if (ob.getCurso() == curso)
				ret.add(ob);
		}
		for (Asignatura op : getAsignaturasOptativas()) {
			if (op.getCurso() == curso)
				ret.add(op);
		}
		return ret;
	}

	public Asignatura getAsignatura(String codigo) {
		Asignatura ret = null;
		for (Asignatura ob : getAsignaturasObligatorias()) {
			if (ob.getCodigo().equals(codigo)) {
				ret = ob;
				break;
			}
		}
		if (ret == null) {
			for (Asignatura op : getAsignaturasOptativas()) {
				if (op.getCodigo().equals(codigo)) {
					ret = op;
					break;
				}
			}
		}
		return ret;
	}

	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		SortedMap<Asignatura, Double> res = new TreeMap<Asignatura, Double>();
		for (Asignatura aob : this.getAsignaturasObligatorias()) {
			res.put(aob, aob.getCreditos());
		}
		for (Asignatura aop : this.getAsignaturasOptativas()) {
			res.put(aop, aop.getCreditos());
		}
		return res;
	}

	/* Boletín 12 */
	public SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas() {
		Comparator<Departamento> cmp = Comparator.comparing(
				(Departamento d1) -> d1.getAsignaturas().size()).reversed();
		// Otra opción: Comparator<Departamento> cmp = Comparator.comparing(d1
		// -> d1.getAsignaturas().size(), Comparator.reversedOrder());
		SortedSet<Departamento> res = new TreeSet<>(cmp.thenComparing(Comparator.naturalOrder()));
		res.addAll(getDepartamentos());
		return res;
	}

}
