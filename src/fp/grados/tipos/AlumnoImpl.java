package fp.grados.tipos;

import java.time.LocalDate;	
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;

public class AlumnoImpl extends PersonaImpl implements Alumno {

	private Set<Asignatura> asignaturas;
	private Expediente expediente;

	//region Constructores
	public AlumnoImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		this.asignaturas = new HashSet<Asignatura>();
		checkEmailAlumUsEs(email);
		this.expediente = new ExpedienteImpl();
	}

	/* T10 */
	public AlumnoImpl(String alumno){
		super(alumno);
		checkEmailAlumUsEs(super.getEmail());
		this.asignaturas = new HashSet<Asignatura>();
		this.expediente = new ExpedienteImpl();
	}
	
	//endregion

	public void setEmail(String email) {
		checkEmailAlumUsEs(email);
		super.setEmail(email);
	}

	public Set<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public Integer getCurso() {
		int ret = 0;
		for (Asignatura a : getAsignaturas()) {
			if (a.getCurso() > ret)
				ret = a.getCurso();
		}
		return ret;
	}

	private void checkEmailAlumUsEs(String email) {
		if (!email.endsWith("@alum.us.es")) {
			throw new ExcepcionAlumnoNoValido(
					"AlumnoImpl.checkEmail:: El Email introducido no es válido");
		}
	}

	// Operaciones
	public void matriculaAsignatura(Asignatura asig) {
		if (!asignaturas.add(asig))
			throw new ExcepcionAlumnoOperacionNoPermitida(
					"AlumnoImpl.matriculaAsignatura:: No se ha podido introducir la asignatura");
	}

	public void eliminaAsignatura(Asignatura asig) {
		if (!asignaturas.remove(asig))
			throw new ExcepcionAlumnoOperacionNoPermitida(
					"AlumnoImpl.matriculaAsignatura:: No se ha podido introducir la asignatura");
	}

	public Boolean estaMatriculadoEn(Asignatura asig) {
		return getAsignaturas().contains(asig);
	}

	// toString
	public String toString() {
		if (getCurso() == null)
			return "(?) " + super.toString();
		else
			return "(" + getCurso() + "º) " + super.toString();
	}

	public Expediente getExpediente() {
		return this.expediente;
	}

	public void evaluaAlumno(Asignatura a, Integer curso,
			Convocatoria convocatoria, Double nota, Boolean mencionHonor) {
		if (!this.estaMatriculadoEn(a))
			throw new ExcepcionAlumnoOperacionNoPermitida(
					"AlumnoImpl.evaluaAlumno:: El alumno no está matriculado en la asignatura definida.");
		expediente.nuevaNota(new NotaImpl(a, curso, convocatoria, nota,
				mencionHonor));

	}

	public void evaluaAlumno(Asignatura a, Integer curso,
			Convocatoria convocatoria, Double nota) {
		if (!this.estaMatriculadoEn(a))
			throw new ExcepcionAlumnoOperacionNoPermitida(
					"AlumnoImpl.evaluaAlumno:: El alumno no está matriculado en la asignatura definida.");
		expediente.nuevaNota(new NotaImpl(a, curso, convocatoria, nota));
	}

	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {
		SortedMap<Asignatura, Calificacion> res = new TreeMap<Asignatura, Calificacion>();
		for(Nota n : this.getExpediente().getNotas()){
			Asignatura a = n.getAsignatura();
			Calificacion c = n.getCalificacion();
			if(res.containsKey(a)){
				if(res.get(a).compareTo(c) < 0)
					res.put(a, c);
			}else{
				res.put(a, c);
			}
		}
		return res;
	}
}
