package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionEspacioNoValido;

public class EspacioImpl implements Espacio {
	private TipoEspacio tipo;
	private String nombre;
	private Integer capacidad;
	private Integer planta;

	/**** Constructores ****/
	// region Constructores
	public EspacioImpl(TipoEspacio tipo, String nombre, Integer capacidad,
			Integer planta) {
		checkCapacidad(capacidad);

		this.tipo = tipo;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.planta = planta;
	}

	public EspacioImpl(String espacio) {
		// A0.10,0,100,TEORIA
		String[] s = espacio.split(",");
		if (s.length != 4)
			throw new IllegalArgumentException(
					"NotaImpl.ConstructorCadena:: La cadena constructor ha de ser del tipo: Fundamentos de Programación#1234567#12.0#ANUAL#1;2014;PRIMERA;10.0;true");
		for (int i = 0; i < s.length; i++)
			s[i] = s[i].trim();

		checkCapacidad(new Integer(s[2]));

		this.nombre = s[0];
		this.planta = new Integer(s[1]);
		this.capacidad = new Integer(s[2]);
		this.tipo = TipoEspacio.valueOf(s[3]);
	}

	// endregion

	public TipoEspacio getTipo() {
		return tipo;
	}

	public void setTipo(TipoEspacio type) {
		this.tipo = type;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer size) {
		checkCapacidad(size);

		this.capacidad = size;
	}

	public Integer getPlanta() {
		return planta;
	}

	public String toString() {
		return getNombre() + " (planta " + getPlanta() + ")";
	}

	public void checkCapacidad(Integer capacidad) {
		if (capacidad < 1) {
			throw new ExcepcionEspacioNoValido(
					"EspacioImpl.checkCapacidad:: En el aula debe caber al menos una persona");
		}
	}

	public int compareTo(Espacio e) {
		int c = getPlanta().compareTo(e.getPlanta());
		if (c == 0)
			c = getNombre().compareTo(e.getNombre());
		return c;
	}

	public boolean equals(Object o) {
		if (o instanceof Espacio) {
			Espacio e = (Espacio) o;
			return getPlanta().equals(e.getPlanta())
					&& getNombre().equals(e.getNombre());
		}
		return false;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		return prime * result + getPlanta().hashCode() + getNombre().hashCode();
	}
}
