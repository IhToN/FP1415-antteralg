package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;

public class AsignaturaImpl implements Asignatura {
	private String nombre;
	private String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;
	private Departamento departamento;

	/**** Constructores ****/
	// region Constructores
	public AsignaturaImpl(String nombre, String codigo, Double creditos,
			TipoAsignatura tipo, Integer curso, Departamento departamento) {
		checkCodigo(codigo);
		checkCurso(curso);
		checkCreditos(creditos);

		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
		this.departamento = departamento;
		if (departamento != null) {
			departamento.nuevaAsignatura(this);
		}
	}

	public AsignaturaImpl(String asignatura) {
		// "Fundamentos de Programación#1234567#12.0#ANUAL#1"
		String[] s = asignatura.split("#");
		if (s.length != 5)
			throw new IllegalArgumentException(
					"PersonaImpl.ConstructorCadena:: La cadena constructor ha de ser del tipo: Fundamentos de Programación#1234567#12.0#ANUAL#1");
		for (int i = 0; i < s.length; i++) s[i] = s[i].trim();
		
		checkCodigo(s[1]);
		checkCreditos(new Double(s[2]));
		checkCurso(new Integer(s[4]));

		this.nombre = s[0];
		this.codigo = s[1];
		this.creditos = new Double(s[2]);
		this.tipo = TipoAsignatura.valueOf(s[3]);
		this.curso = new Integer(s[4]);
		this.departamento = null;
		if (departamento != null) {
			departamento.nuevaAsignatura(this);
		}
	}

	// endregion

	public String getNombre() {
		return nombre;
	}

	public String getAcronimo() {
		String acronimo = "";
		for (int cnt = 0; cnt < getNombre().length(); cnt++) {
			if (Character.isUpperCase(getNombre().charAt(cnt))) {
				char letra = getNombre().charAt(cnt);
				acronimo = acronimo + letra;
			}
		}
		return acronimo;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getCreditos() {
		return creditos;
	}

	public TipoAsignatura getTipo() {
		return tipo;
	}

	public Integer getCurso() {
		return curso;
	}

	public String toString() {
		return "(" + getCodigo() + ") " + getNombre();
	}

	// Exceptions
	private void checkCodigo(String codigo) {
		if (codigo.length() != 7)
			throw new ExcepcionAsignaturaNoValida(
					"AsignaturaImpl.checkCodigo:: El código debe estar formado por 7 dígitos");
		for (int i = 0; i < codigo.length(); i++) {
			if (!Character.isDigit(codigo.charAt(i))) {
				throw new ExcepcionAsignaturaNoValida(
						"AsignaturaImpl.checkCodigo:: El código debe ser numérico");
			}
		}
	}

	private void checkCurso(Integer curso) {
		if (curso < 1 || curso > 4)
			throw new ExcepcionAsignaturaNoValida(
					"AsignaturaImpl.checkCurso:: El curso debe estar entre 1º y 4º");
	}

	private void checkCreditos(Double creditos) {
		if (creditos <= 0)
			throw new ExcepcionAsignaturaNoValida(
					"AsignaturaImpl.checkCreditos:: La asignatura debe tener al menos un crédito");
	}

	// compareTo, equals and hashCode
	public int compareTo(Asignatura asg) {
		int c = getCodigo().compareTo(asg.getCodigo());
		return c;
	}

	public boolean equals(Object o) {
		if (o instanceof Asignatura) {
			Asignatura a = (Asignatura) o;
			return getCodigo().equals(a.getCodigo());
		}
		return false;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		return prime * result + getCodigo().hashCode();
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento dept) {
		Departamento antDept = this.getDepartamento();
		if (antDept != dept) {
			this.departamento = dept;

			if (antDept != null)
				antDept.eliminaAsignatura(this);
			if (dept != null)
				dept.nuevaAsignatura(this);
		}
	}
}
