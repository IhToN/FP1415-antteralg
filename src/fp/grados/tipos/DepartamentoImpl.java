package fp.grados.tipos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class DepartamentoImpl implements Departamento {
	private String nombre;
	private Set<Profesor> profesores;
	private Set<Asignatura> asignaturas;

	public DepartamentoImpl(String nombre) {
		this.nombre = nombre;
		this.profesores = new HashSet<Profesor>();
		this.asignaturas = new HashSet<Asignatura>();
	}

	public int compareTo(Departamento arg0) {
		return this.getNombre().compareTo(arg0.getNombre());
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Departamento) {
			Departamento dept = (Departamento) o;
			res = this.getNombre().equals(dept.getNombre());
		}
		return res;
	}

	public int hashCode() {
		return this.getNombre().hashCode() * 31;
	}

	public String toString() {
		return this.getNombre();
	}

	public String getNombre() {
		return nombre;
	}

	public Set<Profesor> getProfesores() {
		return new HashSet<Profesor>(profesores);
	}

	public Set<Asignatura> getAsignaturas() {
		return new HashSet<Asignatura>(asignaturas);
	}

	public void nuevoProfesor(Profesor prof) {
		this.profesores.add(prof);
		prof.setDepartamento(this);
	}

	public void eliminaProfesor(Profesor prof) {
		if (this.profesores.remove(prof))
			prof.setDepartamento(null);
	}

	public void nuevaAsignatura(Asignatura asig) {
		this.asignaturas.add(asig);
		asig.setDepartamento(this);
	}

	public void eliminaAsignatura(Asignatura asig) {
		this.asignaturas.remove(asig);
		asig.setDepartamento(null);
	}

	public void borraTutorias() {
		for (Profesor p : getProfesores()) {
			p.borraTutorias();
		}
	}

	public void borraTutorias(Categoria c) {
		for (Profesor p : getProfesores()) {
			if (p.getCategoria().equals(c))
				p.borraTutorias();
		}
	}

	public Boolean existeProfesorAsignado(Asignatura a) {
		Boolean ret = false;
		for (Profesor p : getProfesores()) {
			if (p.getAsignaturas().contains(a)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	public Boolean estanTodasAsignaturasAsignadas() {
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
		for (Profesor p : getProfesores()) {
			p.eliminaAsignatura(a);
		}
	}

	public SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura() {
		SortedMap<Asignatura, SortedSet<Profesor>> res = new TreeMap<Asignatura, SortedSet<Profesor>>();
		for (Profesor p : this.getProfesores()) {
			putAsignaturaSetprof(p.getAsignaturas(), p, res);
		}

		return res;
	}

	private void putAsignaturaSetprof(List<Asignatura> asgs, Profesor p,
			SortedMap<Asignatura, SortedSet<Profesor>> res) {
		for (Asignatura a : asgs) {
			SortedSet<Profesor> pfs = new TreeSet<Profesor>();
			if (res.containsKey(a))
				pfs = res.get(a);
			pfs.add(p);
			res.put(a, pfs);
		}
	}

	public SortedMap<Profesor, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		SortedMap<Profesor, SortedSet<Tutoria>> res = new TreeMap<Profesor, SortedSet<Tutoria>>();
		for (Profesor p : this.getProfesores()) {
			res.put(p, p.getTutorias());
		}
		return res;
	}

}
