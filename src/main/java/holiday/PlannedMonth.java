package holiday;

public class PlannedMonth {
	private final String month;
	private final int hoursOff;
	private final int vacationHours;
	private Integer takenHoursOff, takenVacationHours;
	public PlannedMonth(String month, int hoursOff, int vacationHours, Integer takenHoursOff, Integer takenVacationHours) {
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
	public int getHoursOff() {
		return hoursOff;
	}
	public int getVacationHours() {
		return vacationHours;
	}
	public Integer getTakenHoursOff() {
		return takenHoursOff;
	}
	public void setTakenHoursOff(Integer takenHoursOff) {
		this.takenHoursOff = takenHoursOff;
	}
	public Integer getTakenVacationHours() {
		return takenVacationHours;
	}
	public void setTakenVacationHours(Integer takenVacationHours) {
		this.takenVacationHours = takenVacationHours;
	}
}
