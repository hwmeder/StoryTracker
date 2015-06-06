package org.agile4d.storyTracker.mingle;

import java.util.Map;

import org.agile4d.storyTracker.StoryTracker;
import org.junit.Test;

public class MingleStoryTrackerTest {

	@Test
	public void test() {
		StoryTracker mingleStoryTracker = new MingleStoryTracker();
		Map<String, Integer> actual = mingleStoryTracker.getStoryStatusCounts();
		System.out.println();
		System.out.println("Returned HashMap:");
		System.out.println(actual);
	}

}
