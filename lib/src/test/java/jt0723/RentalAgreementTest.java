package jt0723;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class RentalAgreementTest {
    @Test public void testLaborDay() {
        RentalAgreement subject = new RentalAgreement(Tool.LADW, 9, 0, LocalDate.of(2023, 8, 28));
        String report = subject.toString();
        System.out.println(report);
        assertTrue(report.contains("Charge days: 8\n"));
    }

    @Test public void testNoLaborDay() {
        RentalAgreement subject = new RentalAgreement(Tool.LADW, 5, 0, LocalDate.of(2023, 8, 28));
        String report = subject.toString();
        assertTrue(report.contains("Charge days: 5\n"));
    }
}
