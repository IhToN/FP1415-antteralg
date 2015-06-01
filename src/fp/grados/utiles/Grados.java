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
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.AlumnoImpl2;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Beca;
import fp.grados.tipos.BecaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.CentroImpl2;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.DepartamentoImpl2;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.GradoImpl2;
import fp.grados.tipos.Nota;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoBeca;
import fp.grados.tipos.TipoEspacio;
import fp.grados.tipos.Tutoria;

public final class Grados {
	/** T12 - setUsarJava8 **/
	private static Boolean usarJava8 = true;

	public static void setUsarJava8(Boolean b) {
		usarJava8 = b;
	}

	/** T11 - Factorías **/
	// region Departamento
	private static Set<Departamento> departamentos = new HashSet<Departamento>();

	public static Departamento createDepartamento(String nombre) {
		Departamento res;
		if (usarJava8)
			res = new DepartamentoImpl2(nombre);
		else
			res = new DepartamentoImpl(nombre);
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

	public static Beca createBeca(String codigo, Double cuantiaTotal,
			Integer duracion, TipoBeca tipo) {
		Beca res = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
		actualizaPoblacionalesBeca(res);
		return res;
	}

	public static Beca createBeca(String codigo, TipoBeca tipo) {
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
		List<Beca> res = leeFichero(nombreFichero, s -> createBeca(s));
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

	public static Asignatura createAsignatura(String nombre, String codigo,
			Double creditos, TipoAsignatura tipo, Integer curso,
			Departamento departamento) {
		Asignatura res = new AsignaturaImpl(nombre, codigo, creditos, tipo,
				curso, departamento);
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
		/*if (!asignaturas.containsKey(codigo))
			throw new IllegalArgumentException(
					"Grados.getAsignaturaCreada:: La asignatura no está creada");*/
		return asignaturas.get(codigo);
	}

	// endregion
	// region Alumno
	private static Set<Alumno> alumnos = new HashSet<Alumno>();

	public static Alumno createAlumno(String dni, String nombre,
			String apellidos, LocalDate fechaNacimiento, String email) {
		Alumno res;
		if (usarJava8)
			res = new AlumnoImpl2(dni, nombre, apellidos, fechaNacimiento,
					email);
		else
			res = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento, email);
		alumnos.add(res);
		return res;
	}

	public static Alumno createAlumno(Alumno alumno) {
		Alumno res;
		if (usarJava8)
			res = new AlumnoImpl2(alumno.getDNI(), alumno.getNombre(),
					alumno.getApellidos(), alumno.getFechaNacimiento(),
					alumno.getEmail());
		else
			res = new AlumnoImpl(alumno.getDNI(), alumno.getNombre(),
					alumno.getApellidos(), alumno.getFechaNacimiento(),
					alumno.getEmail());
		copiaAsignaturasAlumno(res, alumno);
		copiaExpedienteAlumno(res, alumno);
		alumnos.add(res);
		return res;
	}

	public static Alumno createAlumno(String alumno) {
		Alumno res;
		if (usarJava8)
			res = new AlumnoImpl2(alumno);
		else
			res = new AlumnoImpl(alumno);
		alumnos.add(res);
		return res;
	}

	public static List<Alumno> createAlumnos(String nombreFichero) {
		List<Alumno> res = leeFichero(nombreFichero, s -> createAlumno(s));
		return res;
	}

	private static void copiaAsignaturasAlumno(Alumno res, Alumno alumno) {
		for (Asignatura a : alumno.getAsignaturas()) {
			res.matriculaAsignatura(a);
		}
	}

	private static void copiaExpedienteAlumno(Alumno res, Alumno alumno) {
		for (Nota n : alumno.getExpediente().getNotas()) {
			res.evaluaAlumno(n.getAsignatura(), n.getCursoAcademico(),
					n.getConvocatoria(), n.getValor());
		}
	}

	public static Integer getNumAlumnosCreados() {
		return alumnos.size();
	}

	public static Set<Alumno> getAlumnosCreados() {
		return new HashSet<Alumno>(alumnos);
	}

	// endregion
	// region Espacio
	private static SortedSet<Espacio> espacios = new TreeSet<Espacio>();

	public static Espacio createEspacio(TipoEspacio tipo, String nombre,
			Integer capacidad, Integer planta) {
		Espacio res = new EspacioImpl(tipo, nombre, capacidad, planta);
		espacios.add(res);
		return res;
	}

	public static Espacio createEspacio(Espacio espacio) {
		Espacio res = new EspacioImpl(espacio.getTipo(), espacio.getNombre(),
				espacio.getCapacidad(), espacio.getPlanta());
		espacios.add(res);
		return res;
	}

	public static Espacio createEspacio(String espacio) {
		Espacio res = new EspacioImpl(espacio);
		espacios.add(res);
		return res;
	}

	public static List<Espacio> createEspacios(String nombreFichero) {
		List<Espacio> res = leeFichero(nombreFichero, s -> createEspacio(s));
		return res;
	}

	public static Integer getNumEspaciosCreados() {
		return getEspaciosCreados().size();
	}

	public static SortedSet<Espacio> getEspaciosCreados() {
		return new TreeSet<Espacio>(espacios);
	}

	public static Integer getPlantaMayorEspacio() {
		Boolean isFirst = true;
		Integer res = null;
		for (Espacio e : getEspaciosCreados()) {
			if (isFirst || res < e.getPlanta()) {
				isFirst = false;
				res = e.getPlanta();
			}
		}
		return res;
	}

	public static Integer getPlantaMenorEspacio() {
		Boolean isFirst = true;
		Integer res = null;
		for (Espacio e : getEspaciosCreados()) {
			if (isFirst || res > e.getPlanta()) {
				isFirst = false;
				res = e.getPlanta();
			}
		}
		return res;
	}

	// endregion
	// region Despacho
	public static Despacho createDespacho(String nombre, Integer capacidad,
			Integer planta) {
		Despacho res = new DespachoImpl(nombre, capacidad, planta);
		return res;
	}

	public static Despacho createDespacho(Despacho despacho) {
		Despacho res = new DespachoImpl(despacho.getNombre(),
				despacho.getCapacidad(), despacho.getPlanta(),
				despacho.getProfesores());
		return res;
	}

	public static Despacho createDespacho(String despacho) {
		Despacho res = new DespachoImpl(despacho);
		return res;
	}

	public static List<Despacho> createDespachos(String nombreFichero) {
		List<Despacho> res = leeFichero(nombreFichero, s -> createDespacho(s));
		return res;
	}

	// endregion
	// region Grado
	public static Set<Grado> grados = new HashSet<Grado>();

	public static Grado createGrado(String nombre,
			Set<Asignatura> asignaturasObligatorias,
			Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas) {
		Grado res;
		if (usarJava8)
			res = new GradoImpl2(nombre, asignaturasObligatorias,
					asignaturasOptativas, numeroMinimoCreditosOptativas);
		else
			res = new GradoImpl(nombre, asignaturasObligatorias,
					asignaturasOptativas, numeroMinimoCreditosOptativas);
		grados.add(res);
		return res;
	}

	public static Integer getNumGradosCreados() {
		return grados.size();
	}

	public static Set<Grado> getGradosCreados() {
		return new HashSet<Grado>(grados);
	}

	public static Double getMediaAsignaturasGrados() {
		Double res = 0.0;
		if (grados.isEmpty())
			return res;
		return getMediaAsignaturasObligatoriasGrados()
				+ getMediaAsignaturasOptativasGrados();
	}

	public static Double getMediaAsignaturasObligatoriasGrados() {
		Double res = 0.0;
		if (grados.isEmpty())
			return res;
		for (Grado g : grados) {
			res += g.getAsignaturasObligatorias().size();
		}
		return res / grados.size();
	}

	public static Double getMediaAsignaturasOptativasGrados() {
		Double res = 0.0;
		if (grados.isEmpty())
			return res;
		for (Grado g : grados) {
			res += g.getAsignaturasOptativas().size();
		}
		return res / grados.size();
	}

	// endregion
	// region Centro
	private static Set<Centro> centros = new TreeSet<Centro>();

	public static Centro createCentro(String nombre, String direccion,
			Integer numPlantas, Integer numSotanos) {
		Centro res;
		if (usarJava8)
			res = new CentroImpl2(nombre, direccion, numPlantas, numSotanos);
		else
			res = new CentroImpl(nombre, direccion, numPlantas, numSotanos);
		centros.add(res);
		return res;
	}

	public static Centro createCentro(Centro centro) {
		Centro res;
		if (usarJava8)
			res = new CentroImpl2(centro.getNombre(), centro.getDireccion(),
					centro.getNumeroPlantas(), centro.getNumeroSotanos());
		else
			res = new CentroImpl(centro.getNombre(), centro.getDireccion(),
					centro.getNumeroPlantas(), centro.getNumeroSotanos());
		copiaEspaciosCentro(res, centro);
		centros.add(res);
		return res;
	}

	private static void copiaEspaciosCentro(Centro res, Centro centro) {
		for (Espacio e : centro.getEspacios()) {
			res.nuevoEspacio(e);
		}
	}

	public static Integer getNumCentrosCreados() {
		return centros.size();
	}

	public static Set<Centro> getCentrosCreados() {
		return new HashSet<Centro>(centros);
	}

	public static Integer getMaxPlantas() {
		Boolean isFirst = true;
		Integer res = null;
		for (Centro c2 : centros) {
			if (isFirst || c2.getNumeroPlantas() > res) {
				isFirst = false;
				res = c2.getNumeroPlantas();
			}
		}
		return res;
	}

	public static Integer getMaxSotanos() {
		Boolean isFirst = true;
		Integer res = null;
		for (Centro c2 : centros) {
			if (isFirst || c2.getNumeroSotanos() > res) {
				isFirst = false;
				res = c2.getNumeroSotanos();
			}
		}
		return res;
	}

	public static Double getMediaPlantas() {
		Double res = 0.0;
		if (centros.isEmpty())
			return res;
		for (Centro c : centros)
			res += c.getNumeroPlantas();
		return res / centros.size();
	}

	public static Double getMediaSotanos() {
		Double res = 0.0;
		if (centros.isEmpty())
			return res;
		for (Centro c : centros)
			res += c.getNumeroSotanos();
		return res / centros.size();
	}

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
