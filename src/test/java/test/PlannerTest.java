package test;

import holiday.Planner;
import holiday.PlanResponder;
import holiday.Residuals;

import java.util.Calendar;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class PlannerTest {
	@Rule public final JUnitRuleMockery context = new JUnitRuleMockery();

	private final PlanResponder responder = context.mock(PlanResponder.class);
	private final Planner planner = new Planner(responder, Calendar.JANUARY, Calendar.FEBRUARY);

	@Test
	public void showsIncreasedDaysAndHoursAvailableMinusUsedLastMonth() {
		context.checking(new Expectations() {{
			havingStartedWith(Calendar.JANUARY, 3, 42);
			assumingMonthlyIncrementsOf(1.8, 1.5);
			oneOf(responder).residualsAtTheEndOf(Calendar.FEBRUARY, new Residuals(3 - 2 + 1.8, 42 + 1.5));
		}});
		planner.markDaysOffIn(Calendar.JANUARY, 2);
	}

	private void assumingMonthlyIncrementsOf(double days, double hours) {

	}

	private void havingStartedWith(int month, double days, double hours) {

	}

	//TODO: 6 months plan
	//TODO: remove Calendar
	//TODO: cross a year
	//TODO: mark hours off
}
