package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class LocalDatesTest {
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yyyy");
	@Test
	public void dateFormatting() throws Exception {
		LocalDate dt = LocalDate.now();
		System.out.println(formatter.format(dt));
		System.out.println(formatter.parse("Jan-2015"));
	}
}
