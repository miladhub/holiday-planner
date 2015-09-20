package holiday;

public interface HoursTaken {
	void takeHoursOff(double hours, String month);
	void takeVacationHours(double hours, String month);
	double hoursOffTaken(String month);
	double vacationHoursTaken(String month);
}
