package fp.grados.tipos;

public interface BecaInmutable extends Comparable<BecaInmutable> {
	public String getCodigo();

	public TipoBeca getTipo();

	public Double getCuantiaTotal();

	public Integer getDuracion();

	public Double getCuantiaMensual();
}
