package fp.grados.tipos;

public interface Beca extends Comparable<Beca> {
	public String getCodigo();

	public TipoBeca getTipo();

	public Double getCuantiaTotal();

	public void setCuantiaTotal(Double ta);

	public Integer getDuracion();

	public void setDuracion(Integer l);

	public Double getCuantiaMensual();
}
