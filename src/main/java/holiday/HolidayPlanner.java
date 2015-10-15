package holiday;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HolidayPlanner {
	private final HoursTaken hoursTaken;
	private final Plan plan;
	private final double initialHoursOff;
	private final double hoursOffPerMonth;
	private final double initialVacationHours;
	private final double vacationHoursPerMonth;
	private final DateTimeFormatter monthsFormatter = DateTimeFormatter.ofPattern("MMM-yyyy");
	private final DateTimeFormatter daysFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	private final String initialMonth;

	public HolidayPlanner(Plan plan, HoursTaken hoursTaken, HolidayContext context) {
		this.plan = plan;
		this.hoursTaken = hoursTaken;
		this.initialHoursOff = context.configuration().initialHoursOff;
		this.hoursOffPerMonth = context.configuration().hoursOffPerMonth;
		this.initialVacationHours = context.configuration().initialVacationHours;
		this.vacationHoursPerMonth = context.configuration().vacationHoursPerMonth;
		this.initialMonth = context.configuration().startMonth;
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
