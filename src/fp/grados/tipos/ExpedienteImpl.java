package fp.grados.tipos;

import java.util.ArrayList;
import java.util.List;

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
			if (n.getValor() >= 5.0){
				ret += n.getValor();
				i++;
			}
		}
		if(i > 0)
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
}
