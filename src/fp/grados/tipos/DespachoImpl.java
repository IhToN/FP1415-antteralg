package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;

public class DespachoImpl extends EspacioImpl implements Despacho {
	private Set<Profesor> profesores;

	/**** Constructores ****/
	// region Constructores
	public DespachoImpl(String nombre, Integer capacidad, Integer planta,
			Set<Profesor> profesores) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		checkEspacio(profesores);
		this.profesores = new HashSet<Profesor>(profesores);
	}

	public DespachoImpl(String nombre, Integer capacidad, Integer planta,
			Profesor profesor) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores = new HashSet<Profesor>();
		this.profesores.add(profesor);
		checkEspacio(this.profesores);
	}

	public DespachoImpl(String nombre, Integer capacidad, Integer planta) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores = new HashSet<Profesor>();
	}

	public DespachoImpl(String despacho) {
		// F1.43,1,3
		super(despacho + ",OTRO");
	}

	// endregion

	private void checkEspacio(Set<Profesor> profesores) {
		if (profesores.size() > this.getCapacidad())
			throw new ExcepcionDespachoNoValido(
					"DespachoImpl.checkEspacio:: No puede haber más profesores que espacio en el despacho.");
	}

	public Set<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(Set<Profesor> profesores) {
		checkEspacio(profesores);
		this.profesores = profesores;
	}

	public void setTipo(TipoEspacio tipo) {
		throw new UnsupportedOperationException(
				"DespachoImpl.setTipo:: No es posible cambiar el tipo de un despacho.");
	}

	public String toString() {
		return super.toString() + " " + getProfesores().toString() + "";
	}

}
