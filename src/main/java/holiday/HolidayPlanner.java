package holiday;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HolidayPlanner {
	private final HoursTaken hoursTaken;
	private final Plan plan;
	private final String initialMonth;
	private final double initialHoursOff;
	private final double hoursOffPerMonth;
	private final double initialVacationHours;
	private final double vacationHoursPerMonth;
	private final DateTimeFormatter monthsFormatter = DateTimeFormatter.ofPattern("MMM-yyyy");
	private final DateTimeFormatter daysFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

	public HolidayPlanner(Plan plan, HoursTaken hoursTaken, String initialMonth,
			double initialHoursOff, double hoursOffPerMonth, double initialVacationHours,
			double vacationHoursPerMonth) {
		this.plan = plan;
		this.hoursTaken = hoursTaken;
		this.initialMonth = initialMonth;
		this.initialHoursOff = initialHoursOff;
		this.hoursOffPerMonth = hoursOffPerMonth;
		this.initialVacationHours = initialVacationHours;
		this.vacationHoursPerMonth = vacationHoursPerMonth;
	}

	public void takeHoursOff(double hours, String month) {
		hoursTaken.takeHoursOff(hours, month);
	}
	
	public void takeVacationHours(double hours, String month) {
		hoursTaken.takeVacationHours(hours, month);
	}

	public void planUntil(String endMonth) {
		double lastHoursOff = initialHoursOff - hoursTaken.hoursOffTaken(initialMonth);
		double lastVacationHours = initialVacationHours - hoursTaken.vacationHoursTaken(initialMonth);
		plan.remainingHours(initialMonth, lastHoursOff, lastVacationHours);
		
		LocalDate currentMonth = date(initialMonth).plusMonths(1);
		while (!nextIsAfterEndMonth(currentMonth, endMonth)) {
			lastHoursOff = lastHoursOff + hoursOffPerMonth - hoursTaken.hoursOffTaken(currentMonth.format(monthsFormatter));
			lastVacationHours = lastVacationHours + vacationHoursPerMonth - hoursTaken.vacationHoursTaken(currentMonth.format(monthsFormatter));
			plan.remainingHours(currentMonth.format(monthsFormatter), lastHoursOff, lastVacationHours);
			currentMonth = currentMonth.plusMonths(1);
		}
	}

	private boolean nextIsAfterEndMonth(LocalDate currentMonth, String endMonth) {
		return currentMonth.compareTo(date(endMonth)) > 0;
	}

	private LocalDate date(String month) {
		return LocalDate.parse("01-" + month, daysFormatter);
	}
}
