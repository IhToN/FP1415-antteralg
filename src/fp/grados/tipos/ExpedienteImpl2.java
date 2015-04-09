package fp.grados.tipos;

import java.util.OptionalDouble;

public class ExpedienteImpl2 extends ExpedienteImpl {

	public Double getNotaMedia() {
		Double res = 0.0;
		OptionalDouble optRes = getNotas().stream()
				.filter(n -> n.getValor() >= 5.0)
				.mapToDouble(n -> n.getValor()).average();
		if (optRes.isPresent())
			res = optRes.getAsDouble();
		return res;
	}

}
