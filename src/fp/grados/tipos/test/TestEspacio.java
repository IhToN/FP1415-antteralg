package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionEspacioNoValido;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.TipoEspacio;

public class TestEspacio {

	public static void main(String[] args) {
		testConstructor1();
		testConstructor2();
		
		testCompareTo1();
		testCompareTo2();
		testCompareTo3();
		
		testEquals1();
		testEquals2();
		testEquals3();
	}


	public static void mostrarEspacio(Espacio espacio) {
		System.out.println("Espacio.toString() - " + espacio.toString());
		System.out.println("Tipo: " + espacio.getTipo());
		System.out.println("Capacidad: " + espacio.getCapacidad());
		System.out.println("Planta: " + espacio.getPlanta());
		System.out.println("Nombre: " + espacio.getNombre());
		System.out.println("=======================================");
		System.out.println("");
	}
	
	public static void compararElementos(Espacio a, Espacio b){
		if(a.compareTo(b) > 0){
			System.out.println("El elemento más grande es "+a);
		}else if(a.compareTo(b) < 0){
			System.out.println("El elemento más grande es "+b);
		}else{
			System.out.println("Los elementos son iguales");
		}
	}
	
	public static void elementosIguales(Espacio a, Espacio b){
		if(a.equals(b) && a == b){
			System.out.println("Los elementos son iguales e idénticos");
		}else if(a.equals(b)){
			System.out.println("Los elementos son iguales pero no idénticos");
		}else{
			System.out.println("Los elementos son distintos");
		}
	}

	public static void testConstructor1() {
		System.out.println("============== Probamos el primer constructor");
		Espacio espacio = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.21", 90,
				0);
		mostrarEspacio(espacio);
	}

	public static void testConstructor2() {
		try {
			System.out
					.println("============== Probamos el primer constructor, capacidad menor a 1");
			Espacio espacio = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.21",
					0, 0);
			mostrarEspacio(espacio);
		} catch (ExcepcionEspacioNoValido e) {
			System.out
					.println("Se ha capturado una expecion de tipo ExcepcionEspacioNoValido \n"
							+ e);
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada \n"
					+ e);
		}
	}
	
	private static void testCompareTo1() {
		System.out.println("============== Espacio 1 > Espacio 2");
		Espacio espacio = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.21", 90,
				1);
		Espacio espacio2 = new EspacioImpl(TipoEspacio.LABORATORIO, "A1.21", 90,
				0);
		compararElementos(espacio, espacio2);
	}
	
	private static void testCompareTo2() {
		System.out.println("============== Espacio 2 > Espacio 1");
		Espacio espacio = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.21", 90,
				0);
		Espacio espacio2 = new EspacioImpl(TipoEspacio.LABORATORIO, "A1.21", 90,
				1);
		compararElementos(espacio, espacio2);
	}
	
	private static void testCompareTo3() {
		System.out.println("============== Espacio 1.compareTo(Espacio 2) = 0");
		Espacio espacio = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.21", 90,
				0);
		Espacio espacio2 = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.21", 90,
				0);
		compararElementos(espacio, espacio2);
	}
	
	private static void testEquals1(){
		System.out.println("============== Espacio 1.equals(Espacio 2) && e1==e2");
		Espacio espacio = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.21", 90,
				0);
		Espacio espacio2 = espacio;
		elementosIguales(espacio, espacio2);
	}
	
	private static void testEquals2() {
		System.out.println("============== Espacio 1.equals(Espacio 2)");
		Espacio espacio = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.21", 90,
				0);
		Espacio espacio2 = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.21", 90,
				0);
		elementosIguales(espacio, espacio2);
	}
	
	private static void testEquals3() {
		System.out.println("============== !Espacio 1.equals(Espacio 2)");
		Espacio espacio = new EspacioImpl(TipoEspacio.LABORATORIO, "A0.21", 90,
				0);
		Espacio espacio2 = new EspacioImpl(TipoEspacio.LABORATORIO, "A1.21", 90,
				1);
		elementosIguales(espacio, espacio2);
	}

}
