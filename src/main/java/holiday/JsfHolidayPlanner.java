package holiday;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class JsfHolidayPlanner implements Serializable {
	private static final long serialVersionUID = 1L;

	private static HoursTaken hoursTaken = new InMemoryHoursTaken();
	
	private Integer hoursOffPerMonth = 3, vacationHoursPerMonth = 2, startHoursOff = 10, startVacationHours = 40;
	private String startMonth = "Jan-2015", endMonth = "Mar-2015";
	
	public Integer getHoursOffPerMonth() {
		return hoursOffPerMonth;
	}
	public void setHoursOffPerMonth(Integer hoursOffPerMonth) {
		this.hoursOffPerMonth = hoursOffPerMonth;
	}
	public Integer getVacationHoursPerMonth() {
		return vacationHoursPerMonth;
	}
	public void setVacationHoursPerMonth(Integer vacationHoursPerMonth) {
		this.vacationHoursPerMonth = vacationHoursPerMonth;
	}
	public Integer getStartHoursOff() {
		return startHoursOff;
	}
	public void setStartHoursOff(Integer startHoursOff) {
		this.startHoursOff = startHoursOff;
	}
	public Integer getStartVacationHours() {
		return startVacationHours;
	}
	public void setStartVacationHours(Integer startVacationHours) {
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
	
	private List<PlannedMonth> months;
	public List<PlannedMonth> getMonths() {
		return months;
	}
	
	public void plan() {
		months = new ArrayList<>();
		Plan plan = (month, remainingHoursOff, remainingVacationHours) ->
			months.add(new PlannedMonth(month, remainingHoursOff, remainingVacationHours,
					hoursTaken.hoursOffTaken(month), hoursTaken.vacationHoursTaken(month)));
		HolidayPlanner planner = new HolidayPlanner(plan, hoursTaken, startMonth, startHoursOff, 
				hoursOffPerMonth, startVacationHours, vacationHoursPerMonth);
		planner.planUntil(endMonth);
	}

	public void takeHoursOff(PlannedMonth month) {
		hoursTaken.takeHoursOff(month.getTakenHoursOff(), month.getMonth());
		plan();
	}
	public void takeVacationHours(PlannedMonth month) {
		hoursTaken.takeVacationHours(month.getTakenVacationHours(), month.getMonth());
		plan();
	}
}
