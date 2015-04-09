package fp.grados.tipos;

import java.util.Optional;

import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl2 extends CentroImpl {

	/** Bolet�n 12 **/
	public CentroImpl2(String nombre, String direccion, Integer numPlantas,
	Integer numSotanos) {
		super(nombre, direccion, numPlantas, numSotanos);
	}

	public Espacio getEspacioMayorCapacidad() {
		Optional<Espacio> ret = this.getEspaciosOrdenadosPorCapacidad().stream().findFirst();
		if(!ret.isPresent())
		throw new ExcepcionCentroOperacionNoPermitida(
		"CentroImpl.getEspacioMayorCapacidad:: El centro no tiene ning�n espacio.");
		return ret.get();
	}

	/* Opci�n de clase:
	*
	public Espacio getEspacioMayorCapacidad() {
	Optional<Espacio> ret = getEspacios().stream.max(Comparator.comparing(Espacio::getCapacidad));
	if (!ret.isPresent())
	throw new ExcepcionCentroOperacionNoPermitida(
	"CentroImpl.getEspacioMayorCapacidad:: El centro no tiene ning�n espacio.");

	return ret.get();
}
*
*/

public Integer[] getConteoEspacios() {
	Set<Espacio> espacios = getEspacios();
	Integer[] res = { cuentaEspacio(TipoEspacio.TEORIA, espacios),
		cuentaEspacio(TipoEspacio.LABORATORIO, espacios),
		cuentaEspacio(TipoEspacio.SEMINARIO, espacios),
		cuentaEspacio(TipoEspacio.EXAMEN, espacios),
		cuentaEspacio(TipoEspacio.OTRO, espacios) };

		return res;
	}

	private Integer cuentaEspacio(TipoEspacio tipo, Set<Espacio> espacios) {
		Integer res = (int) espacios.stream()
		.filter(e -> e.getTipo().equals(tipo)).count();
		return res;
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> res = getDespachos().stream()
		.flatMap(d -> d.getProfesores().stream())
		.collect(Collectors.toSet());
		return res;
	}

	public Set<Profesor> getProfesores(Departamento d) {
		Set<Profesor> res = getProfesores().stream()
		.filter(p -> p.getDepartamento().equals(d))
		.collect(Collectors.toSet());
		return res;
	}

	public SortedMap<Profesor, Despacho> getDespachosPorProfesor() {
		SortedMap<Profesor, Despacho> res = getProfesores()
		.stream()
		.filter(p -> tieneDespacho(p))
		.collect(
		Collectors.toMap(p -> p, p -> buscaDespacho(p),
		(d1, d2) -> d2, TreeMap::new));
		return res;
	}

	private Despacho buscaDespacho(Profesor p) {
		Despacho res = getDespachos().stream()
		.filter(d -> d.getProfesores().contains(p)).findFirst().get();
		return res;
	}

	private Boolean tieneDespacho(Profesor p) {
		Boolean res = getDespachos().stream().anyMatch(
		d -> d.getProfesores().contains(p));
		return res;
	}

}
