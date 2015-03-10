package fp.grados.tipos;

import java.util.Set;

public interface Despacho extends Espacio {

	public Set<Profesor> getProfesores();

	public void setProfesores(Set<Profesor> profesores);

}
