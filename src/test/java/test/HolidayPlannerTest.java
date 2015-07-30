package test;

import holiday.HolidayPlanner;
import holiday.HoursTaken;
import holiday.Plan;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class HolidayPlannerTest {
	@Rule public final JUnitRuleMockery context = new JUnitRuleMockery();
	
	private final Plan plan = context.mock(Plan.class);
	private final HoursTaken hoursTaken = context.mock(HoursTaken.class);
	private final HolidayPlanner planner = new HolidayPlanner(plan, hoursTaken, "Jan-2015", 10, 5, 20, 3);
	
	@Test
	public void keepsTrackOfHoursOffTaken() throws Exception {
		context.checking(new Expectations() {{
			oneOf(hoursTaken).takeHoursOff(3, "Jan-2015");
		}});
		planner.takeHoursOff(3, "Jan-2015");
	}
	
	@Test
	public void keepsTrackOfVacationHoursTaken() throws Exception {
		context.checking(new Expectations() {{
			oneOf(hoursTaken).takeVacationHours(2, "Jan-2015");
		}});
		planner.takeVacationHours(2, "Jan-2015");
	}
	
	@Test
	public void visitsHoursOffTakenWhenPlanningOneMonth() throws Exception {
		context.checking(new Expectations() {{
			allowing(hoursTaken).hoursOffTaken("Jan-2015");
				will(returnValue(3));
			allowing(hoursTaken).hoursOffTaken(with(Matchers.not("Jan-2015")));
				will(returnValue(0));
			allowing(hoursTaken).vacationHoursTaken("Jan-2015");
				will(returnValue(2));
			allowing(hoursTaken).vacationHoursTaken(with(Matchers.not("Jan-2015")));
				will(returnValue(0));
				
			oneOf(plan).remainingHours("Jan-2015", 7, 18);
		}});
		planner.planUntil("Jan-2015");
	}
	
	@Test
	public void visitsHoursOffTakenWhenPlanningTwoMonths() throws Exception {
		context.checking(new Expectations() {{
			allowing(hoursTaken).hoursOffTaken("Jan-2015");
			will(returnValue(3));
			allowing(hoursTaken).hoursOffTaken(with(Matchers.not("Jan-2015")));
				will(returnValue(0));
			allowing(hoursTaken).vacationHoursTaken("Jan-2015");
				will(returnValue(2));
			allowing(hoursTaken).vacationHoursTaken(with(Matchers.not("Jan-2015")));
				will(returnValue(0));
				
			oneOf(plan).remainingHours("Jan-2015", 7, 18);
			oneOf(plan).remainingHours("Feb-2015", 12, 21);
		}});
		planner.planUntil("Feb-2015");
	}
	
	@Test
	public void visitsHoursOffTakenWhenPlanningMultipleMonths() throws Exception {
		context.checking(new Expectations() {{
			allowing(hoursTaken).hoursOffTaken("Jan-2015");
			will(returnValue(3));
			allowing(hoursTaken).hoursOffTaken(with(Matchers.not("Jan-2015")));
				will(returnValue(0));
			allowing(hoursTaken).vacationHoursTaken("Jan-2015");
				will(returnValue(2));
			allowing(hoursTaken).vacationHoursTaken(with(Matchers.not("Jan-2015")));
				will(returnValue(0));
				
			oneOf(plan).remainingHours("Jan-2015", 7, 18);
			oneOf(plan).remainingHours("Feb-2015", 12, 21);
			oneOf(plan).remainingHours("Mar-2015", 17, 24);
		}});
		planner.planUntil("Mar-2015");
	}
}
