package holiday;

public class HolidayConfiguration {
	public final double initialHoursOff;
	public final double hoursOffPerMonth;
	public final double initialVacationHours;
	public final double vacationHoursPerMonth;
	public final String startMonth;

	public HolidayConfiguration(double initialHoursOff, double hoursOffPerMonth,
			double initialVacationHours, double vacationHoursPerMonth, String startMonth) {
		this.initialHoursOff = initialHoursOff;
		this.hoursOffPerMonth = hoursOffPerMonth;
		this.initialVacationHours = initialVacationHours;
		this.vacationHoursPerMonth = vacationHoursPerMonth;
		this.startMonth = startMonth;
	}
}
