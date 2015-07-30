package holiday;

public class PlannedMonth {
	private final String month;
	private final int hoursOff;
	private final int vacationHours;
	private Integer changedHoursOff, changedVacationHours;
	public Integer getChangedHoursOff() {
		return changedHoursOff;
	}
	public void setChangedHoursOff(Integer changedHoursOff) {
		this.changedHoursOff = changedHoursOff;
	}
	public Integer getChangedVacationHours() {
		return changedVacationHours;
	}
	public void setChangedVacationHours(Integer changedVacationHours) {
		this.changedVacationHours = changedVacationHours;
	}
	public PlannedMonth(String month, int hoursOff, int vacationHours) {
		super();
		this.month = month;
		this.hoursOff = hoursOff;
		this.vacationHours = vacationHours;
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
}
