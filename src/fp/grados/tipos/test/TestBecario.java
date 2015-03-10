package fp.grados.tipos.test;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionBecarioNoValido;
import fp.grados.tipos.Beca;
import fp.grados.tipos.BecaImpl;
import fp.grados.tipos.Becario;
import fp.grados.tipos.BecarioImpl;
import fp.grados.tipos.TipoBeca;

public class TestBecario {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcion();

		testConstructor2Normal();
		testConstructor2Excepcion();

		testsetFechaComienzoNormal();
		testsetFechaComienzoExcepcion();

		testSetEmail();
	}

	private static void testConstructor1Normal() {
		System.out
				.println("\n==================================Probando el primer constructor");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",
				new BecaImpl("ABC1234", TipoBeca.ORDINARIA),
				LocalDate.of(2015, 1, 1));
	}

	private static void testConstructor1Excepcion() {
		System.out
				.println("\n==================================Probando el primer constructor, fecha de inicio incorrecta");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",
				new BecaImpl("ABC1234", TipoBeca.ORDINARIA),
				LocalDate.of(2010, 1, 1));
	}

	private static void testConstructor2Normal() {
		System.out
				.println("\n==================================Probando el segundo constructor");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", "ABC1234",
				12000.0, 12, TipoBeca.ORDINARIA, LocalDate.of(2015, 1, 1));
	}

	private static void testConstructor2Excepcion() {
		System.out
				.println("\n==================================Probando el segundo constructor, fecha de inicio incorrecta");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", "ABC1234",
				12000.0, 12, TipoBeca.ORDINARIA, LocalDate.of(2010, 1, 1));
	}

	private static void testsetFechaComienzoNormal() {
		System.out
				.println("\n==================================Probando setFechaComienzo");

		Becario b = new BecarioImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",
				new BecaImpl("ABC1234", TipoBeca.ORDINARIA), LocalDate.of(2015,
						1, 1));
		testsetFechaComienzo(b, LocalDate.of(2016, 1, 1));
	}

	private static void testsetFechaComienzoExcepcion() {
		System.out
				.println("\n==================================Probando setFechaComienzo, fecha incorrecta");

		Becario b = new BecarioImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",
				new BecaImpl("ABC1234", TipoBeca.ORDINARIA), LocalDate.of(2015,
						1, 1));
		testsetFechaComienzo(b, LocalDate.of(2010, 1, 1));
	}

	private static void testSetEmail() {
		System.out
				.println("\n==================================Probando setEmail");

		Becario b = new BecarioImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es",
				new BecaImpl("ABC1234", TipoBeca.ORDINARIA), LocalDate.of(2015,
						1, 1));

		try {
			b.setEmail("juan@alum.us.es");
		} catch (UnsupportedOperationException e) {
			System.out
					.println("******************** Se ha capturado la excepción UnsupportedOperationException");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}

	private static void testConstructor1(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email,
			Beca beca, LocalDate fechaComienzo) {

		try {
			Becario b = new BecarioImpl(dni, nombre, apellidos,
					fechaNacimiento, email, beca, fechaComienzo);
			mostrarBecario(b);
		} catch (ExcepcionBecarioNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionBecarioNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}

	}

	private static void testConstructor2(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email,
			String codigo, Double cuantiaTotal, Integer duracion,
			TipoBeca tipo, LocalDate fechaComienzo) {

		try {
			Becario b = new BecarioImpl(dni, nombre, apellidos,
					fechaNacimiento, email, codigo, cuantiaTotal, duracion,
					tipo, fechaComienzo);
			mostrarBecario(b);
		} catch (ExcepcionBecarioNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionBecarioNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void testsetFechaComienzo(Becario b, LocalDate fechaComienzo) {

		try {
			System.out.println("La fecha de inicio antes de la operación es: "
					+ b.getFechaComienzo());
			System.out.println("La nueva fecha de inicio es: " + fechaComienzo);
			b.setFechaComienzo(fechaComienzo);
			System.out
					.println("La fecha de inicio después de la operación es: "
							+ b.getFechaComienzo());
		} catch (ExcepcionBecarioNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionBecarioNoValido");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada.");
		}
	}

	private static void mostrarBecario(Becario b) {
		System.out.println("Becario --> <" + b + ">");
		System.out.println("\tDNI: <" + b.getDNI() + ">");
		System.out.println("\tNombre: <" + b.getNombre() + ">");
		System.out.println("\tApellidos: <" + b.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <" + b.getFechaNacimiento()
				+ ">");
		System.out.println("\tEdad: <" + b.getEdad() + ">");
		System.out.println("\tEmail:  <" + b.getEmail() + ">");
		System.out.println("\tAsignaturas:  <" + b.getAsignaturas() + ">");
		System.out.println("\tBeca: <" + b.getBeca() + ">");
		System.out.println("\tFecha de inicio: <" + b.getFechaComienzo() + ">");
		System.out.println("\tFecha de fin: <" + b.getFechaFin() + ">");
	}
}
