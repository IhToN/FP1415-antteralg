package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;

public class TestDespacho {

	public static void main(String[] args) {
		testConstructor1();
		testConstructor2();
		testConstructor3();

		testExcepcion1();
	}

	public static void mostrarDespacho(Despacho despacho) {
		System.out.println("Espacio.toString() - " + despacho.toString());
		System.out.println("Tipo: " + despacho.getTipo());
		System.out.println("Capacidad: " + despacho.getCapacidad());
		System.out.println("Planta: " + despacho.getPlanta());
		System.out.println("Nombre: " + despacho.getNombre());
		System.out.println("Profesores: " + despacho.getProfesores());
		System.out.println("=======================================");
		System.out.println("");
	}

	public static void testConstructor1() {
		try {
			System.out.println("============== Probamos el primer constructor");
			Set<Profesor> profesores = new HashSet<Profesor>();
			profesores.add(new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
					Categoria.TITULAR, null));
			Despacho despacho = new DespachoImpl("Despacho 1", 2, 1, profesores);
			mostrarDespacho(despacho);
		} catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionEspacioNoValido \n"
							+ e);
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada \n"
					+ e);
		}
	}

	public static void testConstructor2() {
		try {
			System.out
					.println("============== Probamos el segundo constructor");
			Profesor profesor = new ProfesorImpl("12345678Z", "Juan",
					"Nadie Nadie", LocalDate.of(1950, 3, 15),
					"juan.nadie@gmail.com", Categoria.TITULAR, null);
			Despacho despacho = new DespachoImpl("Despacho 1", 2, 1, profesor);
			mostrarDespacho(despacho);

		} catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionEspacioNoValido \n"
							+ e);
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada \n"
					+ e);
		}
	}

	public static void testConstructor3() {
		try {
			System.out.println("============== Probamos el tercer constructor");
			Despacho despacho = new DespachoImpl("Despacho 1", 2, 1);
			mostrarDespacho(despacho);

		} catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionEspacioNoValido \n"
							+ e);
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada \n"
					+ e);
		}
	}

	public static void testExcepcion1() {
		try {
			System.out
					.println("============== Probamos el primer constructor con más profesores que tamaño del despacho");
			Set<Profesor> profesores = new HashSet<Profesor>();
			profesores.add(new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
					Categoria.TITULAR, null));
			profesores.add(new ProfesorImpl("12345678Z", "Paco", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
					Categoria.TITULAR, null));
			profesores.add(new ProfesorImpl("12345678Z", "José", "Nadie Nadie",
					LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com",
					Categoria.TITULAR, null));
			profesores.add(new ProfesorImpl("12345678Z", "Marta",
					"Nadie Nadie", LocalDate.of(1950, 3, 15),
					"juan.nadie@gmail.com", Categoria.TITULAR, null));
			Despacho despacho = new DespachoImpl("Despacho 1", 2, 1, profesores);
			mostrarDespacho(despacho);
		} catch (ExcepcionDespachoNoValido e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionEspacioNoValido \n"
							+ e);
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada \n"
					+ e);
		}
	}

}
