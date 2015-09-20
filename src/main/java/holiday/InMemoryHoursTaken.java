package holiday;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

@Singleton
public class InMemoryHoursTaken implements HoursTaken {
	private final Map<String,Double> hoursOff = new ConcurrentHashMap<>();
	private final Map<String,Double> vacationHours = new ConcurrentHashMap<>();
	@Override
	public void takeHoursOff(double hours, String month) {
		hoursOff.put(month, hours);
	}
	@Override
	public void takeVacationHours(double hours, String month) {
		vacationHours.put(month, hours);
	}
	@Override
	public double hoursOffTaken(String month) {
		return hoursOff.containsKey(month) ? hoursOff.get(month) : 0;
	}
	@Override
	public double vacationHoursTaken(String month) {
		return vacationHours.containsKey(month) ? vacationHours.get(month) : 0;
	}
}
