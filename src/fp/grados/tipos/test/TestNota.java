package fp.grados.tipos.test;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionNotaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.Convocatoria;

public class TestNota {

	public static void main(String[] args) {
		testConstructor1();
		testConstructor2();

		testExcepcion1();
		testExcepcion2();
		testExcepcion3();
		testExcepcion4();
		testExcepcion5();

		testCompareTo1();
		testCompareTo2();
		testCompareTo3();

		testEquals1();
		testEquals2();
		testEquals3();
	}

	public static void mostrarNota(Nota var) {
		System.out.println("toString() - " + var.toString());
		System.out.println("Asignatura: " + var.getAsignatura());
		System.out.println("Convocatoria: " + var.getCursoAcademico());
		System.out.println("Mencion de Honor: " + var.getMencionHonor());
		System.out.println("Calificación: " + var.getCalificacion());
		System.out.println("==============================");
	}

	public static void testConstructor1() {
		System.out.println("============= Probando el constructor 1");
		Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
				"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
		Nota var = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1, true);
		mostrarNota(var);
	}

	public static void testConstructor2() {
		System.out.println("============= Probando el constructor 2");
		Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
				"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
		Nota var = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1);
		mostrarNota(var);
	}

	public static void testExcepcion1() {
		try {
			System.out.println("============= Curso menor a 1900");
			Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
					"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
			Nota var = new NotaImpl(asg, 1989, Convocatoria.PRIMERA, 9.1);
			mostrarNota(var);
		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
	}

	public static void testExcepcion2() {
		try {
			System.out.println("============= Curso mayor a "
					+ String.valueOf(LocalDate.now().getYear() + 1));
			Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
					"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
			Nota var = new NotaImpl(asg, LocalDate.now().getYear() + 2,
					Convocatoria.PRIMERA, 9.1);
			mostrarNota(var);
		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
	}

	public static void testExcepcion3() {
		try {
			System.out.println("============= Nota < 0");
			Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
					"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
			Nota var = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, -9.1);
			mostrarNota(var);
		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
	}

	public static void testExcepcion4() {
		try {
			System.out.println("============= Nota > 10");
			Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
					"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
			Nota var = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 19.1);
			mostrarNota(var);
		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
	}

	public static void testExcepcion5() {
		try {
			System.out.println("============= Matricula con nota menor a 9");
			Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
					"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
			Nota var = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1, true);
			mostrarNota(var);
		} catch (ExcepcionNotaNoValida e) {
			System.out
					.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out
					.println("******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
	}

	private static void testCompareTo1() {
		System.out.println("============== Nota 1 > Nota 2");
		Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
				"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
		Nota Nota = new NotaImpl(asg, 2015, Convocatoria.PRIMERA, 9.1);
		Nota Nota2 = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1);
		compararElementos(Nota, Nota2);
	}

	private static void testCompareTo2() {
		System.out.println("============== Nota 2 > Nota 1");
		Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
				"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
		Nota Nota = new NotaImpl(asg, 2011, Convocatoria.PRIMERA, 9.1);
		Nota Nota2 = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1);
		compararElementos(Nota, Nota2);
	}

	private static void testCompareTo3() {
		System.out.println("============== Nota 1.compareTo(Nota 2) = 0");
		Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
				"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
		Nota Nota = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1);
		Nota Nota2 = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1);
		compararElementos(Nota, Nota2);
	}

	private static void testEquals1() {
		System.out.println("============== Nota 1.equals(Nota 2) && e1==e2");
		Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
				"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
		Nota Nota = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1);
		Nota Nota2 = Nota;
		elementosIguales(Nota, Nota2);
	}

	private static void testEquals2() {
		System.out.println("============== Nota 1.equals(Nota 2)");
		Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
				"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
		Nota Nota = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1);
		Nota Nota2 = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1);
		elementosIguales(Nota, Nota2);
	}

	private static void testEquals3() {
		System.out.println("============== !Nota 1.equals(Nota 2)");
		Asignatura asg = new AsignaturaImpl("Fundamentos de Programación",
				"0000230", 12.0, TipoAsignatura.ANUAL, 1, null);
		Nota Nota = new NotaImpl(asg, 2015, Convocatoria.PRIMERA, 9.1);
		Nota Nota2 = new NotaImpl(asg, 2014, Convocatoria.PRIMERA, 9.1);
		elementosIguales(Nota, Nota2);
	}

	public static void compararElementos(Nota a, Nota b) {
		if (a.compareTo(b) > 0) {
			System.out.println("El elemento más grande es " + a);
		} else if (a.compareTo(b) < 0) {
			System.out.println("El elemento más grande es " + b);
		} else {
			System.out.println("Los elementos son iguales");
		}
	}

	public static void elementosIguales(Nota a, Nota b) {
		if (a.equals(b) && a == b) {
			System.out.println("Los elementos son iguales e idénticos");
		} else if (a.equals(b)) {
			System.out.println("Los elementos son iguales pero no idénticos");
		} else {
			System.out.println("Los elementos son distintos");
		}
	}
}
