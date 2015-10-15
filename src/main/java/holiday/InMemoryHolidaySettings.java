package holiday;

import javax.inject.Singleton;

@Singleton
public class InMemoryHolidaySettings implements HolidayContext {
	private HolidayConfiguration holidayConfiguration = new HolidayConfiguration(93, 8.67, 28.89, 13.4, "Aug-2015");

	public InMemoryHolidaySettings() {
		super();
	}

	@Override
	public HolidayConfiguration configuration() {
		return holidayConfiguration;
	}

	@Override
	public void save(HolidayConfiguration holidayConfiguration) {
		this.holidayConfiguration = holidayConfiguration;
	}
	
	public static InMemoryHolidaySettings of(HolidayConfiguration holidayConfiguration) {
		InMemoryHolidaySettings settings = new InMemoryHolidaySettings();
		settings.save(holidayConfiguration);
		return settings;
	}
}
