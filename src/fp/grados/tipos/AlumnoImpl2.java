package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public class AlumnoImpl2 extends AlumnoImpl {

	public AlumnoImpl2(String alumno) {
		super(alumno);
	}
	
	public AlumnoImpl2(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
	}
	
	public Integer getCurso() {
		Optional<Asignatura> asig = getAsignaturas().stream().max(Comparator.comparing(Asignatura::getCurso));
		Integer ret = 0;
		if(asig.isPresent())
			ret = asig.get().getCurso(); 
		return ret;
	}
}
