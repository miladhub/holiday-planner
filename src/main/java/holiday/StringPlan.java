package holiday;

public class StringPlan implements Plan {
	private String plan = "";
	
	public String dump() {
		return plan;
	}

	@Override
	public void remainingHours(String month, int remainingHoursOff, int remainingVacationHours) {
		plan += month + " ... " + format(remainingHoursOff) + "  ... " + format(remainingVacationHours) + "\n";
	}

	private String format(int hours) {
		return String.format("%3d", hours);
	}
}
