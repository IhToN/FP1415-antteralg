package fp.grados.tipos;

public interface Nota extends Comparable<Nota> {
	public Asignatura getAsignatura();
	
	public Integer getCursoAcademico();

	public Double getValor();

	public Convocatoria getConvocatoria();
	
	public Boolean getMencionHonor();

	public Calificacion getCalificacion();
}
