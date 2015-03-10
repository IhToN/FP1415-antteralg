package fp.grados.tipos;

public interface Espacio extends Comparable<Espacio> {
	public TipoEspacio getTipo();

	public void setTipo(TipoEspacio type);

	public String getNombre();

	public void setNombre(String name);

	public Integer getCapacidad();

	public void setCapacidad(Integer size);

	public Integer getPlanta();
}
