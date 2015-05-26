package fp.grados.tipos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;

public class ExpedienteImpl implements Expediente {

	private List<Nota> notas;

	public ExpedienteImpl() {
		this.notas = new ArrayList<Nota>();
	}

	public List<Nota> getNotas() {
		return new ArrayList<Nota>(notas);
	}

	public void nuevaNota(Nota n) {
		maxConvocatorias(n);
		if (getNotas().contains(n)) {
			notas.remove(n);
			notas.add(n);
		} else
			notas.add(n);
	}

	public Double getNotaMedia() {
		Double ret = 0.0;
		int i = 0;
		for (Nota n : getNotas()) {
			if (n.getValor() >= 5.0) {
				ret += n.getValor();
				i++;
			}
		}
		if (i > 0)
			ret = ret / i;
		return ret;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getNotas().hashCode();
		return result;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Expediente) {
			Expediente a = (Expediente) obj;
			return getNotas().equals(a.getNotas());
		}
		return false;
	}

	public String toString() {
		return this.getNotas().toString();
	}

	private void maxConvocatorias(Nota n) {
		int cont = 0;
		for (Nota l : getNotas()) {
			if (l.getAsignatura().equals(n.getAsignatura())
					&& l.getCursoAcademico().equals(n.getCursoAcademico()))
				cont++;
		}
		if (cont > 1) {
			throw new ExcepcionExpedienteOperacionNoPermitida(
					"ExpedienteImpl.nuevaNota:: El alumno ya ha participado en dos convocatorias.");
		}
	}

	/** Boletín 12 **/
	public List<Nota> getNotasOrdenadasPorAsignatura() {
		Comparator<Nota> cmp = Comparator.comparing(
				(Nota n1) -> n1.getAsignatura()).thenComparing(
				Comparator.naturalOrder());
		List<Nota> res = new ArrayList<Nota>(notas.stream().sorted(cmp)
				.collect(Collectors.toList()));
		return res;
	}

	public Nota getMejorNota() {
		Comparator<Nota> cmp = Comparator.comparing(Nota::getMencionHonor)
				.thenComparing(Nota::getValor).reversed()
				.thenComparing(Nota::getConvocatoria).reversed()
				.thenComparing(n -> n.getAsignatura().getCurso()).reversed()
				.thenComparing(Comparator.naturalOrder());
		Nota res = notas.stream().sorted(cmp).findFirst().get();
		return res;
	}
}
