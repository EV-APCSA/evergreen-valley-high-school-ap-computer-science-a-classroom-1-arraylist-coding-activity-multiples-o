
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberListTest {

    /** Helper: build expected 0,3,6,...,297 */
    private List<Integer> expectedMultiples() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(3 * i);
        }
        return list;
    }

    /** Helper: only odd multiples of 3 from 0..297 */
    private List<Integer> expectedOddMultiples() {
        List<Integer> all = expectedMultiples();
        List<Integer> odds = new ArrayList<>();
        for (int n : all) {
            if (n % 2 != 0) {
                odds.add(n);
            }
        }
        return odds;
    }

    @Test
    void testMultiplesOf3SizeAndValues() {
        NumberList nl = new NumberList();
        ArrayList<Integer> list = nl.multiplesOf3();

        assertNotNull(list, "multiplesOf3 should not return null");
        assertEquals(100, list.size(), "List should contain 100 numbers");

        // check first and last
        assertEquals(0, list.get(0), "First element should be 0");
        assertEquals(297, list.get(99), "Last element should be 297");

        // check full sequence
        assertEquals(expectedMultiples(), list,
                "List should contain 0,3,6,...,297 in order");
    }

    @Test
    void testToStringContainsAllNumbersInOrder() {
        NumberList nl = new NumberList();
        ArrayList<Integer> list = nl.multiplesOf3();
        String s = nl.toString(list);

        assertNotNull(s, "toString should not return null");
        List<Integer> parsed = extractInts(s);

        assertEquals(expectedMultiples(), parsed,
                "toString output should contain all multiples in order");

        // very light formatting check: should have at least 9 line breaks (10 rows)
        long newlines = s.chars().filter(ch -> ch == '\n').count();
        assertTrue(newlines >= 9, "Expected at least 9 line breaks (10 rows)");
    }

    @Test
    void testRemoveEvensRemovesOnlyEvens() {
        NumberList nl = new NumberList();
        ArrayList<Integer> list = nl.multiplesOf3();

        nl.removeEvens(list);

        assertNotNull(list, "List should not be null after removeEvens");

        // no even numbers remain
        for (int n : list) {
            assertNotEquals(0, n % 2, "List should not contain even numbers");
        }

        // should match expected odd multiples of 3
        assertEquals(expectedOddMultiples(), list,
                "List should contain only odd multiples of 3 in order");
    }

    /** Helper: pull out all integers from a string, in order. */
    private List<Integer> extractInts(String s) {
        List<Integer> result = new ArrayList<>();
        if (s == null) return result;

        String[] tokens = s.split("\\s+");
        for (String tok : tokens) {
            if (tok.isEmpty()) continue;
            try {
                result.add(Integer.parseInt(tok));
            } catch (NumberFormatException ignored) {
                // ignore non-numeric tokens
            }
        }
        return result;
    }
}
