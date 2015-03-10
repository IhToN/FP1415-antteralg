package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fp.grados.excepciones.ExcepcionPersonaNoValida;
import fp.grados.tipos.Persona;
import fp.grados.tipos.PersonaImpl;
import fp.grados.utiles.Grados;

// Este código se les da como material adjunto al boletín T3,
// a excepción de los tests del constructor 3 y de setEmail()
public class TestPersona {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcional1();
		testConstructor1Excepcional2();
		testConstructor1Excepcional3();
		testConstructor1Excepcional4();

		testSetDNINormal();
		testSetDNIExcepcional1();
		testSetDNIExcepcional2();
		testSetDNIExcepcional3();

		testConstructor2Normal();
		testSetEmail();
		testSetEmailExcepcion();

		testCompareTo1();
		testCompareTo2();
		testCompareTo3();

		testEquals1();
		testEquals2();
		testEquals3();

		System.out
				.println("==================================Probando el constructor cadena");
		List<Persona> personas = Grados.leeFichero("res/personas.txt",
				s -> new PersonaImpl(s));
		System.out.println("Personas del .txt:\n" + personas);
	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional1() {
		System.out
				.println("==================================Probando el primer constructor con dni sin letra");
		testConstructor1("123456789", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional2() {
		System.out
				.println("==================================Probando el primer constructor con dni de longitud menor de la esperada");
		testConstructor1("1234567X", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional3() {
		System.out
				.println("==================================Probando el primer constructor con letra en dni que no se corresponde a los dígitos");
		testConstructor1("12345678X", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testConstructor1Excepcional4() {
		System.out
				.println("==================================Probando el primer constructor con email sin arroba");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadiegmail.com");
	}

	private static void testSetDNINormal() {
		System.out.println("==================================Probando setDNI");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677J");
	}

	private static void testSetDNIExcepcional1() {
		System.out
				.println("==================================Probando setDNI con dni sin letra");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "123456779");
	}

	private static void testSetDNIExcepcional2() {
		System.out
				.println("==================================Probando setDNI con dni de longitud menor de la esperada");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677");
	}

	private static void testSetDNIExcepcional3() {
		System.out
				.println("==================================Probando setDNI con letra en dni que no se corresponde a los dígitos");

		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
		testSetDNI(p, "12345677X");
	}

	private static void testConstructor1(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email) {

		try {
			Persona p = new PersonaImpl(dni, nombre, apellidos,
					fechaNacimiento, email);
			mostrarPersona(p);
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}

	private static void testConstructor2Normal() {
		System.out
				.println("==================================Probando el segundo constructor");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15));
	}

	private static void testSetEmail() {
		System.out
				.println("==================================Probando el setEmail");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15));
		mostrarPersona(p);

		testSetEmail(p, "albpallea@gmail.com");
	}

	private static void testSetEmailExcepcion() {
		System.out
				.println("==================================Probando el setEmail con excepcion");
		Persona p = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15));
		mostrarPersona(p);

		testSetEmail(p, "albpallea");
	}

	private static void testConstructor2(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento) {

		try {
			Persona p = new PersonaImpl(dni, nombre, apellidos, fechaNacimiento);
			mostrarPersona(p);
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}

	private static void testSetDNI(Persona p, String nuevoDNI) {

		try {
			System.out
					.println("El dni antes de la operación es: " + p.getDNI());
			System.out.println("El nuevo dni es: " + nuevoDNI);
			p.setDNI(nuevoDNI);
			System.out.println("El dni después de la operación es: "
					+ p.getDNI());
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setDNI no funciona correctamente");
		}
	}

	private static void testSetEmail(Persona p, String nuevoEmail) {

		try {
			System.out.println("El email antes de la operación es: "
					+ p.getEmail());
			System.out.println("El nuevo email es: " + nuevoEmail);
			p.setEmail(nuevoEmail);
			System.out.println("El email después de la operación es: "
					+ p.getEmail());
		} catch (ExcepcionPersonaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. setDNI no funciona correctamente");
		}
	}

	private static void mostrarPersona(Persona p) {
		System.out.println("Persona --> <" + p + ">");
		System.out.println("\tNombre: <" + p.getNombre() + ">");
		System.out.println("\tApellidos: <" + p.getApellidos() + ">");
		System.out.println("\tDNI: <" + p.getDNI() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ p.getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + p.getEdad() + ">");
		System.out.println("\tEmail:  <" + p.getEmail() + ">");
	}

	private static void testCompareTo1() {
		System.out.println("============== Persona 1 > Persona 2");
		Persona Persona = new PersonaImpl("12345678Z", "Alberto",
				"Palacios Leal", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Persona Persona2 = new PersonaImpl("12345678Z", "Juan",
				"Retromonguer Cacahué", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		compararElementos(Persona, Persona2);
	}

	private static void testCompareTo2() {
		System.out.println("============== Persona 2 > Persona 1");
		Persona Persona = new PersonaImpl("12345678Z", "Manolo",
				"Palacios Leal", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Persona Persona2 = new PersonaImpl("12345678Z", "Antonio",
				"Palacios Leal", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		compararElementos(Persona, Persona2);
	}

	private static void testCompareTo3() {
		System.out.println("============== Persona 1.compareTo(Persona 2) = 0");
		Persona Persona = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Persona Persona2 = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		compararElementos(Persona, Persona2);
	}

	private static void testEquals1() {
		System.out
				.println("============== Persona 1.equals(Persona 2) && e1==e2");
		Persona Persona = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Persona Persona2 = Persona;
		elementosIguales(Persona, Persona2);
	}

	private static void testEquals2() {
		System.out.println("============== Persona 1.equals(Persona 2)");
		Persona Persona = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Persona Persona2 = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		elementosIguales(Persona, Persona2);
	}

	private static void testEquals3() {
		System.out.println("============== !Persona 1.equals(Persona 2)");
		Persona Persona = new PersonaImpl("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		Persona Persona2 = new PersonaImpl("12345678Z", "Juan", "Aadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
		elementosIguales(Persona, Persona2);
	}

	public static void compararElementos(Persona a, Persona b) {
		if (a.compareTo(b) > 0) {
			System.out.println("El elemento más grande es " + a);
		} else if (a.compareTo(b) < 0) {
			System.out.println("El elemento más grande es " + b);
		} else {
			System.out.println("Los elementos son iguales");
		}
	}

	public static void elementosIguales(Persona a, Persona b) {
		if (a.equals(b) && a == b) {
			System.out.println("Los elementos son iguales e idénticos");
		} else if (a.equals(b)) {
			System.out.println("Los elementos son iguales pero no idénticos");
		} else {
			System.out.println("Los elementos son distintos");
		}
	}

}
