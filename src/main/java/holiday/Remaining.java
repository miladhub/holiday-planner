package holiday;

public class Remaining {
	public int vacationHours;
	public int hoursOff;

	public Remaining(int vacationHours, int hoursOff) {
		this.vacationHours = vacationHours;
		this.hoursOff = hoursOff;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hoursOff;
		result = prime * result + vacationHours;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Remaining other = (Remaining) obj;
		if (hoursOff != other.hoursOff)
			return false;
		if (vacationHours != other.vacationHours)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Remaining of " + vacationHours + " vacation hours and " + hoursOff + " hours off";
	}
}
