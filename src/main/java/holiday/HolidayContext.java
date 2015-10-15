package holiday;

public interface HolidayContext {
	HolidayConfiguration configuration();
	void save(HolidayConfiguration holidayConfiguration);
}
