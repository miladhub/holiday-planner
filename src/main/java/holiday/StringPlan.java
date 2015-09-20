package holiday;

public class StringPlan implements Plan {
	private String plan = "";
	
	public String dump() {
		return plan;
	}

	@Override
	public void remainingHours(String month, double remainingHoursOff, double remainingVacationHours) {
		plan += month + " ... " + format(remainingHoursOff) + "  ... " + format(remainingVacationHours) + "\n";
	}

	private String format(double hours) {
		return String.format("%3.0f", hours);
	}
}
