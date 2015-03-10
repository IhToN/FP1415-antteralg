package fp.grados.tipos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import fp.grados.excepciones.ExcepcionPersonaNoValida;

public class PersonaImpl implements Persona {
	private String dni;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String email;

	//region Constructores
	public PersonaImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento, String email) {
		checkDNI(dni);
		checkEmail(email);

		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
	}

	public PersonaImpl(String dni, String nombre, String apellidos,
			LocalDate fechaNacimiento) {
		this(dni, nombre, apellidos, fechaNacimiento, "");
	}
	
	/* T10 */
	public PersonaImpl(String persona){
		//"12345678Z,Juan,López García,20/01/1998,juan@acmemail.com"
		//Para la fecha también se admite la forma 05/02/1998, completando con ceros los campos día y mes.
		
		String[] s = persona.split(",");
		if(s.length != 5)
			throw new IllegalArgumentException(
					"PersonaImpl.ConstructorCadena:: La cadena constructor ha de ser del tipo: 12345678Z,Juan,López García,20/1/1998,juan@alum.us.es");
		for (int i = 0; i < s.length; i++) s[i] = s[i].trim();
		
		checkDNI(s[0]);
		checkEmail(s[4]);
		
		this.dni = s[0];
		this.nombre = s[1];
		this.apellidos = s[2];
		this.fechaNacimiento = LocalDate.parse(s[3], DateTimeFormatter.ofPattern("d/M/y"));
		this.email = s[4];
	}
	//endregion

	public String getDNI() {
		return dni;
	}

	public void setDNI(String dni) {
		checkDNI(dni);

		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String sn1) {
		apellidos = sn1;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate bday) {
		this.fechaNacimiento = bday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		checkEmail(email);

		this.email = email;
	}

	public Integer getEdad() {
		/*
		 * Integer edad = LocalDate.now().getYear() - fechaNacimiento.getYear();
		 * return (LocalDate.now().getDayOfYear() >
		 * fechaNacimiento.getDayOfYear() ? edad : edad + 1);
		 */
		return (int) getFechaNacimiento().until(LocalDate.now(),
				ChronoUnit.YEARS);
	}

	public String toString() {
		return getDNI()
				+ " - "
				+ getApellidos()
				+ ", "
				+ getNombre()
				+ " - "
				+ getFechaNacimiento().format(
						DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	// Exceptions
	private void checkDNI(String dni) {
		final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
		if (dni.length() != 9)
			throw new ExcepcionPersonaNoValida(
					"PersonaImpl.checkDNI:: El DNI no es válido");

		for (int i = 0; i < dni.length() - 1; i++) {
			if (!Character.isDigit(dni.charAt(i)))
				throw new ExcepcionPersonaNoValida(
						"PersonaImpl.checkDNI:: El DNI no es válido");
		}

		Character c = dni.charAt(dni.length() - 1);
		Integer dniValue = new Integer(dni.substring(0, 8));
		if (!c.equals(LETRAS_DNI.charAt(dniValue % 23))) {
			throw new ExcepcionPersonaNoValida(
					"PersonaImpl.checkDNI:: El DNI no es válido");
		}
	}

	private void checkEmail(String email) {
		if (!(email.contains("@") || email.isEmpty())) {
			throw new ExcepcionPersonaNoValida(
					"PersonaImpl.checkEmail:: El Email introducido no es válido");
		}
	}

	public int compareTo(Persona p) {
		int c = getApellidos().compareTo(p.getApellidos());
		if (c == 0) {
			c = getNombre().compareTo(p.getNombre());
			if (c == 0)
				c = getDNI().compareTo(p.getDNI());
		}
		return c;
	}

	public boolean equals(Object o) {
		if (o instanceof Persona) {
			Persona p = (Persona) o;
			return getApellidos().equals(p.getApellidos())
					&& getNombre().equals(p.getNombre())
					&& getDNI().equals(p.getDNI());
		}
		return false;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		return prime * result + getApellidos().hashCode()
				+ getNombre().hashCode() + getDNI().hashCode();
	}
}
