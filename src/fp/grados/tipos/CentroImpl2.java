package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl2 extends CentroImpl {
	
	/** Boletín 12 **/
	public CentroImpl2(String nombre, String direccion, Integer numPlantas,
			Integer numSotanos) {
		super(nombre, direccion, numPlantas, numSotanos);
	}
	
	public Espacio getEspacioMayorCapacidad() {
		if (getEspacios().size() == 0)
			throw new ExcepcionCentroOperacionNoPermitida(
					"CentroImpl.getEspacioMayorCapacidad:: El centro no tiene ningún espacio.");
		Espacio ret = this.getEspaciosOrdenadosPorCapacidad().last();
		return ret;
	}
	
	/* Opción de clase:
	 * 
	 public Espacio getEspacioMayorCapacidad() {
	 	try{
			Optional<Espacio> ret = getEspacios().stream.max(Comparator.comparing(Espacio::getCapacidad));
			if (ret.isPresent()){
				
			}
			return ret;
		}catch(Exception e){
			throw new ExcepcionCentroOperacionNoPermitida(
					"CentroImpl.getEspacioMayorCapacidad:: El centro no tiene ningún espacio.");
		}
	 }
	 * 
	 */

}
