package holiday.jsf;

public class PlannedMonth {
	private final String month;
	private final double hoursOff;
	private final double vacationHours;
	private Double takenHoursOff, takenVacationHours;
	public PlannedMonth(String month, double hoursOff, double vacationHours, Double takenHoursOff, Double takenVacationHours) {
		super();
		this.month = month;
		this.hoursOff = hoursOff;
		this.vacationHours = vacationHours;
		this.takenHoursOff = takenHoursOff;
		this.takenVacationHours = takenVacationHours;
	}
	public String getMonth() {
		return month;
	}
	public double getHoursOff() {
		return hoursOff;
	}
	public double getVacationHours() {
		return vacationHours;
	}
	public Double getTakenHoursOff() {
		return takenHoursOff;
	}
	public void setTakenHoursOff(Double takenHoursOff) {
		this.takenHoursOff = takenHoursOff;
	}
	public Double getTakenVacationHours() {
		return takenVacationHours;
	}
	public void setTakenVacationHours(Double takenVacationHours) {
		this.takenVacationHours = takenVacationHours;
	}
}
