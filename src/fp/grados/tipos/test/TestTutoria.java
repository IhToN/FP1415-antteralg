package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalTime;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.Tutoria;
import fp.grados.tipos.TutoriaImpl;

public class TestTutoria {

	public static void main(String[] args) {
		testConstructor1();
		testConstructor2();
		
		testExcepcion1();
		testExcepcion2();

		testCompareTo1();
		testCompareTo2();
		testCompareTo3();

		testEquals1();
		testEquals2();
		testEquals3();
	}

	public static void mostrarTutoria(Tutoria var) {
		System.out.println("toString(): " + var.toString());
		System.out.println("DiaSemana: " + var.getDiaSemana());
		System.out.println("HComienzo: " + var.getHoraComienzo());
		System.out.println("HFin: " + var.getHoraFin());
		System.out.println("Duración: " + var.getDuracion() + " minutos");
		System.out.println("==============================");
	}

	public static void testConstructor1() {
		System.out.println("============== Probamos el primer constructor");
		Tutoria var = new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(11, 30),
				LocalTime.of(13, 30));
		mostrarTutoria(var);
	}

	public static void testConstructor2() {
		System.out.println("============== Probamos el segundo constructor");
		Tutoria var = new TutoriaImpl(DayOfWeek.WEDNESDAY, LocalTime.of(9, 30),
				15);
		mostrarTutoria(var);
	}

	public static void testExcepcion1() {
		try {
			System.out
					.println("============== Excepcion: Día de la tutoria fin de semana");
			Tutoria var = new TutoriaImpl(DayOfWeek.SATURDAY, LocalTime.of(11,
					30), LocalTime.of(13, 30));
			mostrarTutoria(var);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida \n"
							+ e);
		} catch (Exception e) {
			System.out
					.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!! \n"
							+ e);
		}
	}

	public static void testExcepcion2() {
		try {
			System.out
					.println("============== Excepcion: Duracion menor a 15 minutos");
			Tutoria var = new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(11,
					30), 3);
			mostrarTutoria(var);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida \n"
							+ e);
		} catch (Exception e) {
			System.out
					.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!! \n"
							+ e);
		}
	}

	private static void testCompareTo1() {
		System.out.println("============== Tutoria 1 > Tutoria 2");
		Tutoria Tutoria = new TutoriaImpl(DayOfWeek.THURSDAY, LocalTime.of(11,
				30), LocalTime.of(13, 30));
		Tutoria Tutoria2 = new TutoriaImpl(DayOfWeek.MONDAY,
				LocalTime.of(9, 30), 15);
		compararElementos(Tutoria, Tutoria2);
	}

	private static void testCompareTo2() {
		System.out.println("============== Tutoria 2 > Tutoria 1");
		Tutoria Tutoria = new TutoriaImpl(DayOfWeek.WEDNESDAY, LocalTime.of(9,
				30), LocalTime.of(13, 30));
		Tutoria Tutoria2 = new TutoriaImpl(DayOfWeek.MONDAY,
				LocalTime.of(9, 30), 15);
		compararElementos(Tutoria, Tutoria2);
	}

	private static void testCompareTo3() {
		System.out.println("============== Tutoria 1.compareTo(Tutoria 2) = 0");
		Tutoria Tutoria = new TutoriaImpl(DayOfWeek.MONDAY,
				LocalTime.of(11, 30), LocalTime.of(13, 30));
		Tutoria Tutoria2 = new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(11,
				30), 15);
		compararElementos(Tutoria, Tutoria2);
	}

	private static void testEquals1() {
		System.out
				.println("============== Tutoria 1.equals(Tutoria 2) && e1==e2");
		Tutoria Tutoria = new TutoriaImpl(DayOfWeek.MONDAY,
				LocalTime.of(11, 30), LocalTime.of(13, 30));
		Tutoria Tutoria2 = Tutoria;
		elementosIguales(Tutoria, Tutoria2);
	}

	private static void testEquals2() {
		System.out.println("============== Tutoria 1.equals(Tutoria 2)");
		Tutoria Tutoria = new TutoriaImpl(DayOfWeek.MONDAY,
				LocalTime.of(11, 30), LocalTime.of(13, 30));
		Tutoria Tutoria2 = new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(11,
				30), 15);
		elementosIguales(Tutoria, Tutoria2);
	}

	private static void testEquals3() {
		System.out.println("============== !Tutoria 1.equals(Tutoria 2)");
		Tutoria Tutoria = new TutoriaImpl(DayOfWeek.MONDAY,
				LocalTime.of(11, 30), LocalTime.of(13, 30));
		Tutoria Tutoria2 = new TutoriaImpl(DayOfWeek.WEDNESDAY, LocalTime.of(9,
				30), 15);
		elementosIguales(Tutoria, Tutoria2);
	}

	public static void compararElementos(Tutoria a, Tutoria b) {
		if (a.compareTo(b) > 0) {
			System.out.println("El elemento más grande es " + a);
		} else if (a.compareTo(b) < 0) {
			System.out.println("El elemento más grande es " + b);
		} else {
			System.out.println("Los elementos son iguales");
		}
	}

	public static void elementosIguales(Tutoria a, Tutoria b) {
		if (a.equals(b) && a == b) {
			System.out.println("Los elementos son iguales e idénticos");
		} else if (a.equals(b)) {
			System.out.println("Los elementos son iguales pero no idénticos");
		} else {
			System.out.println("Los elementos son distintos");
		}
	}
}
