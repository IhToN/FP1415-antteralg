package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Locale;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;

public class TutoriaImpl implements Tutoria {
	private DayOfWeek diaSemana;
	private LocalTime horaComienzo;
	private LocalTime horaFin;

	/**** Constructores ****/
	// region Constructores
	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo,
			LocalTime horaFin) {
		checkFinde(diaSemana);
		checkDuracion((int) horaComienzo.until(horaFin,
				(TemporalUnit) ChronoUnit.MINUTES));

		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaFin;
	}

	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo,
			Integer duracion) {
		this(diaSemana, horaComienzo, horaComienzo.plusMinutes(duracion));
	}

	public TutoriaImpl(String tutoria) {
		// L,15:30,17:30
		String[] s = tutoria.split(",");
		if (s.length != 3)
			throw new IllegalArgumentException(
					"NotaImpl.ConstructorCadena:: La cadena constructor ha de ser del tipo: Fundamentos de Programación#1234567#12.0#ANUAL#1;2014;PRIMERA;10.0;true");
		for (int i = 0; i < s.length; i++)
			s[i] = s[i].trim();

		DayOfWeek diaSemana = dayFromChar(s[0]);
		LocalTime horaComienzo = LocalTime.parse(s[1]);
		LocalTime horaFin = LocalTime.parse(s[2]);

		checkFinde(diaSemana);
		checkDuracion((int) horaComienzo.until(horaFin,
				(TemporalUnit) ChronoUnit.MINUTES));

		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaFin;
	}

	// endregion

	/**** Get & Set ****/
	// region GetSet
	public DayOfWeek getDiaSemana() {
		return diaSemana;
	}

	public LocalTime getHoraComienzo() {
		return horaComienzo;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public Integer getDuracion() {
		return (int) getHoraComienzo().until(getHoraFin(),
				(TemporalUnit) ChronoUnit.MINUTES);
	}

	// endregion

	/**** Exceptions ****/
	// region Exceptions
	private void checkFinde(DayOfWeek diaSemana) {
		switch (diaSemana) {
		case SATURDAY:
		case SUNDAY:
			throw new ExcepcionTutoriaNoValida(
					"TutoriaImpl.checkFinde:: Una tutoría no puede ser en fin de semana");
		default:
			break;
		}
	}

	private void checkDuracion(Integer duracion) {
		if (duracion < 15) {
			throw new ExcepcionTutoriaNoValida(
					"TutoriaImpl.checkDuracion:: La duración debe ser de al menos 15 minutos");
		}
	}

	// endregion

	public String toString() {
		return getDiaSemana().getDisplayName(TextStyle.NARROW,
				new Locale("es", "ES"))
				+ " " + getHoraComienzo() + "-" + getHoraFin();
	}

	public int compareTo(Tutoria t) {
		int c = getDiaSemana().compareTo(t.getDiaSemana());
		if (c == 0)
			c = getHoraComienzo().compareTo(t.getHoraComienzo());
		return c;
	}

	public boolean equals(Object o) {
		if (o instanceof Tutoria) {
			Tutoria t = (Tutoria) o;
			return getDiaSemana().equals(t.getDiaSemana())
					&& getHoraComienzo().equals(t.getHoraComienzo());
		}
		return false;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;

		return prime * result + getDiaSemana().hashCode()
				+ getHoraComienzo().hashCode();
	}

	public static DayOfWeek dayFromChar(String c) {
		DayOfWeek res = DayOfWeek.SATURDAY;
		switch (c) {
		case "L":
			res = DayOfWeek.MONDAY;
			break;
		case "M":
			res = DayOfWeek.TUESDAY;
			break;
		case "X":
			res = DayOfWeek.WEDNESDAY;
			break;
		case "J":
			res = DayOfWeek.THURSDAY;
			break;
		case "V":
			res = DayOfWeek.FRIDAY;
			break;
		default:
			break;
		}
		return res;
	}
}
