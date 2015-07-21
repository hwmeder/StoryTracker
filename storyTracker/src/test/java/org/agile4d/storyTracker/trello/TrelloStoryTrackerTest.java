package org.agile4d.storyTracker.trello;

import static org.junit.Assert.*;

import java.util.Map;

import org.agile4d.storyTracker.StoryTracker;
import org.agile4d.storyTracker.trello.TrelloStoryTracker;
import org.junit.Test;

public class TrelloStoryTrackerTest {

	@Test
	public void test() {
		StoryTracker trelloStoryTracker = new TrelloStoryTracker();
		Map<String, Integer> actual = trelloStoryTracker.getStoryStatusCounts();
		System.out.println();
		System.out.println("Returned HashMap:");
		System.out.println(actual);
	}

}
