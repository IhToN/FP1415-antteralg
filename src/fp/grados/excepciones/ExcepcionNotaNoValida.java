package fp.grados.excepciones;

public class ExcepcionNotaNoValida extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionNotaNoValida() {
		super();
	}

	public ExcepcionNotaNoValida(String texto) {
		super(texto);
	}
}
