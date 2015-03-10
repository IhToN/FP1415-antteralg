package fp.grados.tipos.test;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.TipoAsignatura;

public class ExperimentosRelacionDepartamentoAsignatura {

	public static void main(String[] args) {
		Departamento lsi = new DepartamentoImpl(
				"Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl(
				"Ciencias de la Computación e Inteligencia Artificial");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programación",
				"2050001", 12.0, TipoAsignatura.ANUAL, 1, lsi);
		muestraDepartamentos(lsi, ccia);

		System.out
				.println("\nAñadimos la asignatura FP al departamento CCIA...");
		ccia.nuevaAsignatura(fp);
		muestraDepartamentos(lsi, ccia);

		System.out
				.println("\nCambiamos el departamento de la asignatura FP...");
		fp.setDepartamento(lsi);
		muestraDepartamentos(lsi, ccia);
	}

	private static void muestraDepartamentos(Departamento d1, Departamento d2) {
		System.out.println(d1 + ": " + d1.getAsignaturas());
		System.out.println(d2 + ": " + d2.getAsignaturas());
	}

}
