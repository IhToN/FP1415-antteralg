package fp.grados.tipos;

import java.util.Optional;

import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl2 extends CentroImpl {
	
	/** Boletín 12 **/
	public CentroImpl2(String nombre, String direccion, Integer numPlantas,
			Integer numSotanos) {
		super(nombre, direccion, numPlantas, numSotanos);
	}
	
	public Espacio getEspacioMayorCapacidad() {
		Optional<Espacio> ret = this.getEspaciosOrdenadosPorCapacidad().stream().findFirst();
		if(!ret.isPresent())
			throw new ExcepcionCentroOperacionNoPermitida(
					"CentroImpl.getEspacioMayorCapacidad:: El centro no tiene ningún espacio.");
		return ret.get();
	}
	
	/* Opción de clase:
	 * 
	 public Espacio getEspacioMayorCapacidad() {
		Optional<Espacio> ret = getEspacios().stream.max(Comparator.comparing(Espacio::getCapacidad));
		if (!ret.isPresent())
			throw new ExcepcionCentroOperacionNoPermitida(
				"CentroImpl.getEspacioMayorCapacidad:: El centro no tiene ningún espacio.");
		
		return ret.get();
	 }
	 * 
	 */

}
