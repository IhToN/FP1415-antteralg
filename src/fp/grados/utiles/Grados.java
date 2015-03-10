package fp.grados.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.ProfesorImpl2;

public class Grados {

	/** T11 - Factorías **/
	// region Factorias
	// Departamento
	public static Departamento createDepartamento(String nombre) {
		Departamento res = new DepartamentoImpl(nombre);
		departamentos.add(res);
		return res;
	}

	// Profesor
	public static Profesor createProfesor(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email,
			Categoria categoria, Departamento departamento) {
		Profesor res;
		if(usarImplementacionMapProfesor)
			res = new ProfesorImpl2(dni, nombre, apellidos,
					fechaNacimiento, email, categoria, departamento);
		else
			res = new ProfesorImpl(dni, nombre, apellidos,
					fechaNacimiento, email, categoria, departamento);
		//TODO actualizar propiedades poblacionales
		
		return res;
	}
	public static Profesor createProfesor(Profesor profesor){
		Profesor res;
		if(usarImplementacionMapProfesor)
			res = new ProfesorImpl2(profesor.getDNI(), profesor.getNombre(), profesor.getApellidos(),
					profesor.getFechaNacimiento(), profesor.getEmail(), profesor.getCategoria(), profesor.getDepartamento());
		else
			res = new ProfesorImpl2(profesor.getDNI(), profesor.getNombre(), profesor.getApellidos(),
					profesor.getFechaNacimiento(), profesor.getEmail(), profesor.getCategoria(), profesor.getDepartamento());
		//TODO actualizar propiedades poblacionales
		
		return res;
	}

	// endregion

	/** T11 - Propiedades Poblacionales **/
	// region Propiedades Poblacionales
	private static Set<Departamento> departamentos = new HashSet<Departamento>();

	public static Integer getNumDepartamentosCreados() {
		return departamentos.size();
	}

	public static Set<Departamento> getDepartamentosCreados() {
		return new HashSet<Departamento>(departamentos);
	}
	
	private static Boolean usarImplementacionMapProfesor = true;
	public static void setUsarImplementacionMapProfesor(Boolean b){
		usarImplementacionMapProfesor = b;
	}
	//TODO Propiedades Poblacionales

	// endregion

	public static <T> List<T> leeFichero(String nombreFichero,
			Function<String, T> funcion_deString_aT) {
		List<T> res = null;
		try {
			res = Files.lines(Paths.get(nombreFichero))
					.map(funcion_deString_aT).collect(Collectors.toList());
		} catch (IOException e) {
			System.out
					.println("Error en lectura del fichero: " + nombreFichero);
		}

		return res;
	}
}
