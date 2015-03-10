package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl implements Centro {
	private String nombre;
	private String direccion;
	private Integer numPlantas;
	private Integer numSotanos;
	private Set<Espacio> espacios;

	public CentroImpl(String nombre, String direccion, Integer numPlantas,
			Integer numSotanos) {
		checkPlantas(numSotanos, numPlantas);

		this.nombre = nombre;
		this.direccion = direccion;
		this.numPlantas = numPlantas;
		this.numSotanos = numSotanos;
		this.espacios = new TreeSet<Espacio>();
	}

	private void checkPlantas(Integer numSotanos, Integer numPlantas) {
		if (numSotanos < 0) {
			throw new ExcepcionCentroNoValido(
					"El número de sótanos debe ser mayor o igual que 0. ");
		}

		if (numPlantas < 1) {
			throw new ExcepcionCentroNoValido(
					"El número de plantas debe ser mayor o igual que 1.");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getNumeroSotanos() {
		return numSotanos;
	}

	public Integer getNumeroPlantas() {
		return numPlantas;
	}

	public int compareTo(Centro c) {
		return getNombre().compareTo(c.getNombre());
	}

	public boolean equals(Object o) {
		if (o instanceof Centro) {
			Centro c = (Centro) o;
			return getNombre().equals(c.getNombre());
		}
		return false;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		return prime * result + getNombre().hashCode();
	}

	public String toString() {
		return getNombre();
	}

	public Set<Espacio> getEspacios() {
		return new TreeSet<Espacio>(espacios);
	}

	public void nuevoEspacio(Espacio e) {
		if (e.getPlanta() < -this.getNumeroSotanos()
				|| e.getPlanta() >= this.getNumeroPlantas())
			throw new ExcepcionCentroOperacionNoPermitida(
					"CentroImpl.nuevoEspacio:: La planta del espacio debe estar entre ["
							+ (-this.getNumeroSotanos()) + ", "
							+ (this.getNumeroPlantas() - 1) + "]");
		espacios.add(e);
	}

	public void eliminaEspacio(Espacio e) {
		espacios.remove(e);
	}

	@Override
	public Integer[] getConteosEspacios() {
		Integer[] ret = { 0, 0, 0, 0, 0 };
		for (Espacio e : getEspacios()) {
			switch (e.getTipo()) {
			case TEORIA:
				ret[0]++;
				break;
			case LABORATORIO:
				ret[1]++;
				break;
			case SEMINARIO:
				ret[2]++;
				break;
			case EXAMEN:
				ret[3]++;
				break;
			case OTRO:
				ret[4]++;
				break;
			default:
				break;
			}
		}
		return ret;
	}

	public Set<Despacho> getDespachos() {
		Set<Despacho> ret = new TreeSet<Despacho>();
		for (Espacio e : getEspacios()) {
			if ((e instanceof Despacho))
				ret.add((Despacho) e);
		}
		return ret;
	}

	public Set<Despacho> getDespachos(Departamento d) {
		Set<Despacho> ret = new TreeSet<Despacho>();
		for (Despacho ds : getDespachos()) {
			if (existeProfesorDepartamento(ds.getProfesores(), d))
				ret.add(ds);
		}
		return ret;
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> ret = new HashSet<Profesor>();
		for (Despacho ds : getDespachos()) {
			ret.addAll(ds.getProfesores());
		}
		return ret;
	}

	public Set<Profesor> getProfesores(Departamento d) {
		Set<Profesor> ret = new HashSet<Profesor>();
		for (Despacho ds : getDespachos(d)) {
			for (Profesor p : ds.getProfesores()) {
				if (p.getDepartamento().equals(d)) {
					ret.add(p);
				}
			}
		}
		return ret;
	}

	public Espacio getEspacioMayorCapacidad() {
		if (getEspacios().size() == 0)
			throw new ExcepcionCentroOperacionNoPermitida(
					"CentroImpl.getEspacioMayorCapacidad:: El centro no tiene ningún espacio.");
		Espacio ret = new EspacioImpl(TipoEspacio.OTRO, "Vacio", 1, 1);
		for (Espacio e : getEspacios()) {
			if (e.getCapacidad() >= ret.getCapacidad())
				ret = e;
		}
		return ret;
	}

	private boolean existeProfesorDepartamento(Set<Profesor> profesores,
			Departamento d) {
		boolean ret = false;
		for (Profesor p : profesores) {
			if (p.getDepartamento().equals(d)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	public SortedMap<Profesor, Despacho> getDespachosPorProfesor() {
		SortedMap<Profesor, Despacho> res = new TreeMap<Profesor, Despacho>();

		for (Despacho d : this.getDespachos()) {
			/* For anidados no gustan :'(
			for(Profesor p : d.getProfesores()){
				res.put(p, d);
			}*/
			putProfesorDespacho(d.getProfesores(), d, res);
		}

		return res;
	}

	private void putProfesorDespacho(Set<Profesor> pfs, Despacho d,
			SortedMap<Profesor, Despacho> res) {
		for (Profesor p : pfs) {
			res.put(p, d);
		}
	}
	
}
