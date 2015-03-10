package fp.grados.tipos;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionBecarioNoValido;

public class BecarioImpl extends AlumnoImpl implements Becario {
	private Beca beca;
	private LocalDate fechaComienzo;

	public BecarioImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email, Beca beca,
			LocalDate fechaComienzo) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkFechaComienzo(fechaComienzo);

		this.beca = beca;
		this.fechaComienzo = fechaComienzo;
	}

	public BecarioImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email, String codigoBeca,
			Double cuantiaBeca, Integer duracionBeca, TipoBeca tipoBeca,
			LocalDate fechaComienzo) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkFechaComienzo(fechaComienzo);

		this.beca = new BecaImpl(codigoBeca, cuantiaBeca, duracionBeca,
				tipoBeca);
		this.fechaComienzo = fechaComienzo;
	}

	private void checkFechaComienzo(LocalDate fechaComienzo) {
		if (!fechaComienzo.isAfter(LocalDate.now()))
			throw new ExcepcionBecarioNoValido(
					"BecarioImpl.checkFechaComienzo:: La fecha de comienzo debe ser posterior a la fecha actual");
	}

	public Beca getBeca() {
		return beca;
	}

	public LocalDate getFechaComienzo() {
		return fechaComienzo;
	}

	public void setFechaComienzo(LocalDate fc) {
		checkFechaComienzo(fc);

		this.fechaComienzo = fc;
	}

	public LocalDate getFechaFin() {
		return getFechaComienzo().plusMonths(getBeca().getDuracion());
	}

	public void setEmail(String e) {
		throw new UnsupportedOperationException(
				"BecarioImpl.setEmail:: No es posible cambiar el email de un becario.");
	}

	public String toString() {
		return super.toString() + " " + getBeca();
	}
}
