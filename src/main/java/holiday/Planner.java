package holiday;

public class Planner {
	private RemainingsListener listener;
	private RemainingsRepository remainingsRepo;
	private int startMonth;
	private Increments increments;
	private int endMonth;

	public Planner(RemainingsListener listener, int startMonth, int endMonth, RemainingsRepository remainingsRepo, Increments increments) {
		this.listener = listener;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
		this.remainingsRepo = remainingsRepo;
		this.increments = increments;
	}

	public void markDaysOffIn(int month, int vacationHours) {
		Remaining initiallyAvailable = initiallyAvailable();
		for (int currMonth = month + 1; currMonth <= endMonth; currMonth++) {
			listener.remainingAtTheBeginningOf(currMonth, new Remaining(
					initiallyAvailable.vacationHours - vacationHours + increments.holidayHoursPerMonth * currMonth,
					initiallyAvailable.hoursOff + increments.hoursOffPerMonth * currMonth));
		}
	}

	public void markHoursOffIn(int month, int hoursOff) {
		Remaining initiallyAvailable = initiallyAvailable();
		for (int currMonth = month + 1; currMonth <= endMonth; currMonth++) {
			listener.remainingAtTheBeginningOf(currMonth, new Remaining(
					initiallyAvailable.vacationHours + increments.holidayHoursPerMonth * currMonth,
					initiallyAvailable.hoursOff - hoursOff + increments.hoursOffPerMonth * currMonth));
		}
	}

	private Remaining initiallyAvailable() {
		return remainingsRepo.remainingAvailableIn(startMonth);
	}
}
