package holiday;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

@Singleton
public class InMemoryHoursTaken implements HoursTaken {
	private final Map<String,Integer> hoursOff = new ConcurrentHashMap<>();
	private final Map<String,Integer> vacationHours = new ConcurrentHashMap<>();
	@Override
	public void takeHoursOff(int hours, String month) {
		hoursOff.put(month, hours);
	}
	@Override
	public void takeVacationHours(int hours, String month) {
		vacationHours.put(month, hours);
	}
	@Override
	public int hoursOffTaken(String month) {
		return hoursOff.containsKey(month) ? hoursOff.get(month) : 0;
	}
	@Override
	public int vacationHoursTaken(String month) {
		return vacationHours.containsKey(month) ? vacationHours.get(month) : 0;
	}
}
