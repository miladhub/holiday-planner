package holiday.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import holiday.HolidayPlanner;
import holiday.HolidayContext;
import holiday.HoursTaken;
import holiday.Plan;

@ManagedBean
@ViewScoped
public class JsfHolidayPlanner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject private HoursTaken hoursTaken;
	@Inject private HolidayContext context;
	
	private String endMonth = "Aug-2016";
	private List<PlannedMonth> months;	
	private HolidayPlanner planner;

	@PostConstruct
	private void createPlanner() {
		Plan plan = (month, remainingHoursOff, remainingVacationHours) ->
			months.add(new PlannedMonth(month, remainingHoursOff, remainingVacationHours,
					hoursTaken.hoursOffTaken(month), hoursTaken.vacationHoursTaken(month)));
		planner = new HolidayPlanner(plan, hoursTaken, context);
		plan();
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
