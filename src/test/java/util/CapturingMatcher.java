package util;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alioth Latour
 * @datetime 5/7/2021 3:46 PM
 */

public class CapturingMatcher<T> extends BaseMatcher<T> {
	
	private List<T> matchedList = new ArrayList<>();
	
	@Override
	public boolean matches(Object matched) {
		matchedList.add((T) matched);
		return true;
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText("any object");
	}
	
	public <T> T getLastValue() {
		return (matchedList.isEmpty()) ? null : (T) matchedList.get(matchedList.size() - 1);
	}
	
}
