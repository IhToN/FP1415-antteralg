package fp.grados.tipos;

import java.time.LocalDate;

public interface Persona extends Comparable<Persona> {
	public String getDNI();

	public void setDNI(String dni);

	public String getNombre();

	public void setNombre(String name);

	public String getApellidos();

	public void setApellidos(String sn1);

	public LocalDate getFechaNacimiento();

	public void setFechaNacimiento(LocalDate bday);

	public String getEmail();

	public void setEmail(String email);

	public Integer getEdad();
}
