import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

public class WordStatisticsTest {
	
	private final WordStatistics wordStatistics = new WordStatistics();

	@Test
	public void testCounter() throws IOException {
		
		String input[] = {"one" , "two" , "three" };
		Map<String, Integer> output = wordStatistics.counter(input);
		Integer numOne = 1;
		assertEquals(output.get("one") , numOne);
		assertEquals(output.get("two") , numOne);
		assertEquals(output.get("four") , null);
	}
	
	@Test
	public void testReplaceWord() throws IOException {
		
		String input[] = {"one" , "two" , "three" , "one1"};
		String output = wordStatistics.replaceWord(input, "one" , "hello");
		assertTrue(output.contains("hello two three one1"));
		
	}

}
