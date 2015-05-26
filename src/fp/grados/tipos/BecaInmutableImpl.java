package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;

public final class BecaInmutableImpl implements BecaInmutable {
	private static final Double CUANTIA_MINIMA = 1500.0;
	private final String codigo;
	private final TipoBeca tipo;
	private final Double cuantiaTotal;
	private final Integer duracion;

	/**** Constructores ****/
	// region Constructores
	public BecaInmutableImpl(String codigo, Double cuantiaTotal,
			Integer duracion, TipoBeca tipo) {
		checkCodigo(codigo);
		checkCuantia(cuantiaTotal);
		checkDuracion(duracion);

		this.codigo = codigo;
		this.tipo = tipo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
	}

	public BecaInmutableImpl(String codigo, TipoBeca tipo) {
		this(codigo, CUANTIA_MINIMA, 1, tipo);
	}
	
	public BecaInmutableImpl(String beca) {
		// ABC1234,6000.0,12,ORDINARIA
		String[] s = beca.split(",");
		if (s.length != 4)
			throw new IllegalArgumentException(
					"PersonaImpl.ConstructorCadena:: La cadena constructor ha de ser del tipo: Fundamentos de Programación#1234567#12.0#ANUAL#1");
		for (int i = 0; i < s.length; i++)
			s[i] = s[i].trim();

		checkCodigo(s[0]);
		checkCuantia(new Double(s[1]));
		checkDuracion(new Integer(s[2]));

		this.codigo = s[0];
		this.cuantiaTotal = new Double(s[1]);
		this.duracion = new Integer(s[2]);
		this.tipo = TipoBeca.valueOf(s[3]);
	}

	// endregion

	/**** Gets ****/
	// region Gets
	public String getCodigo() {
		return codigo;
	}

	public TipoBeca getTipo() {
		return tipo;
	}

	public Double getCuantiaTotal() {
		return cuantiaTotal;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public Double getCuantiaMensual() {
		return getCuantiaTotal() / getDuracion();
	}

	// endregion

	/**** Exceptions ****/
	// region Exceptions
	public void checkCodigo(String codigo) {
		if (codigo.length() != 7) {
			throw new ExcepcionBecaNoValida(
					"BecaImpl.checkCodigo:: El código debe constar de 7 caracteres alfanumericos");
		}

		for (int i = 0; i < codigo.length(); i++) {
			if (i <= 2) {
				if (!Character.isLetter(codigo.charAt(i))) {
					throw new ExcepcionBecaNoValida(
							"BecaImpl.checkCodigo:: El código es tal que CCCNNNN");
				}
			} else {
				if (!Character.isDigit(codigo.charAt(i))) {
					throw new ExcepcionBecaNoValida(
							"BecaImpl.checkCodigo:: El código es tal que CCCNNNN");
				}
			}
		}
	}

	public void checkCuantia(Double cuantia) {
		if (cuantia < CUANTIA_MINIMA) {
			throw new ExcepcionBecaNoValida(
					"BecaImpl.checkCuantia:: La cuantía debe ser como mínimo "
							+ CUANTIA_MINIMA + " euros");
		}
	}

	public void checkDuracion(Integer duracion) {
		if (duracion < 1) {
			throw new ExcepcionBecaNoValida(
					"BecaImpl.checkDuracion:: La beca debe durar al menos un mes");
		}
	}

	// endregion

	public String toString() {
		return "[" + getCodigo() + ", " + getTipo() + "]";
	}

	public int compareTo(BecaInmutable beca) {
		int c = getCodigo().compareTo(beca.getCodigo());
		if (c == 0)
			c = getTipo().compareTo(beca.getTipo());
		return c;
	}

	public boolean equals(Object o) {
		if (o instanceof BecaInmutable) {
			BecaInmutable b = (BecaInmutable) o;
			return getCodigo().equals(b.getCodigo())
					&& getTipo().equals(b.getTipo());
		}
		return false;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		return prime * result + getCodigo().hashCode() + getTipo().hashCode();
	}
}
