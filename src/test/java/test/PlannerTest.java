package test;

import holiday.Increments;
import holiday.Planner;
import holiday.RemainingsListener;
import holiday.Remaining;
import holiday.RemainingsRepository;

import java.util.Calendar;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

public class PlannerTest {
	//comment at start of file changed
	@Rule public final JUnitRuleMockery context = new JUnitRuleMockery();

	private final RemainingsListener responder = context.mock(RemainingsListener.class);
	private final RemainingsRepository remainings = context.mock(RemainingsRepository.class);

	@Test
	public void showsIncreasedDaysAndHoursAvailableMinusDaysUsedLastMonth() {
		final Increments increments = new Increments(16, 8);
		final Planner planner = new Planner(responder, Calendar.JANUARY, Calendar.FEBRUARY, remainings, increments);

		context.checking(new Expectations() {{
			havingStartedWith(Calendar.JANUARY, 3, 42);
			oneOf(responder).remainingAtTheBeginningOf(Calendar.FEBRUARY,
					new Remaining(3 - 2 + 16, 42 + 8));
		}});

		planner.markDaysOffIn(Calendar.JANUARY, 2);
	}

	@Test @Ignore
	public void takesIntoAccountDaysOffAlreadyScheduled() {
		final Increments increments = new Increments(16, 8);
		final Planner planner = new Planner(responder, Calendar.JANUARY, Calendar.MARCH, remainings, increments);

		context.checking(new Expectations() {{
			havingStartedWith(Calendar.JANUARY, 3, 42);
			oneOf(responder).remainingAtTheBeginningOf(Calendar.FEBRUARY, new Remaining(3 + 16 - 2, 42 + 8));
			oneOf(responder).remainingAtTheBeginningOf(Calendar.MARCH, new Remaining(3 + 16 + 16 - 2, 42 + 8 + 8));
			oneOf(responder).remainingAtTheBeginningOf(Calendar.MARCH, new Remaining(3 + 16 + 16 - 2 - 7, 42 + 8 + 8));
		}});

		planner.markDaysOffIn(Calendar.JANUARY, 2);
		planner.markDaysOffIn(Calendar.FEBRUARY, 7);
	}

	@Test
	public void showsIncreasedDaysAndHoursAvailableMinusHoursUsedLastMonth() {
		final Increments increments = new Increments(16, 8);
		final Planner planner = new Planner(responder, Calendar.JANUARY, Calendar.FEBRUARY, remainings, increments);

		context.checking(new Expectations() {{
			havingStartedWith(Calendar.JANUARY, 3, 42);
			oneOf(responder).remainingAtTheBeginningOf(Calendar.FEBRUARY,
					new Remaining(3 + 16, 42 - 2 + 8));
		}});

		planner.markHoursOffIn(Calendar.JANUARY, 2);
	}


	@Test
	public void updatesRemainingHolidaysForAllMonthsInPlan() {
		final Increments increments = new Increments(16, 8);
		final Planner planner = new Planner(responder, Calendar.JANUARY, Calendar.APRIL, remainings, increments);

		context.checking(new Expectations() {{
			havingStartedWith(Calendar.JANUARY, 3, 42);
			oneOf(responder).remainingAtTheBeginningOf(Calendar.FEBRUARY, new Remaining(3 - 2 + 16, 42 + 8));
			oneOf(responder).remainingAtTheBeginningOf(Calendar.MARCH, new Remaining(3 - 2 + 16 + 16, 42 + 8 + 8));
			oneOf(responder).remainingAtTheBeginningOf(Calendar.APRIL, new Remaining(3 - 2 + 16 + 16 + 16, 42 + 8 + 8 + 8));
		}});

		planner.markDaysOffIn(Calendar.JANUARY, 2);
	}

	@Test
	public void updatesRemainingHoursOffForAllMonthsInPlan() {
		final Increments increments = new Increments(16, 8);
		final Planner planner = new Planner(responder, Calendar.JANUARY, Calendar.APRIL, remainings, increments);

		context.checking(new Expectations() {{
			havingStartedWith(Calendar.JANUARY, 3, 42);
			oneOf(responder).remainingAtTheBeginningOf(Calendar.FEBRUARY, new Remaining(3 + 16, 42 + 8 - 2));
			oneOf(responder).remainingAtTheBeginningOf(Calendar.MARCH, new Remaining(3 + 16 + 16, 42 + 8 + 8 - 2));
			oneOf(responder).remainingAtTheBeginningOf(Calendar.APRIL, new Remaining(3 + 16 + 16 + 16, 42 + 8 + 8 + 8 - 2));
		}});

		planner.markHoursOffIn(Calendar.JANUARY, 2);
	}

	private void havingStartedWith(final int month, final int days, final int hours) {
		context.checking(new Expectations() {{
			allowing(remainings).remainingAvailableIn(month);
				will(returnValue(new Remaining(days, hours)));
		}});
	}

	//a comment
}
