package jt0723;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

/**
 * Test suite for the ToolRental class, the entry point of this application. This also contains the required test scenarios.
 */
public class ToolRentalTest {
    @Test public void test1() {
        ToolRental subject = new ToolRental();
        assertThrows("checkout throws IllegalArgumentException for invalid discount", IllegalArgumentException.class, () -> {
            subject.checkout(Tool.JAKR, 5, 101, LocalDate.of(2015, 9, 3));
        });
    }

    @Test public void test2() {
        ToolRental subject = new ToolRental();
        final RentalAgreement rentalAgreement = subject.checkout(Tool.LADW, 3, 10, LocalDate.of(2020, 7, 2));
        rentalAgreement.printReport();
        final String report = rentalAgreement.toString();
        assertTrue(report.contains("Charge days: 2\n"));
        assertTrue(report.contains("Final charge: $3.58\n"));
    }

    @Test public void test3() {
        ToolRental subject = new ToolRental();
        final RentalAgreement rentalAgreement = subject.checkout(Tool.CHNS, 5, 25, LocalDate.of(2015, 7, 2));
        rentalAgreement.printReport();
        final String report = rentalAgreement.toString();
        assertTrue(report.contains("Charge days: 3\n"));
        assertTrue(report.contains("Final charge: $3.35\n"));
    }

    @Test public void test4() {
        ToolRental subject = new ToolRental();
        final RentalAgreement rentalAgreement = subject.checkout(Tool.JAKD, 6, 0, LocalDate.of(2015, 9, 3));
        rentalAgreement.printReport();
        final String report = rentalAgreement.toString();
        assertTrue(report.contains("Charge days: 3\n"));
        assertTrue(report.contains("Final charge: $8.97\n"));
    }

    @Test public void test5() {
        ToolRental subject = new ToolRental();
        final RentalAgreement rentalAgreement = subject.checkout(Tool.JAKR, 9, 0, LocalDate.of(2015, 7, 2));
        rentalAgreement.printReport();
        final String report = rentalAgreement.toString();
        assertTrue(report.contains("Charge days: 5\n"));
        assertTrue(report.contains("Final charge: $14.95\n"));
    }

    @Test public void test6() {
        ToolRental subject = new ToolRental();
        final RentalAgreement rentalAgreement = subject.checkout(Tool.JAKR, 4, 50, LocalDate.of(2020, 7, 2));
        rentalAgreement.printReport();
        final String report = rentalAgreement.toString();
        assertTrue(report.contains("Charge days: 1\n"));
        assertTrue(report.contains("Final charge: $1.49\n"));
    }

    @Test public void testNegativeRentalDays() {
        ToolRental subject = new ToolRental();
        assertThrows("checkout throws IllegalArgumentException for negative rental day count", IllegalArgumentException.class, () -> { 
            subject.checkout(Tool.JAKR, -1, 50, LocalDate.of(2020, 7, 2)); 
        });
    }

}
