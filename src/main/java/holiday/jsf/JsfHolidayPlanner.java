package holiday.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import holiday.HolidayPlanner;
import holiday.HoursTaken;
import holiday.Plan;

@ManagedBean
@ViewScoped
public class JsfHolidayPlanner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject private HoursTaken hoursTaken;
	
	private double hoursOffPerMonth = 8.67, vacationHoursPerMonth = 13.4, startHoursOff = 93, startVacationHours = 28.89;
	private String startMonth = "Aug-2015", endMonth = "Aug-2016";
	private List<PlannedMonth> months;	
	private HolidayPlanner planner;

	@PostConstruct
	private void createPlanner() {
		Plan plan = (month, remainingHoursOff, remainingVacationHours) ->
			months.add(new PlannedMonth(month, remainingHoursOff, remainingVacationHours,
					hoursTaken.hoursOffTaken(month), hoursTaken.vacationHoursTaken(month)));
		planner = new HolidayPlanner(plan, hoursTaken, startMonth, startHoursOff, 
				hoursOffPerMonth, startVacationHours, vacationHoursPerMonth);
	}
	
	
	public void takeHoursOff(PlannedMonth month) {
		planner.takeHoursOff(month.getTakenHoursOff(), month.getMonth());
		plan();
	}
	public void takeVacationHours(PlannedMonth month) {
		planner.takeVacationHours(month.getTakenVacationHours(), month.getMonth());
		plan();
	}
	public void plan() {
		months = new ArrayList<>();
		planner.planUntil(endMonth);
	}
	
	public boolean isShouldRenderTable() {
		return months != null && !months.isEmpty();
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
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public List<PlannedMonth> getMonths() {
		return months;
	}
}
