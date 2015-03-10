package fp.grados.tipos;

import java.time.LocalDate;

public interface Becario extends Alumno {
	public Beca getBeca();
	public LocalDate getFechaComienzo();
	public void setFechaComienzo(LocalDate fc);
	public LocalDate getFechaFin();
}
