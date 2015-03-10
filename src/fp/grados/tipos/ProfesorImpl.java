package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl extends PersonaImpl implements Profesor {
	private static final Integer DEDICACION_MAXIMA = 24;
	private Categoria categoria;
	private SortedSet<Tutoria> tutorias;
	private Departamento departamento;

	private List<Asignatura> asignaturas;
	private List<Double> creditos;

	public ProfesorImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email, Categoria categoria,
			Departamento departamento) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEdad(fechaNacimiento);
		this.categoria = categoria;
		setDepartamento(departamento);
		this.tutorias = new TreeSet<Tutoria>();
		this.creditos = new ArrayList<Double>();
		this.asignaturas = new ArrayList<Asignatura>();
	}

	private void checkEdad(LocalDate fechaNacimiento) {
		if (fechaNacimiento.until(LocalDate.now(),
				(TemporalUnit) ChronoUnit.YEARS) < 18)
			throw new ExcepcionProfesorNoValido(
					"ProfesorImpl.checkEdad:: Un profesor debe ser mayor de edad");
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		super.setFechaNacimiento(fechaNacimiento);
		checkEdad(fechaNacimiento);
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria c) {
		this.categoria = c;
	}

	public SortedSet<Tutoria> getTutorias() {
		return new TreeSet<Tutoria>(tutorias);
	}

	public void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos,
			DayOfWeek dia) {
		tutorias.add(new TutoriaImpl(dia, horaComienzo, duracionMinutos));
	}

	public void borraTutoria(LocalTime horaComienzo, DayOfWeek dia) {
		tutorias.remove(new TutoriaImpl(dia, horaComienzo, 15));

	}

	public void borraTutorias() {
		tutorias.clear();
	}

	public String toString() {
		return super.toString() + " (" + getCategoria() + ")";
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento dep) {
		if (dep != this.departamento) {
			Departamento antiguoDepartamento = this.departamento;
			this.departamento = dep;
			if (antiguoDepartamento != null)
				antiguoDepartamento.eliminaProfesor(this);
			if (dep != null)
				dep.nuevoProfesor(this);
		}
	}

	public void imparteAsignatura(Asignatura asig, Double dedicacion) {
		checkAsignaturaDepartamento(asig);
		checkCreditosAsignatura(asig, dedicacion);
		if (asignaturas.contains(asig)) {
			checkDedicacion(dedicacion - this.dedicacionAsignatura(asig));
			creditos.set(asignaturas.indexOf(asig), dedicacion);
		} else {
			checkDedicacion(dedicacion);
			asignaturas.add(asig);
			creditos.add(dedicacion);
		}

	}

	private void checkAsignaturaDepartamento(Asignatura asig) {
		if (!getDepartamento().getAsignaturas().contains(asig))
			throw new ExcepcionProfesorOperacionNoPermitida(
					"ProfesorImpl.imparteAsignatura:: Los departamentos de la asignatura y el profesor no se corresponden");
	}

	private void checkCreditosAsignatura(Asignatura asig, Double dedicacion) {
		if (dedicacion > asig.getCreditos() || dedicacion < 0)
			throw new ExcepcionProfesorOperacionNoPermitida(
					"ProfesorImpl.imparteAsignatura:: Un profesor debe impartir más de 0 créditos en una asignatura.");
	}

	private void checkDedicacion(Double dedicacion) {
		if (getDedicacionTotal() + dedicacion > DEDICACION_MAXIMA)
			throw new ExcepcionProfesorOperacionNoPermitida(
					"ProfesorImpl.imparteAsignatura:: Un profesor no puede impartir más de "
							+ DEDICACION_MAXIMA + " créditos.");

	}

	public Double dedicacionAsignatura(Asignatura asig) {
		Double ret = 0.0;
		if (asignaturas.contains(asig))
			ret = creditos.get(asignaturas.indexOf(asig));
		return ret;
	}

	public void eliminaAsignatura(Asignatura asig) {
		if (asignaturas.contains(asig)) {
			creditos.remove(asignaturas.indexOf(asig));
			asignaturas.remove(asig);
		}
	}

	public List<Asignatura> getAsignaturas() {
		return new ArrayList<Asignatura>(asignaturas);
	}

	public List<Double> getCreditos() {
		return new ArrayList<Double>(creditos);
	}

	public Double getDedicacionTotal() {
		Double ret = 0.0;
		for (Double d : getCreditos()) {
			ret += d;
		}
		return ret;
	}
}
