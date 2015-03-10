package fp.grados.excepciones;

@SuppressWarnings("serial")
public class ExcepcionGradoNoValido extends RuntimeException {
	public ExcepcionGradoNoValido() {
		super();
	}
	
	public ExcepcionGradoNoValido(String msg) {
		super(msg);
	}

}
