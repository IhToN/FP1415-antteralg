package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;

// Este código se les da como material adjunto al boletín T3,
// a excepción de los tests del constructor 3 y de setEmail()
public class TestProfesor {

	public static void main(String[] args) {

		testConstructor1Normal();
		testConstructor1Excepcional1();

		testCompareTo1();
		testCompareTo2();
		testCompareTo3();

		testEquals1();
		testEquals2();
		testEquals3();
	}

	private static void testConstructor1Normal() {
		System.out
				.println("==================================Probando el primer constructor");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
				Categoria.TITULAR);
	}

	private static void testConstructor1Excepcional1() {
		System.out
				.println("==================================Probando el primer constructor menor de edad");
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",
				LocalDate.of(1997, 3, 15), "juan.nadie@gmail.com",
				Categoria.TITULAR);
	}

	private static void testConstructor1(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email,
			Categoria categoria) {

		try {
			Profesor p = new ProfesorImpl(dni, nombre, apellidos,
					fechaNacimiento, email, categoria, null);
			mostrarProfesor(p);
		} catch (ExcepcionProfesorNoValido e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionProfesorNoValida \n"+e);
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente \n"+e);
		}

	}


	private static void mostrarProfesor(Profesor p) {
		System.out.println("Profesor --> <" + p + ">");
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
		System.out.println("============== Profesor 1 > Profesor 2");
		Profesor Profesor = new ProfesorImpl("12345678Z", "Alberto",
				"Palacios Leal", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.TITULAR, null);
		Profesor Profesor2 = new ProfesorImpl("12345678Z", "Juan",
				"Retromonguer Cacahué", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.TITULAR, null);
		compararElementos(Profesor, Profesor2);
	}

	private static void testCompareTo2() {
		System.out.println("============== Profesor 2 > Profesor 1");
		Profesor Profesor = new ProfesorImpl("12345678Z", "Manolo",
				"Palacios Leal", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, null);
		Profesor Profesor2 = new ProfesorImpl("12345678Z", "Antonio",
				"Palacios Leal", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR, null);
		compararElementos(Profesor, Profesor2);
	}

	private static void testCompareTo3() {
		System.out
				.println("============== Profesor 1.compareTo(Profesor 2) = 0");
		Profesor Profesor = new ProfesorImpl("12345678Z", "Juan",
				"Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.TITULAR, null);
		Profesor Profesor2 = new ProfesorImpl("12345678Z", "Juan",
				"Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.TITULAR, null);
		compararElementos(Profesor, Profesor2);
	}

	private static void testEquals1() {
		System.out
				.println("============== Profesor 1.equals(Profesor 2) && e1==e2");
		Profesor Profesor = new ProfesorImpl("12345678Z", "Juan",
				"Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.TITULAR, null);
		Profesor Profesor2 = Profesor;
		elementosIguales(Profesor, Profesor2);
	}

	private static void testEquals2() {
		System.out.println("============== Profesor 1.equals(Profesor 2)");
		Profesor Profesor = new ProfesorImpl("12345678Z", "Juan",
				"Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.TITULAR, null);
		Profesor Profesor2 = new ProfesorImpl("12345678Z", "Juan",
				"Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.TITULAR, null);
		elementosIguales(Profesor, Profesor2);
	}

	private static void testEquals3() {
		System.out.println("============== !Profesor 1.equals(Profesor 2)");
		Profesor Profesor = new ProfesorImpl("12345678Z", "Juan",
				"Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.TITULAR, null);
		Profesor Profesor2 = new ProfesorImpl("12345678Z", "Juan",
				"Aadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.com", Categoria.TITULAR, null);
		elementosIguales(Profesor, Profesor2);
	}

	public static void compararElementos(Profesor a, Profesor b) {
		if (a.compareTo(b) > 0) {
			System.out.println("El elemento más grande es " + a);
		} else if (a.compareTo(b) < 0) {
			System.out.println("El elemento más grande es " + b);
		} else {
			System.out.println("Los elementos son iguales");
		}
	}

	public static void elementosIguales(Profesor a, Profesor b) {
		if (a.equals(b) && a == b) {
			System.out.println("Los elementos son iguales e idénticos");
		} else if (a.equals(b)) {
			System.out.println("Los elementos son iguales pero no idénticos");
		} else {
			System.out.println("Los elementos son distintos");
		}
	}

}
