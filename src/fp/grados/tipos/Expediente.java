package fp.grados.tipos;

import java.util.List;

public interface Expediente {
	List<Nota> getNotas();

	void nuevaNota(Nota n);

	Double getNotaMedia();
	
	/* Boletín 12 */
	List<Nota> getNotasOrdenadasPorAsignatura();
	Nota getMejorNota();
}