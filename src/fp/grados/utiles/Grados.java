package fp.grados.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Beca;
import fp.grados.tipos.BecaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoBeca;
import fp.grados.tipos.Tutoria;

public class Grados {

	/** T11 - Factorías **/
	// region Departamento
	private static Set<Departamento> departamentos = new HashSet<Departamento>();

	public static Departamento createDepartamento(String nombre) {
		Departamento res = new DepartamentoImpl(nombre);
		departamentos.add(res);
		return res;
	}

	public static Integer getNumDepartamentosCreados() {
		return departamentos.size();
	}

	public static Set<Departamento> getDepartamentosCreados() {
		return new HashSet<Departamento>(departamentos);
	}

	// endregion
	// region Profesor
	private static Boolean usarImplementacionMapProfesor = true;
	private static Set<Profesor> profesores = new HashSet<Profesor>();

	public static Profesor createProfesor(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email,
			Categoria categoria, Departamento departamento) {
		Profesor res;
		if (usarImplementacionMapProfesor)
			res = new ProfesorImpl2(dni, nombre, apellidos, fechaNacimiento,
					email, categoria, departamento);
		else
			res = new ProfesorImpl(dni, nombre, apellidos, fechaNacimiento,
					email, categoria, departamento);
		profesores.add(res);
		return res;
	}

	public static Profesor createProfesor(Profesor profesor) {
		Profesor res;
		if (usarImplementacionMapProfesor)
			res = new ProfesorImpl2(profesor.getDNI(), profesor.getNombre(),
					profesor.getApellidos(), profesor.getFechaNacimiento(),
					profesor.getEmail(), profesor.getCategoria(),
					profesor.getDepartamento());
		else
			res = new ProfesorImpl2(profesor.getDNI(), profesor.getNombre(),
					profesor.getApellidos(), profesor.getFechaNacimiento(),
					profesor.getEmail(), profesor.getCategoria(),
					profesor.getDepartamento());
		copiaAsignaturasProfesor(res, profesor);
		copiaTutoriasProfesor(res, profesor);
		profesores.add(res);
		return res;
	}

	public static void setUsarImplementacionMapProfesor(Boolean b) {
		usarImplementacionMapProfesor = b;
	}

	public static Integer getNumProfesoresCreados() {
		return profesores.size();
	}

	public static Set<Profesor> getProfesoresCreados() {
		return new HashSet<Profesor>(profesores);
	}

	private static void copiaAsignaturasProfesor(Profesor res, Profesor profesor) {
		for (Asignatura a : profesor.getAsignaturas()) {
			res.imparteAsignatura(a, profesor.dedicacionAsignatura(a));
		}
	}

	private static void copiaTutoriasProfesor(Profesor res, Profesor profesor) {
		for (Tutoria t : profesor.getTutorias()) {
			res.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(),
					t.getDiaSemana());
		}
	}

	// endregion
	// region Beca
	private static Set<Beca> becas = new HashSet<Beca>();
	private static Integer[] numBecasPorTipo = { 0, 0, 0 };

	public static Beca createBeca(String codigo, Double cuantiaTotal, Integer duracion,
			TipoBeca tipo){
		Beca res = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
		actualizaPoblacionalesBeca(res);
		return res;
	}
	
	public static Beca createBeca(String codigo, TipoBeca tipo){
		Beca res = new BecaImpl(codigo, tipo);
		actualizaPoblacionalesBeca(res);
		return res;
	}

	public static Beca createBeca(String beca) {
		Beca res = new BecaImpl(beca);
		actualizaPoblacionalesBeca(res);
		return res;
	}

	public static Beca createBeca(Beca beca) {
		Beca res = new BecaImpl(beca.getCodigo(), beca.getCuantiaTotal(),
				beca.getDuracion(), beca.getTipo());
		actualizaPoblacionalesBeca(res);
		return res;
	}
	
	public static List<Beca> createBecas(String nombreFichero) {
		List<Beca> res = leeFichero(nombreFichero,
				s -> createBeca(s));
		return res;
	}

	public static Integer getNumBecasCreadas() {
		return becas.size();
	}

	public static Set<Beca> getBecasCreadas() {
		return new HashSet<Beca>(becas);
	}

	public static Integer getNumBecasTipo(TipoBeca tipo) {
		Integer res = 0;
		switch (tipo) {
		case ORDINARIA:
			res = numBecasPorTipo[0];
			break;
		case MOVILIDAD:
			res = numBecasPorTipo[1];
			break;
		case EMPRESA:
			res = numBecasPorTipo[2];
			break;
		default:
			break;
		}
		return res;
	}

	private static void actualizaPoblacionalesBeca(Beca beca) {
		becas.add(beca);
		switch (beca.getTipo()) {
		case ORDINARIA:
			numBecasPorTipo[0]++;
			break;
		case MOVILIDAD:
			numBecasPorTipo[1]++;
			break;
		case EMPRESA:
			numBecasPorTipo[2]++;
			break;
		default:
			break;
		}
	}

	// endregion
	// region Asignatura
	private static Map<String, Asignatura> asignaturas = new HashMap<String, Asignatura>();

	public static Asignatura createAsignatura(String nombre, String codigo, Double creditos,
			TipoAsignatura tipo, Integer curso, Departamento departamento) {
		Asignatura res = new AsignaturaImpl(nombre, codigo, creditos, tipo, curso, departamento);
		asignaturas.put(res.getCodigo(), res);
		return res;
	}

	public static Asignatura createAsignatura(String asignatura) {
		Asignatura res = new AsignaturaImpl(asignatura);
		asignaturas.put(res.getCodigo(), res);
		return res;
	}

	public static List<Asignatura> createAsignaturas(String nombreFichero) {
		List<Asignatura> res = leeFichero(nombreFichero,
				s -> createAsignatura(s));
		return res;
	}

	public static Integer getNumAsignaturasCreadas() {
		return asignaturas.size();
	}

	public static Set<Asignatura> getAsignaturasCreadas() {
		return new HashSet<Asignatura>(asignaturas.values());
	}

	public static Set<String> getCodigosAsignaturasCreadas() {
		return new HashSet<String>(asignaturas.keySet());
	}

	public static Asignatura getAsignaturaCreada(String codigo) {
		if (!asignaturas.containsKey(codigo))
			throw new IllegalArgumentException(
					"Grados.getAsignaturaCreada:: La asignatura no está creada");
		return asignaturas.get(codigo);
	}

	// endregion
	// region Alumno
	//TODO To la mierda del alunno
	// endregion
	// region Espacio
	//TODO To la mierda del espacio
	// endregion
	// region Despacho
	//TODO To la mierda del despacho
	// endregion
	// region Grado
	//TODO To la mierda del grado
	// endregion
	// region Centro
	//TODO To la mierda del centro
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
