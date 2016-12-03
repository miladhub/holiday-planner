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

    private class RemainingHours {
        private final double hoursOff, vacationHours;
        private final String month;

        private RemainingHours(double hoursOff, double vacationHours, String month) {
            this.hoursOff = hoursOff;
            this.vacationHours = vacationHours;
            this.month = month;
        }

        private String nextMonth() {
            return date(month).plusMonths(1).format(monthsFormatter);
        }
    }

    public void planUntil(String endMonth) {
        RemainingHours rh = accountForInitialMonth(new RemainingHours(initialHoursOff, initialVacationHours, initialMonth));
        while (!nextIsAfterEndMonth(date(rh.month), endMonth)) {
            rh = accountForMonth(rh);
        }
    }

    private RemainingHours accountForInitialMonth(RemainingHours current) {
        RemainingHours next = new RemainingHours(
                current.hoursOff - hoursTaken.hoursOffTaken(current.month),
                current.vacationHours - hoursTaken.vacationHoursTaken(current.month),
                current.nextMonth());
        plan.remainingHours(current.month, next.hoursOff, next.vacationHours);
        return next;
    }

    private RemainingHours accountForMonth(RemainingHours current) {
        RemainingHours next = new RemainingHours(
                current.hoursOff + hoursOffPerMonth - hoursTaken.hoursOffTaken(current.month),
                current.vacationHours + vacationHoursPerMonth - hoursTaken.vacationHoursTaken(current.month),
                current.nextMonth());
        plan.remainingHours(current.month, next.hoursOff, next.vacationHours);
        return next;
    }

    private boolean nextIsAfterEndMonth(LocalDate currentMonth, String endMonth) {
        return currentMonth.compareTo(date(endMonth)) > 0;
    }

    private LocalDate date(String month) {
        return LocalDate.parse("01-" + month, daysFormatter);
    }
}
