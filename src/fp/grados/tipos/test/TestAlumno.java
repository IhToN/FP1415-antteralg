package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;
import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestAlumno {

	public static void main(String[] args) {

		testConstructorNormal();
		testConstructorExcepcionEmail();

		testSetEmailNormal();
		testSetEmailExcepcion();

		testMatriculaAsignatura();
		testMatriculaAsignaturaExcepcion();

		testEliminaAsignatura();
		testEliminaAsignaturaExcepcion();

	}

	private static void testConstructorNormal() {
		System.out
				.println("\n==================================Probando el primer constructor");
		testConstructor("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
	}

	private static void testConstructorExcepcionEmail() {
		try {
			System.out
					.println("\n==================================Probando el primer constructor, email incorrecto");
			testConstructor("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionEspacioNoValido \n"
							+ e);
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionAlumnoOperacionNoPermitida \n"
							+ e);
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada \n"
					+ e);
		}
	}

	private static void testSetEmailNormal() {
		System.out
				.println("\n==================================Probando setEmail");

		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		testSetEmail(a, "juan@alum.us.es");
	}

	private static void testSetEmailExcepcion() {
		try {
			System.out
					.println("\n==================================Probando setEmail, email incorrecto");
			Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
			testSetEmail(a, "juan@gmail.com");
		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionEspacioNoValido \n"
							+ e);
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionAlumnoOperacionNoPermitida \n"
							+ e);
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada \n"
					+ e);
		}
	}

	private static void testMatriculaAsignatura() {
		System.out
				.println("\n==================================Probando matriculaAsignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		testMatriculaAsignatura(a, asig);
	}

	private static void testMatriculaAsignaturaExcepcion() {
		try {
			System.out
					.println("\n==================================Probando matriculaAsignatura, matricula doble en una asignatura");
			Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
			Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
					"2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
			a.matriculaAsignatura(asig);
			testMatriculaAsignatura(a, asig);
		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionEspacioNoValido \n"
							+ e);
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionAlumnoOperacionNoPermitida \n"
							+ e);
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada \n"
					+ e);
		}
	}

	private static void testEliminaAsignatura() {
		System.out
				.println("\n==================================Probando eliminaAsignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
		a.matriculaAsignatura(asig);
		testEliminaAsignatura(a, asig);
	}

	private static void testEliminaAsignaturaExcepcion() {
		try {
			System.out
					.println("\n==================================Probando eliminaAsignatura, asignatura no matriculada");
			Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
			Asignatura asig = new AsignaturaImpl("Fundamentos de Programación",
					"2050001", 12.0, TipoAsignatura.ANUAL, 1, null);
			testEliminaAsignatura(a, asig);
		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionEspacioNoValido \n"
							+ e);
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionAlumnoOperacionNoPermitida \n"
							+ e);
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada \n"
					+ e);
		}
	}

	private static void testConstructor(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email) {

		try {
			Alumno a = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento,
					email);
			mostrarAlumno(a);
		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}

	private static void testSetEmail(Alumno a, String nuevoEmail) {

		try {
			System.out.println("El email antes de la operación es: "
					+ a.getEmail());
			System.out.println("El nuevo email es: " + nuevoEmail);
			a.setEmail(nuevoEmail);
			System.out.println("El email después de la operación es: "
					+ a.getEmail());
		} catch (ExcepcionAlumnoNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void testMatriculaAsignatura(Alumno a, Asignatura asig) {

		try {
			System.out.println("Las asignaturas antes de la operación son: "
					+ a.getAsignaturas());
			System.out.println("Nueva asignatura a matricular: " + asig);
			a.matriculaAsignatura(asig);
			System.out.println("Las asignaturas después de la operación son: "
					+ a.getAsignaturas());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void testEliminaAsignatura(Alumno a, Asignatura asig) {

		try {
			System.out.println("Las asignaturas antes de la operación son: "
					+ a.getAsignaturas());
			System.out.println("Asignatura a eliminar: " + asig);
			a.eliminaAsignatura(asig);
			System.out.println("Las asignaturas después de la operación son: "
					+ a.getAsignaturas());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void mostrarAlumno(Alumno a) {
		System.out.println("Alumno --> <" + a + ">");
		System.out.println("\tDNI: <" + a.getDNI() + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tApellidos: <" + a.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ a.getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + a.getEdad() + ">");
		System.out.println("\tEmail:  <" + a.getEmail() + ">");
		System.out.println("\tAsignaturas:  <" + a.getAsignaturas() + ">");
	}
}
