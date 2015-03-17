package fp.grados.tipos;

import java.time.LocalDate;

public class AlumnoImpl2 extends AlumnoImpl {

	public AlumnoImpl2(String alumno) {
		super(alumno);
	}
	
	public AlumnoImpl2(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
	}

}
