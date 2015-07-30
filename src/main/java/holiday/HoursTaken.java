package holiday;

public interface HoursTaken {
	void takeHoursOff(int hours, String month);
	void takeVacationHours(int hours, String month);
	int hoursOffTaken(String month);
	int vacationHoursTaken(String month);
}
