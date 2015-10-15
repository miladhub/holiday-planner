package holiday.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import holiday.HolidayConfiguration;
import holiday.HolidayContext;

@ManagedBean
@RequestScoped
public class JsfSetup implements Serializable {
	private static final long serialVersionUID = 1L;

	private double hoursOffPerMonth, vacationHoursPerMonth, startHoursOff, startVacationHours;
	private String startMonth;

	@Inject private HolidayContext context;
	
	@PostConstruct
	public void loadSettings() {
		this.startHoursOff = context.configuration().initialHoursOff;
		this.startVacationHours = context.configuration().initialVacationHours;
		this.vacationHoursPerMonth = context.configuration().vacationHoursPerMonth;
		this.hoursOffPerMonth = context.configuration().hoursOffPerMonth;
		this.startMonth = context.configuration().startMonth;
	}
	
	public void save() {
		context.save(new HolidayConfiguration(startHoursOff, hoursOffPerMonth, startVacationHours,
				vacationHoursPerMonth, startMonth));
	}
	
	public Double getHoursOffPerMonth() {
		return hoursOffPerMonth;
	}
	public void setHoursOffPerMonth(Double hoursOffPerMonth) {
		this.hoursOffPerMonth = hoursOffPerMonth;
	}
	public Double getVacationHoursPerMonth() {
		return vacationHoursPerMonth;
	}
	public void setVacationHoursPerMonth(Double vacationHoursPerMonth) {
		this.vacationHoursPerMonth = vacationHoursPerMonth;
	}
	public Double getStartHoursOff() {
		return startHoursOff;
	}
	public void setStartHoursOff(Double startHoursOff) {
		this.startHoursOff = startHoursOff;
	}
	public Double getStartVacationHours() {
		return startVacationHours;
	}
	public void setStartVacationHours(Double startVacationHours) {
		this.startVacationHours = startVacationHours;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
}
