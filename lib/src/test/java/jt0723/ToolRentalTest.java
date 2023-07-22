package jt0723;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Test suite for the ToolRental class, the entry point of this application. This also contains the required test scenarios.
 */
public class ToolRentalTest {
    @Test public void test1() {
        ToolRental classUnderTest = new ToolRental();
        assertThrows("test1", IllegalArgumentException.class, () -> {
            classUnderTest.checkout(Tool.JAKR, 5, 101, LocalDate.of(2015, 9, 3));
        });
    }

    @Test public void test2() {
        ToolRental classUnderTest = new ToolRental();
        assertNotNull("test2", classUnderTest.checkout(Tool.LADW, 3, 10, LocalDate.of(2020, 7, 2)));
    }

    @Test public void test3() {
        ToolRental classUnderTest = new ToolRental();
        assertNotNull("test3", classUnderTest.checkout(Tool.CHNS, 5, 25, LocalDate.of(2015, 7, 2)));
    }

    @Test public void test4() {
        ToolRental classUnderTest = new ToolRental();
        assertNotNull("test4", classUnderTest.checkout(Tool.JAKD, 6, 0, LocalDate.of(2015, 9, 3)));
    }

    @Test public void test5() {
        ToolRental classUnderTest = new ToolRental();
        assertNotNull("test5", classUnderTest.checkout(Tool.JAKR, 9, 0, LocalDate.of(2015, 7, 2)));
    }

    @Test public void test6() {
        ToolRental classUnderTest = new ToolRental();
        assertNotNull("test6", classUnderTest.checkout(Tool.JAKR, 4, 50, LocalDate.of(2020, 7, 2)));
    }

    @Test public void testNegativeRentalDays() {
        ToolRental classUnderTest = new ToolRental();
        assertThrows("checkout throws IllegalArgumentException for negative rental day count", IllegalArgumentException.class, () -> { 
            classUnderTest.checkout(Tool.JAKR, -1, 50, LocalDate.of(2020, 7, 2)); 
        });
    }

}
